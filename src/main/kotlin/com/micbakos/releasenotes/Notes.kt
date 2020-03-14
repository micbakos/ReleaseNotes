package com.micbakos.releasenotes

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