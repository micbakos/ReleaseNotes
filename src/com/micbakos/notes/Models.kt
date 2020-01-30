package com.micbakos.notes

import com.google.gson.annotations.SerializedName

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

data class Config(
    @SerializedName("github_url")
    val githubUrl: String,
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