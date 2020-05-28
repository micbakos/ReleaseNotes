package com.micbakos.releasenotes

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus
import com.micbakos.releasenotes.utilities.stubSystemError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ArgumentsReaderTest {

    companion object {

        private const val TEST_DIR = "some/directory/to/project"
        private const val TEST_VARIANT = "project1"
        private const val TEST_FROM_COMMIT = "d0625b6fd42eaa046df16cecaff2ba7eb625c813"
        private const val TEST_TO_COMMIT = "385fe2928332e2b13e24ac73aae8c113e59d9782"
        private const val TEST_FILE_OUTPUT = "path/to/output.md"
    }

    @Test
    fun `given array of four arguments, when read is invoked, then the result arguments is the expected`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT, TEST_TO_COMMIT)

        val result = input.read()

        assertEquals(
            Arguments(
                directory = TEST_DIR,
                variant = TEST_VARIANT,
                fromCommit = TEST_FROM_COMMIT,
                toCommit = TEST_TO_COMMIT,
                output = Output.StdOutput
            ), result
        )
    }

    @Test
    fun `given array of five arguments, when read is invoked, then the result arguments is the expected`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT, TEST_TO_COMMIT, TEST_FILE_OUTPUT)

        val result = input.read()

        assertEquals(
            Arguments(
                directory = TEST_DIR,
                variant = TEST_VARIANT,
                fromCommit = TEST_FROM_COMMIT,
                toCommit = TEST_TO_COMMIT,
                output = Output.FileOutput(TEST_FILE_OUTPUT)
            ), result
        )
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    fun `given array of less arguments, when read is invoked, then the process terminates with status 0`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT)
        stubSystemError()

        input.read()
    }

}
