package aoc23


fun main() {
    val input = input(2023, 7)


    val card2number = mapOf(
        "A" to 14,
        "K" to 13,
        "Q" to 12,
        "J" to 11,
        "T" to 10,
        "9" to 9,
        "8" to 8,
        "7" to 7,
        "6" to 6,
        "5" to 5,
        "4" to 4,
        "3" to 3,
        "2" to 2
    )


    fun fullHouse(hand: String) = false

    fun fiveKind(hand: String) = false
    fun fourKind(hand: String) = false
    fun threeKind(hand: String) = false

    fun twoPair(hand: String) = false
    fun onePair(hand: String) = false
    fun high(hand: String) = false


    fun part1(input: List<String>): Int {
        val idx = 0

        val data = input[idx].split(' ').splitNoBlanks()
        val hand = data[0]
        val rank = data[1]



        return -1
    }


    fun part2(input: List<String>): Int {
        return -1
    }


    "--- 1 ---------------------------".println()

    val raw1 = "32T3K 765\n" +
            "T55J5 684\n" +
            "KK677 28\n" +
            "KTJJT 220\n" +
            "QQQJA 483"
    val example1 = raw1.split('\n')
    check(part1(example1) == 6440)
    part1(input).println() // TODO SOLUTION day 7 part 1 =

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 0)
    part2(input).println() // TODO SOLUTION day 7 part 2 =

}
