package com.micbakos.releasenotes

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream


internal class ArgumentsReaderTest {

    companion object {

        private const val TEST_DIR = "some/directory/to/project"
        private const val TEST_VARIANT = "project1"
        private const val TEST_FROM_COMMIT = "d0625b6fd42eaa046df16cecaff2ba7eb625c813"
        private const val TEST_TO_COMMIT = "385fe2928332e2b13e24ac73aae8c113e59d9782"

    }

    @Test
    fun `given array of four arguments, when read is invoked, then the result arguments is the expected`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT, TEST_TO_COMMIT)

        val result = input.read()

        assertEquals(Arguments(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT, TEST_TO_COMMIT), result)
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    fun `given array of less arguments, when read is invoked, then the process terminates with status 0`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT)
        stubSystemError()

        input.read()
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    fun `given array of less arguments, when read is invoked, then the process terminates with error message`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT)
        val errorStream = stubSystemError()

        input.read()

        assertEquals("\"Incorrect arguments. Valid arguments are:\\n\\t\$ Notes.kts <project-dir> <variant> <from-commit> <to-commit>\"", errorStream.toString())
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    fun `given array of more arguments, when read is invoked, then the result arguments is the expected`() {
        val input = arrayOf(TEST_DIR, TEST_VARIANT, TEST_FROM_COMMIT, TEST_TO_COMMIT, "another argument")
        val errorStream = stubSystemError()

        input.read()

        assertEquals("\"Incorrect arguments. Valid arguments are:\\n\\t\$ Notes.kts <project-dir> <variant> <from-commit> <to-commit>\"", errorStream.toString())
    }

    private fun stubSystemError(): ByteArrayOutputStream = ByteArrayOutputStream().apply {
        System.setErr(PrintStream(this))
    }
}