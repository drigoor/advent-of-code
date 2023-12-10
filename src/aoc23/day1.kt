package aoc23


fun main() {

    /*
    On each line, the calibration value can be found by combining the first
    digit and the last digit (in that order) to form a single two-digit number.
     */
    fun part1(input: List<String>): Int =
        input.map {
            val n = it.find { it.isDigit() }?.digitToInt() ?: 0
            val m = it.findLast { it.isDigit() }?.digitToInt() ?: 0

            "$n$m".toInt()
        }.sum()


    fun lixo(s: String) {
        val digits = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        var digitsN = 0

        for (i in s.indices) {
            when (s[i]) {
                in '1'..'9' -> {
                    digitsN = 0
                }
            }
        }

    }

    fun part2New(input: List<String>): List<String> {
        /*

        TODO 2023 day 1 -- parte 2 alternativa que tem problemas...

        não serve inteiramente porque se subtituirmos 1º o two antes do eight, este valor não dá:
        eightwoaois9
         */
        val digitsMap = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )

        return input.map {
            var current = it
            digitsMap.forEach { (k, v) ->
                current = current.replace(k, v)
            }
            current
        }

    }

    /*
    find the real first and last digit on each line
     */
    fun part2(input: List<String>): Int {
        val digits1 = mapOf(
            "0" to 0,        // not in the puzzle, but its ok - just to transform correctly to a number if no digit was found
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9
        )
        val digits1Keys = digits1.keys

        val digits2 = mapOf(
            "zero" to 0,        // not in the puzzle, but its ok - just to transform correctly to a number if no digit was found
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )
        val digits2Keys = digits2.keys

        return input.map {
            val n = it.findAnyOf(digits1Keys)
            val m = it.findLastAnyOf(digits1Keys)

            val a = it.findAnyOf(digits2Keys)
            val b = it.findLastAnyOf(digits2Keys)


            val x =
                if (n == null) {
                    if (a == null) {
                        0
                    } else {
                        digits2[a.second]
                    }
                } else {
                    if (a == null) {
                        digits1[n.second]
                    } else {
                        if (n.first < a.first) digits1[n.second] else digits2[a.second]
                    }
                }

            val y =
                if (m == null) {
                    if (b == null) {
                        0
                    } else {
                        digits2[b.second]
                    }
                } else {
                    if (b == null) {
                        digits1[m.second]
                    } else {
                        if (m.first > b.first) digits1[m.second] else digits2[b.second]
                    }
                }

            "$x$y".toInt()
        }.sum()
    }

    val input = input(2023, 1)

    "--- 1 ---------------------------".println()

    val raw1 = "1abc2\n" +
            "pqr3stu8vwx\n" +
            "a1b2c3d4e5f\n" +
            "treb7uchet"
    val example1 = raw1.split('\n')
    check(part1(example1) == 142)
    part1(input).println()

    "--- 2 ---------------------------".println()

    val raw2 = "two1nine\n" +
            "eightwothree\n" +
            "abcone2threexyz\n" +
            "xtwone3four\n" +
            "4nineeightseven2\n" +
            "zoneight234\n" +
            "7pqrstsixteen"
    val example2 = raw2.split('\n')
    check(part2(example2) == 281)
    part2(input).println()


    part2New(example1).println()
    "--------------".println()
    part2New(example2).println()
    part1(part2New(example2)).println()

    /*

    TODO 2023 day 1 alternativa à parte 2

    ALTERNATIVA (from: https://github.com/Orimalc/AoC-2023-Kotlin/blob/main/src/Day01.kt)

    na parte 2 converter os numeros para extenso:
    4nineeightseven2 --> fournineeightseventwo

    Parece-me muito mais simples...

     */

}
