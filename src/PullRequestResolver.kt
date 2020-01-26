
fun String.resolve(): List<PullRequest> {
    val output = lines()

    // The title consists of two capture groups. The PR's id and branch
    val titleRegex = "Merge pull request #([0-9]+) from taxibeat/(.+)".toRegex()
    // The body also consists of two capture groups. The JIRA id and the rest of the title
    val bodyRegex = "\\[(.+-\\d+)] (.+)".toRegex()

    // A pull request is analyzed in every two lines as described in the --format command
    return MutableList(output.size / 2) { index ->
        val outputIndex = index * 2
        val commitTitle = output[outputIndex]
        val body = output[outputIndex + 1]

        val commitTitleResults = titleRegex.find(commitTitle)
        val commitBodyResults = bodyRegex.find(body)

        return@MutableList if (commitTitleResults != null && commitBodyResults != null) {
            val (id, branch) = commitTitleResults.destructured
            val (issueId, title) = commitBodyResults.destructured

            PullRequest(id = id, branch = branch, issueId = issueId, title = title)
        } else {
            null
        }
    }
        .filterNotNull()
}
