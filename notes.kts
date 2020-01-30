#!/usr/bin/env kscript

@file:DependsOn("com.google.code.gson:gson:2.8.6")
@file:Include("src/com/micbakos/notes/Pipeline.kt")
@file:Include("src/com/micbakos/notes/Models.kt")
@file:Include("src/com/micbakos/notes/ArgumentsReader.kt")
@file:Include("src/com/micbakos/notes/EnvironmentConfig.kt")
@file:Include("src/com/micbakos/notes/GitLogger.kt")
@file:Include("src/com/micbakos/notes/PullRequestResolver.kt")
@file:Include("src/com/micbakos/notes/NotesWriter.kt")

import DependsOn

Pipeline(args).start()
