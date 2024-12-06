package aoc24


import kotlin.math.abs


fun main() {
    val input = input(2024, 2)


    fun isLevelSafeP1(level: List<Int>): Boolean {
        var previous = level[0]
        val isIncreasing = level[1] > previous

        for (i in 1 until level.size) {
            val current = level[i]
            val diff = current - previous

            // The levels are either...
            if (isIncreasing) {
                // ...all increasing...
                if (previous > current) {
                    return false
                }
            } else {
                // ...or all decreasing.
                if (previous < current) {
                    return false
                }
            }

            val delta = abs(diff)

            // Any two adjacent levels differ by at least one and at most three.
            if (delta !in 1..3) {
                return false
            }

            previous = current
        }

        return true
    }


    fun isReportSafeP1(input: List<String>): Int =
        input.splitNoBlanks2Int().sumOf { if (isLevelSafeP1(it)) "1".toInt() else "0".toInt() }


    /*
        So, a report only counts as safe if both of the following are true:
        - The levels are either all increasing or all decreasing.
        - Any two adjacent levels differ by at least one and at most three.
         */
    fun part1(input: List<String>) = isReportSafeP1(input)


    fun isLevelSafeP2(level: List<Int>): Boolean {
        if (isLevelSafeP1(level)) return true

        for (i in level.indices) {
            if (isLevelSafeP1(removeElementAtIndex(level, i))) {
                return true
            }
        }

        return false
    }


    fun isReportSafeP2(input: List<String>): Int =
        input.splitNoBlanks2Int().sumOf { if (isLevelSafeP2(it)) "1".toInt() else "0".toInt() }


    /*
    Now, the same rules apply as before, except if removing a single level from an unsafe report
    would make it safe, the report instead counts as safe.
     */
    fun part2(input: List<String>) = isReportSafeP2(input)


    "--- 1 ---------------------------".println()

    val raw1 = "7 6 4 2 1\n" +
            "1 2 7 8 9\n" +
            "9 7 6 2 1\n" +
            "1 3 2 4 5\n" +
            "8 6 4 4 1\n" +
            "1 3 6 7 9"
    val example1 = raw1.split('\n')
    check(part1(example1) == 2)
    part1(input).println() // SOLUTION day 2 part 1 = 269


    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 4)
    part2(input).println() // TODO SOLUTION day 1 part 2 = 636 TODO NO! 337
}
