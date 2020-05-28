package com.micbakos.releasenotes

import kotlin.system.exitProcess

fun Array<String>.read(): Arguments {
    return if (size in 4..5) {
        Arguments(
            directory = directory(),
            variant = variant(),
            fromCommit = fromCommit(),
            toCommit = toCommit(),
            output = output()
        )
    } else {
        System.err.println(
            "Incorrect arguments. Valid arguments are:\n" +
                    "\t$ Notes.kts <project-dir> <variant> <from-commit> <to-commit> <output-file(optional)>"
        )
        exitProcess(0)
    }
}

private fun Array<String>.directory() = get(0)
private fun Array<String>.variant() = get(1)
private fun Array<String>.fromCommit() = get(2)
private fun Array<String>.toCommit() = get(3)
private fun Array<String>.output() = if (size == 5) Output.FileOutput(get(4)) else Output.StdOutput
