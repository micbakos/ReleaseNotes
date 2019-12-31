fun String.toJiraLink() = "[$this](https://jira.taxibeat.com/browse/$this)"
fun String.toGithubLink() = "[#$this](https://github.com/taxibeat/android/pull/$this)"

fun List<PullRequest>.write() = forEach {
    println("${it.issueId.toJiraLink()} ${it.title} ${it.id.toGithubLink()}")
}
