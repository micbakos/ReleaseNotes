import kotlin.system.exitProcess

fun Array<String>.read(): Arguments {
    return if (size == 4) {
        try {
            Arguments(directory = get(0), project = Project.fromArgument(get(1)), fromCommit = get(2), toCommit = get(3))
        } catch (exception: IllegalArgumentException) {
            System.err.println("Incorrect project value. Valid values are 'passenger' or 'driver'")
            exitProcess(-1)
        }
    } else {
        System.err.println("Incorrect arguments. Valid arguments are:\n\t$ notes.kts <project-dir> <passenger|driver> <from-commit> <to-commit>")
        exitProcess(-1)
    }
}
