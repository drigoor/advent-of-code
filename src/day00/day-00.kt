package day00


import utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun main() {
    val lines = readLines(directory, "example.in")

    // HERE

    val part1 = 42

    val part2 = 42

    printResults("", part1, part2)
}
