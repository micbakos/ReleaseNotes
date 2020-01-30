package com.micbakos.notes

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.File
import kotlin.system.exitProcess

fun Arguments.resolveConfig(): Config {
    val matches: Array<File>? = File(directory).listFiles { _, name ->
        name.endsWith(ProjectConfiguration.FILE_NAME)
    }

    if (matches == null || matches.isEmpty()) {
        System.err.println("No file named '${ProjectConfiguration.FILE_NAME}' was found inside '$directory'")
        exitProcess(-1)
    } else {
        return matches[0].readConfig()
    }
}

fun File.readConfig(): Config {
    return try {
        Gson().fromJson(readText(), Config::class.java)
    } catch (exception: JsonSyntaxException) {
        System.err.println("Error parsing ${ProjectConfiguration.FILE_NAME}")
        exitProcess(-1)
    }
}