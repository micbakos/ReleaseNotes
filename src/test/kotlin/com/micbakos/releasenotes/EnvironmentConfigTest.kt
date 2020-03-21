package com.micbakos.releasenotes

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus
import com.micbakos.releasenotes.providers.arguments
import com.micbakos.releasenotes.providers.config
import com.micbakos.releasenotes.utilities.stubSystemError
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EnvironmentConfigTest {

    companion object {

        private const val DIR_NORMAL_BOTH = "src/test/resources/normal/both"
        private const val DIR_NORMAL_JSON = "src/test/resources/normal/json"
        private const val DIR_NORMAL_YML = "src/test/resources/normal/yml"

        private const val DIR_MALFORMED_BOTH = "src/test/resources/malformed/both"
        private const val DIR_MALFORMED_JSON = "src/test/resources/malformed/json"
        private const val DIR_MALFORMED_YML = "src/test/resources/malformed/yml"

    }

    @Nested
    @DisplayName("Correct configuration")
    inner class NormalParsing {

        @Test
        fun `given both files, when resolving config, then the expected config is returned`() {
            val arguments = arguments(directory = DIR_NORMAL_BOTH)

            val result = resolveConfig(arguments)

            assertEquals(config(), result)
        }

        @Test
        fun `given json file, when resolving config, then the expected config is returned`() {
            val arguments = arguments(directory = DIR_NORMAL_JSON)

            val result = resolveConfig(arguments)

            assertEquals(config(), result)
        }

        @Test
        fun `given yml file, when resolving config, then the expected config is returned`() {
            val arguments = arguments(directory = DIR_NORMAL_YML)

            val result = resolveConfig(arguments)

            assertEquals(config(), result)
        }

    }

    @Nested
    @DisplayName("Wrong directory")
    inner class WrongConfiguration {

        @Test
        @ExpectSystemExitWithStatus(0)
        fun `given directory with no config file, when resolving config, then the correct error is print out in std err`() {
            val directory = "/dir"
            val arguments = arguments(directory = directory)
            val errorStream = stubSystemError()

            resolveConfig(arguments)

            assertEquals(
                "No file named '[notesConfig.json, notesConfig.yaml, notesConfig.yml]' was found inside '$directory'",
                errorStream.toString()
            )
        }

    }

    @Nested
    @DisplayName("Malformed configuration files")
    inner class MalformedConfFiles {

        @Test
        @ExpectSystemExitWithStatus(0)
        fun `given directory with both malformed files, when resolving config, then the correct error is print out in std err`() {
            val arguments = arguments(directory = DIR_MALFORMED_BOTH)
            val errorStream = stubSystemError()

            resolveConfig(arguments)

            assertEquals(
                "Error parsing notesConfig.json",
                errorStream.toString()
            )
        }

        @Test
        @ExpectSystemExitWithStatus(0)
        fun `given directory with json malformed file, when resolving config, then the correct error is print out in std err`() {
            val arguments = arguments(directory = DIR_MALFORMED_JSON)
            val errorStream = stubSystemError()

            resolveConfig(arguments)

            assertEquals(
                "Error parsing notesConfig.json",
                errorStream.toString()
            )
        }

        @Test
        @ExpectSystemExitWithStatus(0)
        fun `given directory with yml malformed files, when resolving config, then the correct error is print out in std err`() {
            val arguments = arguments(directory = DIR_MALFORMED_YML)
            val errorStream = stubSystemError()

            resolveConfig(arguments)

            assertEquals(
                "Error parsing notesConfig.yml",
                errorStream.toString()
            )
        }

    }

}