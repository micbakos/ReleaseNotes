package com.micbakos.releasenotes

import com.micbakos.releasenotes.providers.pullRequestsMap
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class WriterTest {

    companion object {

        private const val FILE_OUTPUT = "src/test/resources/output/output.txt"

    }

    @Test
    fun `given map of pull requests, when writer is invoked, then the returned output is the expected`() {
        val result = write(pullRequestsMap())

        assertEquals(expectedOutput(), result)
    }

    private fun expectedOutput() = File(FILE_OUTPUT).readText()

}