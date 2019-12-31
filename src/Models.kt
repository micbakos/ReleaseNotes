data class Arguments(
    val directory: String,
    val project: Project,
    val fromCommit: String,
    val toCommit: String
)

enum class Project {
    PASSENGER,
    DRIVER;

    fun other() = when (this) {
        PASSENGER -> DRIVER
        DRIVER -> PASSENGER
    }

    companion object {

        fun fromArgument(value: String): Project {
            values().forEach {
                if (it.name.equals(value, ignoreCase = true)) return it
            }

            throw IllegalArgumentException()
        }

    }
}

data class PullRequest(
    val id: String,
    val branch: String,
    val issueId: String,
    val title: String
)
