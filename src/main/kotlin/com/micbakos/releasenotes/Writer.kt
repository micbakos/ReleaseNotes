package com.micbakos.releasenotes

fun report(pullRequests: Map<String, List<PullRequest>>): String {
    val output = StringBuilder()
    pullRequests.entries.forEachIndexed { index, pair ->
        if (index > 0) output.appendln().appendln()

        output.appendln("**${pair.key}**")
        pair.value.forEachIndexed { prIndex, pr ->
            if (prIndex > 0) output.appendln()
            output.append("* ${pr.title} ${pr.id}")
        }
    }

    return output.toString()
}
