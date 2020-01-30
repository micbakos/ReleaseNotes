package com.micbakos.notes

class Pipeline(private val args: Array<String>) {

    fun start() {
        val arguments = args.read()
        val config = resolveConfig(arguments)
        val logResult = gitLog(arguments)
    }

}
