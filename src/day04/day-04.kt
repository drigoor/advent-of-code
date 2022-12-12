package day04


import utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun parseRange(str: String): IntRange {
    val limits = str.split("-").map { it.toInt() }
    return limits[0]..limits[1]
}


fun isTottalyIn(r1: IntRange, r2: IntRange) = (r1.contains(r2.first) && r1.contains(r2.last)) || (r2.contains(r1.first) && r2.contains(r1.last))


fun isPartiallyIn(r1: IntRange, r2: IntRange) =  r1.contains(r2.first) || r1.contains(r2.last) || r2.contains(r1.first) || r2.contains(r1.last)


fun main() {
    val lines = readLines(directory, "my.in")

    val common = lines
        .map {
            it.split(",")
                .map { parseRange(it) }
        }

    val part1 = common
        .map {
            isTottalyIn(it[0], it[1])
        }
        .count { it }

    val part2 = common
        .map {
            isPartiallyIn(it[0], it[1])
        }
        .count { it }

    printResults("2", part1, part2)
}
