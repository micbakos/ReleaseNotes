#!/usr/bin/env kscript

@file:Include("src/com/micbakos/notes/Pipeline.kt")
@file:Include("src/com/micbakos/notes/Models.kt")
@file:Include("src/com/micbakos/notes/ArgumentsReader.kt")
@file:Include("src/com/micbakos/notes/GitLogger.kt")
@file:Include("src/com/micbakos/notes/PullRequestResolver.kt")
@file:Include("src/com/micbakos/notes/NotesWriter.kt")

import DependsOn

Pipeline(args).start()
