package aoc23


fun main() {
    val input = input(2023, 3)


    fun emptyLine(line: String) = ".".repeat(line.length)


    fun Char.isSymbol() = !this.isDigit() && this != '.'


    fun p1(p: String, c: String, n: String): List<Int> {
        // (p)revious, (c)urrent, and (n)ext

        var numberStr = ""
        var isPart = false
        val numbers = mutableListOf<Int>()

        for (i in c.indices) {
            val ch = c[i]

            if (!isPart) {
                isPart = p[i].isSymbol() || ch.isSymbol() || n[i].isSymbol()
            }

            if (ch.isDigit()) {
                numberStr += ch
            } else {
                if (numberStr.isNotEmpty() && isPart) {
                    numbers += numberStr.toInt()
                }
                numberStr = ""
                if (ch == '.') {
                    isPart = p[i].isSymbol() || ch.isSymbol() || n[i].isSymbol()
                }
            }
        }

        return numbers
    }


    fun part1(input: List<String>): Int {
        val result = Array(input.size) { emptyList<Int>() }

        for (idx in input.indices) {
            val c = input[idx]
            val p = if (idx == 0) emptyLine(c) else input[idx - 1]
            val n = if (idx == input.size - 1) emptyLine(c) else input[idx + 1]

            result[idx] = p1(p, c, n)
        }

        return result.sumOf { it.sum() }
    }


    val gears = HashMap<Pair<Int, Int>, List<Int>>()

    fun p2(p: String, c: String, n: String, currentLine: Int): List<Int> {
        // (p)revious, (c)urrent, and (n)ext

        var numberStr = ""
        var isPart = false
        val numbers = mutableListOf<Int>()

        for (i in c.indices) {
            val ch = c[i]

            if (!isPart) {
                isPart = p[i].isSymbol() || ch.isSymbol() || n[i].isSymbol()
            }

            if (ch.isDigit()) {
                numberStr += ch
            } else {
                if (numberStr.isNotEmpty() && isPart) {
                    val number = numberStr.toInt()
                    numbers += number

                    val k = Pair(currentLine, i)
                    if (gears.containsKey(k)) {
                        gears[k] = emptyList()
                    }
                    println("--> $k --> $number")
//                    gears[k]?.addLast(number)
                }
                numberStr = ""
                if (ch == '.') {
                    isPart = p[i].isSymbol() || ch.isSymbol() || n[i].isSymbol()
                }
            }
        }

//        numbers.println()
        gears.println()

        return numbers
    }



    fun part2(input: List<String>): Int {
        val result = Array(input.size) { emptyList<Int>() }

        for (idx in input.indices) {
            val c = input[idx]
            val p = if (idx == 0) emptyLine(c) else input[idx - 1]
            val n = if (idx == input.size - 1) emptyLine(c) else input[idx + 1]

            result[idx] = p2(p, c, n, idx)
        }

        return result.sumOf { it.sum() }
    }


    "--- 1 ---------------------------".println()

    val raw1 = "467..114..\n" +
            "...*......\n" +
            "..35..633.\n" +
            "......#...\n" +
            "617*......\n" +
            ".....+.58.\n" +
            "..592.....\n" +
            "......755.\n" +
            "...\$.*....\n" +
            ".664.598.."
    val example1 = raw1.split('\n')
    check(part1(example1) == 4361)
    part1(input).println()

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 467835)
    part2(input).println()

}
