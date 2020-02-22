#!/usr/bin/env kscript

@file:DependsOn("com.google.code.gson:gson:2.8.6")
@file:Include("src/com/micbakos/notes/ArgumentsReader.kt")
@file:Include("src/com/micbakos/notes/EnvironmentConfig.kt")
@file:Include("src/com/micbakos/notes/GitLogger.kt")
@file:Include("src/com/micbakos/notes/Models.kt")
@file:Include("src/com/micbakos/notes/PullRequestResolver.kt")
@file:Include("src/com/micbakos/notes/Writer.kt")
@file:EntryPoint("Notes")

import DependsOn
import Include
import EntryPoint
import com.micbakos.notes.gitLog
import com.micbakos.notes.read
import com.micbakos.notes.resolve
import com.micbakos.notes.resolveConfig

class Notes {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Notes().start(args)
        }
    }

    fun start(args: Array<String>) {
        val arguments = args.read()
        val config = resolveConfig(arguments)
        val logResult = gitLog(arguments)
        val pullRequests = resolve(logResult, config, arguments)
        println(write(pullRequests))
    }

}