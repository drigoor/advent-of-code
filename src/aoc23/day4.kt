package aoc23


import kotlin.math.pow


fun main() {
    val input = input(2023, 4)


    fun p1(card: String): List<Int> {
        val (winNumbers, numbers) = card.split(":")[1].split("|").splitNoBlanks()

        return numbers.filter { it in winNumbers }.map { it.toInt() }
    }


    fun part1(input: List<String>): Int {
        return input.sumOf {
            val result = p1(it)

            when (result.size) {
                0 -> 0
                1 -> 1
                else -> 2.0.pow((result.size - 1).toDouble()).toInt()
            }
        }
    }


    fun processRound(cardsToMatchingNumbers: List<Int>, cardsToProcess: List<Int>): List<Int> {
        val size = cardsToMatchingNumbers.size
        val newCardsToProcess = mutableListOf<Int>()

        for (idx in cardsToProcess) {
            val count = cardsToMatchingNumbers[idx]

            for (i in 1..count) {
                val newCardIdx = idx + i

                if (newCardIdx < size) {
//                    newCardsToProcess.addLast(newCardIdx)
                }
            }
        }

        return newCardsToProcess
    }


    fun part2(input: List<String>): Int {
        val cardsToMatchingNumbers = mutableListOf<Int>() // indica para cada cartão quantos números tem iguais
        var cardsToProcess = mutableListOf<Int>()

        for (idx in input.indices) {
            val count = p1(input[idx]).count()
//            cardsToMatchingNumbers.addLast(count)
            if (count != 0) {
                cardsToProcess.add(idx)
            }
        }

        val cardsProcessed = mutableListOf<Int>()

        while (cardsToProcess.isNotEmpty()) {
            cardsProcessed.addAll(cardsToProcess)
            cardsToProcess.println()
            "-------------------".println()

            cardsToProcess = processRound(cardsToMatchingNumbers, cardsToProcess).toMutableList()
        }

        cardsProcessed.println()

        return cardsProcessed.size
    }


    "--- 1 ---------------------------".println()

    val raw1 = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
//    val raw1 = "Card   3: 60 78 77 44 62 54 94 50 32 11 |  2  6 89 50 11 60 57 53 71 44 47 62 49 42 73 78 77 54 99 29 35 94 32 68 74"
//    val raw1 = "Card 106:  3  4 83 71 10 23 75 50 17 53 | 57 54  9 50 33 51 10  7 14 82 46 13  3 34 52 87 67 48 61 28 64 30 78 92 38"
//    val raw1 = "Card 166:  9 80 14 37 32 95  8 90 89 97 |  1 17 48 12 63 68 62 50 10 71 83 84  3 44 16 58 38 97  9 53 91 25 14 79 37"
//    val raw1 = "Card  15: 78 68 34 44 80 25 70 98 71 99 | 80 73 44 20 38 34 70 57 75 77 98 40 55 71 19 67 32 10 60 94 62 68 22 13 66\n" +
//                "Card  16: 19 47  8 42 85 14 35 24 95 61 | 51 33 98 48 14 25 62 40 37 77 91  8  6 35 63 20 11 97 27 89  1 72 57 31 78"
    val example1 = raw1.split('\n')
    "________________________________________-".println()
    check(part1(example1) == 13)
    part1(input).println() // TODO SOLUTION day 4 part 1 = 23941

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 30)
    part2(input).println() // TODO SOLUTION day 4 part 2 = ???

}
