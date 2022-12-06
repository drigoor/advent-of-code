package utils


import java.io.File


private fun dir(directory: String) = listOf(
    System.getProperty("user.dir"),
    "src",
    directory
).joinToString(separator = File.separator, postfix = File.separator)


fun readLines(directory: String, filename: String) = File(dir(directory) + filename).readLines()


fun printResults(part1Check: String, part1: Any, part2: Any) {
    val part1Message = if (part1.toString() == part1Check) "Correct Solution for example with" else "Wrong Solution for example with"

    println("$part1Message $part1")
    println("Solution for part two: $part2")
}
