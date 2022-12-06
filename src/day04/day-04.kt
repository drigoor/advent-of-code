package day04


import utils.*


val directory = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun parseRange(str: String): IntRange {
    val limits = str.split("-").map { it.toInt() }
    return limits[0]..limits[1]
}


fun tottalyIn(r1: IntRange, r2: IntRange): Boolean {
    val a = r1.first
    val b = r1.last
    val x = r2.first
    val y = r2.last

    return (r1.contains(x) && r1.contains(y)) || (r2.contains(a) && r2.contains(b))
}

fun partiallyIn(r1: IntRange, r2: IntRange): Boolean {
    val a = r1.first
    val b = r1.last
    val x = r2.first
    val y = r2.last

    return r1.contains(x) || r1.contains(y) || r2.contains(a) || r2.contains(b)
}


fun main() {
    val lines = readLines(directory, "my.in")

    val common = lines
        .map {
            it.split(",")
                .map { parseRange(it) }
        }

    val part1 = common
        .map {
            tottalyIn(it[0], it[1])
        }
        .count { it }

    val part2 = common
        .map {
            partiallyIn(it[0], it[1])
        }
        .count { it }

    printResults("2", part1, part2)
}
