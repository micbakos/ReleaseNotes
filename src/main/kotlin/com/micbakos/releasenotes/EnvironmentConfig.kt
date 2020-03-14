package com.micbakos.releasenotes

import com.sksamuel.hoplite.ConfigLoader
import java.io.File
import kotlin.system.exitProcess

fun resolveConfig(arguments: Arguments): Config {
    val matches: Array<File>? = File(arguments.directory).listFiles { _, name ->
        ProjectConfiguration.VALID_FILE_NAMES.contains(name)
    }

    if (matches == null || matches.isEmpty()) {
        System.err.println("No file named '${ProjectConfiguration.VALID_FILE_NAMES}' was found inside '${arguments.directory}'")
        exitProcess(0)
    } else {
        if (matches.size > 1) System.out.println("Found multiple configuration files. Loading ${matches[0].name}")
        return matches[0].readConfig()
    }
}

fun File.readConfig(): Config {
    return try {
        ConfigLoader().loadConfigOrThrow<Config>(this.toPath())
    } catch (exception: RuntimeException) {
        System.err.println("Error parsing ${this.name}")
        exception.printStackTrace()
        exitProcess(0)
    }
}
