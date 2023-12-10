package aoc23


fun main() {
    val input = input(2023, 6)


    fun p1(input: List<String>): Long {
        val times = input[0].split(':')[1].splitNoBlanks().map { it.toLong() }
        val distances = input[1].split(':')[1].splitNoBlanks().map { it.toLong() }

        var result = 1L

        for (idx in times.indices) {
            val time = times[idx]
            val distanceToBeat = distances[idx]

            var count = 0

            for (hold in 1..time) {
                val distanceTravelled = (time - hold) * hold
                if (distanceTravelled > distanceToBeat) {
                    count++
                }
            }

            result *= count
        }

        return result
    }


    fun part1(input: List<String>) = p1(input)


    fun part2(input: List<String>) = p1(input.map { it.replace(" ", "") })


    "--- 1 ---------------------------".println()

    val raw1 = "Time:      7  15   30\n" +
            "Distance:  9  40  200"
    val example1 = raw1.split('\n')
    check(part1(example1) == 288L)
    part1(input).println() // TODO SOLUTION day 6 part 1 = 1660968

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 71503L)
    part2(input).println() // TODO SOLUTION day 6 part 2 = 26499773
}
