package com.micbakos.notes

import kotlin.system.exitProcess

fun Array<String>.read(): Arguments {
    return if (size == 4) {
        Arguments(directory = get(0), variant = get(1), fromCommit = get(2), toCommit = get(3))
    } else {
        System.err.println("Incorrect arguments. Valid arguments are:\n\t$ notes.kts <project-dir> <variant> <from-commit> <to-commit>")
        exitProcess(-1)
    }
}
