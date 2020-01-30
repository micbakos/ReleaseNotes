package com.micbakos.notes

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.File
import kotlin.system.exitProcess

private val NOTES_FILE = "notesConfig.json"

fun Arguments.resolveConfig(): Config {
    val configFileName = NOTES_FILE
    val matches: Array<File>? = File(directory).listFiles { _, name -> name.endsWith(configFileName) }

    if (matches == null || matches.isEmpty()) {
        System.err.println("No file named '$configFileName' was found inside '$directory'")
        exitProcess(-1)
    } else {
        return matches[0].readConfig()
    }
}

fun File.readConfig(): Config {
    return try {
        Gson().fromJson(readText(), Config::class.java)
    } catch (exception: JsonSyntaxException) {
        System.err.println("Error parsing $NOTES_FILE")
        exitProcess(-1)
    }
}