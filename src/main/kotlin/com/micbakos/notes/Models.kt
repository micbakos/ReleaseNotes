package com.micbakos.notes

/**
 * Need a class to be able to use a const val, since top level constants are not supported
 */
class ProjectConfiguration {

    companion object {
        private const val JSON_SUFFIX = "json"
        private const val YAML_SUFFIX = "yaml"
        private const val YML_SUFFIX = "yml"
        const val FILE_NAME = "notesConfig.$JSON_SUFFIX"
        const val PULL_REQUEST_ID_REGEX = "(#\\d+)"
    }

}

data class Arguments(
    val directory: String,
    val variant: String,
    val fromCommit: String,
    val toCommit: String
)

data class PullRequest(
    val id: String,
    val branch: String,
    val title: String
)

data class Config(
    val variants: List<Variant>,
    val links: List<Link>
)

data class Variant(
    val name: String,
    val categories: List<Category>
)

data class Category(
    val title: String,
    val regex: String
)

data class Link(
    val url: String,
    val regex: String
)
