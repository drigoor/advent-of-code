package day01


import utils.*


val directory = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun main() {
    val lines = readLines(directory, "my.in")

    val data: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())

    lines.forEach {
        if (it.isBlank()) {
            data.add(mutableListOf()) // TODO usar +=
        } else {
            data.last().add(it.toInt())
        }
    }

    val common = data
        .map { it.sum() }

    val part1 = common
        .max()

    val part2 = common
        .sortedDescending()
        .take(3)
        .sum()

    printResults("24000", part1, part2)
}
