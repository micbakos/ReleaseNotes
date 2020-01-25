#!/usr/bin/env kscript

@file:Include("src/Models.kt")
@file:Include("src/ArgumentsReader.kt")
@file:Include("src/GitLogger.kt")
@file:Include("src/PullRequestResolver.kt")
@file:Include("src/NotesWriter.kt")

args.read().also {
    it.gitLog().resolve(it.project).write()
}

