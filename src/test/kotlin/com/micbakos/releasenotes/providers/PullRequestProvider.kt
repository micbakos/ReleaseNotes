package com.micbakos.releasenotes.providers

import com.micbakos.releasenotes.PullRequest

fun pullRequestsMap() = mapOf(
    "Features" to listOf(
        PullRequest(id = "#100", branch = "feature/add-new-feature", title = "[[ID-1000]](https://issue.tracker.com/ID-1000) Add new feature to project"),
        PullRequest(id = "#103", branch = "feature/team1/feature-of-team-1", title = "[[ID-1052]](https://issue.tracker.com/ID-1052) Feature of team 1 with another link [[ID-1053]](https://issue.tracker.com/ID-1053)")
    ),
    "Bugs" to listOf(
        PullRequest(id = "#101", branch = "bug/fix-bug", title = "[[ID-1023]](https://issue.tracker.com/ID-1023) Fix nasty bug in project")
    )
)