package com.micbakos.notes

import kotlin.system.exitProcess

fun resolve(log: String, config: Config, arguments: Arguments): Map<String, List<PullRequest>> {
    val output = log.lines()
    val pullRequestCategories = mutableMapOf<String, ArrayList<PullRequest>>()
    for (line in 0..(output.size - 2) step 2) {
        // A pull request is analyzed in every two lines as described in the --format command
        val commitTitle = output[line]
        val body = output[line + 1]

        val variant = config.variants.find { it.name == arguments.variant } ?: run {
            System.err.println("Variant named: ${arguments.variant} does not exist in ${ProjectConfiguration.FILE_NAME}")
            exitProcess(-1)
        }

        val category = variant.categories.find {
            it.regex.toRegex().containsMatchIn(commitTitle)
        } ?: continue

        val branchName = category.regex.toRegex().find(commitTitle)?.value ?: continue

        val pullRequestId = ProjectConfiguration.PULL_REQUEST_ID_REGEX.toRegex()
            .find(commitTitle)?.value ?: continue

        /*config.links.forEach { link ->
            body = body.replace(link.regex.toRegex(), link.url)
        }*/

        val pullRequest = PullRequest(pullRequestId, branchName, body)
        pullRequestCategories[category.title]?.add(pullRequest) ?: run {
            pullRequestCategories[category.title] = arrayListOf(pullRequest)
        }
    }

    return pullRequestCategories
}
