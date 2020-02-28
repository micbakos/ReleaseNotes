package com.micbakos.notes

fun write(pullRequests: Map<String, List<PullRequest>>): String {
    val output = StringBuilder()
    pullRequests.entries.forEach {
        output.appendln("**${it.key}**")
        it.value.forEach { pr ->
            output.appendln("* ${pr.title} ${pr.id}")
        }
        output.appendln()
    }
    return output.toString()
}