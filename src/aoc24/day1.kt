package aoc24


import kotlin.math.abs


fun main() {
    val input = input(2024, 1)


    /*
    Pair up the smallest number in the left list with the smallest number in the right list,
    then the second-smallest left number with the second-smallest right number, and so on.
     */
    fun part1(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        input.forEach {
            val (l, r) = it.split("   ")
            left.add(l.toInt())
            right.add(r.toInt())
        }

        val x = left.sorted()
        val y = right.sorted()

        val r = x.zip(y).sumOf {
            abs(it.first - it.second)
        }

        return r
    }


    fun part2(input: List<String>): Int {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        input.forEach {
            val (l, r) = it.split("   ")
            left.add(l.toInt())
            right.add(r.toInt())
        }

        var r = left.sumOf { l -> l * right.count { it == l } }

        return r
    }


    "--- 1 ---------------------------".println()

    val raw1 = "3   4\n" +
            "4   3\n" +
            "2   5\n" +
            "1   3\n" +
            "3   9\n" +
            "3   3"
    val example1 = raw1.split('\n')
    check(part1(example1) == 11)
    part1(input).println() // TODO SOLUTION day 0 part 1 = 2769675


    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 31)
    part2(input).println() // TODO SOLUTION day 0 part 2 = 24643097

}
