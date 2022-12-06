package day03


import utils.*


val directory = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


const val LOWER = 'a'.code - 1
const val UPPER = 'A'.code - 27


fun priority(ch: Char) = ch.code - if (ch.isLowerCase()) LOWER else UPPER


fun main() {
    val lines = readLines(directory,"my.in")

    val part1 = lines
        .map {
            val half = it.length / 2
            val a = it.substring(0, half)
            val b = it.substring(half, it.length)
            a.toCharArray().intersect(b.toCharArray().toList())
        }
        .fold(0) { total, next ->
            total + priority(next.first())
        }

    val part2 = lines
        .chunked(3)
        .map {
            val a = it[0].toCharArray()
            val b = it[1].toCharArray().toList()
            val c = it[2].toCharArray().toList()

            priority(a.intersect(b).intersect(c).first())
        }
        .sum()

    printResults("157", part1, part2)
}
