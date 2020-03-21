package com.micbakos.releasenotes.utilities

import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun stubSystemError(): ByteArrayOutputStream = ByteArrayOutputStream().apply {
    System.setErr(PrintStream(this))
}