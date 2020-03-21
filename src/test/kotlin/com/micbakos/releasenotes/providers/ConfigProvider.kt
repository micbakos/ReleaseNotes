package com.micbakos.releasenotes.providers

import com.micbakos.releasenotes.Category
import com.micbakos.releasenotes.Config
import com.micbakos.releasenotes.Link
import com.micbakos.releasenotes.Variant

fun config(
    variants: List<Variant> = listOf(variant()),
    links: List<Link> = listOf(link())
) = Config(
    variants = variants,
    links = links
)

fun variant(
    name: String = "project",
    categories: List<Category> = listOf(
        category(),
        category("Bugs", "bug\\/.+")
    )
) = Variant(
    name = name,
    categories = categories
)

fun category(
    title: String = "Features",
    regex: String = "feature\\/.+"
) = Category(
    title = title,
    regex = regex
)

fun link(
    url: String = "https://issue.tracker.com/\$1",
    regex: String = "\\[(\\D+-\\d+)\\]"
) = Link(
    url = url,
    regex = regex
)