package com.micbakos.notes

import com.sksamuel.hoplite.ConfigException
import com.sksamuel.hoplite.ConfigLoader
import java.io.File
import kotlin.system.exitProcess

fun resolveConfig(arguments: Arguments): Config {
    val matches: Array<File>? = File(arguments.directory).listFiles { _, name ->
        name.endsWith(ProjectConfiguration.FILE_NAME)
    }

    if (matches == null || matches.isEmpty()) {
        System.err.println("No file named '${ProjectConfiguration.FILE_NAME}' was found inside '${arguments.directory}'")
        exitProcess(-1)
    } else {
        return matches[0].readConfig()
    }
}

fun File.readConfig(): Config {
    return try {
        ConfigLoader().loadConfigOrThrow<Config>(this.toPath())
    } catch (exception: ConfigException) {
        System.err.println("Error parsing ${ProjectConfiguration.FILE_NAME}")
        exitProcess(-1)
    }
}
