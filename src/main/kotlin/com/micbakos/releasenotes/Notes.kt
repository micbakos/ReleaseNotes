package com.micbakos.releasenotes

import java.io.File

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
        val output = report(pullRequests)

        print(arguments, output)
    }

    private fun print(arguments: Arguments, outputStr: String) {
        when (arguments.output) {
            is Output.FileOutput -> File(arguments.output.path).writeText(outputStr)
            is Output.StdOutput -> println(outputStr)
        }
    }

}
