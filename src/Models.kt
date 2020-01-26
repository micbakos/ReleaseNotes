data class Arguments(
    val directory: String,
    val fromCommit: String,
    val toCommit: String
)

data class PullRequest(
    val id: String,
    val branch: String,
    val issueId: String,
    val title: String
)
