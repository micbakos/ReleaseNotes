package com.micbakos.releasenotes

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus
import com.micbakos.releasenotes.providers.arguments
import com.micbakos.releasenotes.providers.config
import com.micbakos.releasenotes.providers.pullRequestsMap
import com.micbakos.releasenotes.utilities.stubSystemError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class PullRequestResolverTest {

    companion object {

        private const val PROJECT_DIR = "src/test/resources/normal/json"
        private const val FILE_GIT_LOG = "src/test/resources/git-log/git-log.txt"

    }

    @Test
    fun `given git log and correct arguments, when resolving the log, the map of pull requests are the expected ones`() {
        val gitLog = File(FILE_GIT_LOG).readText()
        val arguments = arguments(directory = PROJECT_DIR)
        val config = config()

        val pullRequests = resolve(gitLog, config, arguments)

        assertEquals(pullRequestsMap(), pullRequests)
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    fun `given git log and incorrect variant name, when resolving the log, the error is print in std err`() {
        val gitLog = File(FILE_GIT_LOG).readText()
        val arguments = arguments(directory = PROJECT_DIR)
        val config = config(variants = listOf(Variant("Whatever", categories = listOf())))
        val error = stubSystemError()

        resolve(gitLog, config, arguments)

        assertEquals("Variant named: Whatever does not exist in file loaded.", error.toString())
    }

}