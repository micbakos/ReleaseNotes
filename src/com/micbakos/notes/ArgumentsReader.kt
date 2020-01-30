package com.micbakos.notes

import kotlin.system.exitProcess

fun Array<String>.read(): Arguments {
    return if (size == 3) {
        Arguments(directory = get(0), fromCommit = get(1), toCommit = get(2))
    } else {
        System.err.println("Incorrect arguments. Valid arguments are:\n\t$ notes.kts <project-dir> <passenger|driver> <from-commit> <to-commit>")
        exitProcess(-1)
    }
}
