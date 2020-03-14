package com.micbakos.releasenotes

import kotlin.system.exitProcess

fun resolve(log: String, config: Config, arguments: Arguments): Map<String, List<PullRequest>> {
    val output = log.lines()
    val pullRequestCategories = mutableMapOf<String, ArrayList<PullRequest>>()
    for (line in 0..(output.size - 2) step 2) {
        // A pull request is analyzed in every two lines as described in the --format command
        val commitTitle = output[line]
        val body = output[line + 1]

        val variant = ensureVariant(config, arguments)

        val category = findMatchedCategory(variant, commitTitle) ?: continue

        val branchName = category.regex.toRegex().find(commitTitle)?.value ?: continue

        val pullRequestId = ProjectConfiguration.PULL_REQUEST_ID_REGEX.toRegex()
            .find(commitTitle)?.value ?: continue

        val bodyWithLinks = resolveLinks(config, body)

        val pullRequest = PullRequest(pullRequestId, branchName, bodyWithLinks)
        pullRequestCategories[category.title]?.add(pullRequest) ?: run {
            pullRequestCategories[category.title] = arrayListOf(pullRequest)
        }
    }

    return pullRequestCategories
}

private fun ensureVariant(config: Config, arguments: Arguments): Variant {
    return config.variants.find { it.name == arguments.variant } ?: run {
        System.err.println("Variant named: ${arguments.variant} does not exist in file loaded.")
        exitProcess(-1)
    }
}

private fun findMatchedCategory(variant: Variant, commitTitle: String): Category? {
    return variant.categories.find {
        it.regex.toRegex().containsMatchIn(commitTitle)
    }
}

private fun resolveLinks(config: Config, rawBody: String): String {
    var body = rawBody
    config.links.forEach { link ->
        val regex = link.regex.toRegex()

        regex.findAll(rawBody).forEach {
            val matchedLink = it.value
            val replacement = matchedLink.replace(regex, link.url)
            body = body.replace(matchedLink, "[$matchedLink]($replacement)")
        }
    }
    return body
}
