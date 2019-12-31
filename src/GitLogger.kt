import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

fun Arguments.gitLog(): String {
    // A command consists of a list of strings that will be fed to the process builder sequentially
    val command = listOf(
        "git",
        "log",
        "${fromCommit}..${toCommit}", // The range of commits to show
        "--format=%s%n%b", // The format as of: Title\nBody
        "--merges", // Accept only merge commits
        "--grep=Merge pull request" // Filter commits which are actualy pull request merges
    )

    return command.execute(File(directory))
}


/** Extension method on List<String> that executes the sequence of string commands in the given directory */
fun List<String>.execute(directory: File): String {
    val process = ProcessBuilder(this)
        .directory(directory)
        .start()
        .also { it.waitFor(10, TimeUnit.SECONDS) }

    if (process.exitValue() != 0) {
        System.err.write(process.errorStream.readBytes())
        exitProcess(process.exitValue())
    }

    return process.inputStream.bufferedReader().readText()
}
