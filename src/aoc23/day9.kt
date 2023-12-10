package aoc23


fun main() {
    val input = input(2023, 9)


    fun px(numbers: List<Int>, fn: (List<Int>) -> Int): List<Int> {
        var current = numbers
        val finalValues = mutableListOf<Int>()

        do {
            val notAllZeros = !current.allZeros()

            if (notAllZeros) {
                finalValues.add(fn(current)) // <-- secret sauce [1] with fn...
                current = current.reduceIt()
            }
        } while (notAllZeros)

        return finalValues
    }


    val p1Fn = { lst: List<Int> -> lst.last() }
    val p2Fn = { lst: List<Int> -> lst.first() }

    fun p1(numbers: List<Int>) = px(numbers, p1Fn).sum()
    fun p2(numbers: List<Int>) = px(numbers, p2Fn).subOfReversed() // <-- secret sauce [2]

    fun part1(input: List<String>) = input.splitNoBlanks2Int().sumOf { p1(it) }
    fun part2(input: List<String>) = input.splitNoBlanks2Int().sumOf { p2(it) }


    "--- 1 ---------------------------".println()

    val raw1 = "0 3 6 9 12 15\n" +
            "1 3 6 10 15 21\n" +
            "10 13 16 21 30 45"
    val example1 = raw1.split('\n')
    check(part1(example1) == 114)
    part1(input).println() // TODO SOLUTION day 9 part 1 = 1772145754

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 2)
    part2(input).println() // TODO SOLUTION day 9 part 2 = 867

}
