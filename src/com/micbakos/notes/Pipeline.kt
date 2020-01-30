package com.micbakos.notes

class Pipeline(private val args: Array<String>) {

    fun start() {
        val arguments = args.read()
        //arguments.gitLog().resolve().write()
        val config = arguments.resolveConfig()
        println(config.toString())
    }

}
