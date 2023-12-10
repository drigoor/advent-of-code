package aoc23


fun main() {
    val input = input(2023, 8)


    /*

    // Este código só lida com AAA, BBB, etc
    // tem que lidar com AGD, etc

    fun reduceLetters(str: String): Int {
        val ch = str[0]

        assert(str.all { it == ch })

        return ch.code - 'A'.code
    }


    // recebe "AAA" -> 0, "BBB" -> 1
    fun reduceLettersPair(str: String): Pair<Int, Int> {
        val (left, right) = str.split(", ")

        return Pair(reduceLetters(left), reduceLetters(right))
    }


    // "(BBB, CCC)" -> (1, 2)
    fun parseMapEntry(str: String): Pair<Int, Pair<Int, Int>> {
        val (node, leftAndRight) = str.split(" = ")
        val pair = leftAndRight.substring(1, leftAndRight.length - 1)

        return Pair(reduceLetters(node), reduceLettersPair(pair))
    }


    // "AAA = (BBB, CCC)
    // BBB = (DDD, EEE)"
    // ->
    // { 0 -> (1, 2), 1 -> (3, 4)
    fun raw2map(raw: List<String>): Map<Int, Pair<Int, Int>> {
        val map: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

        raw.map {
            val (entry, leftAndRight) = parseMapEntry(it)
            map[entry] = leftAndRight
        }

        return map
    }


    fun part1(input: List<String>): Int {
        val directions = input[0] // "RL"
        val rawMap = input.subList(2, input.size) // listOf("AAA = (BBB, CCC)", ...)
        val map = raw2map(rawMap)

        var current = reduceLetters(rawMap[0].substring(0, 2))
        val end = reduceLetters("ZZZ")
        var count = 0

        while (true) {
            for (dir in directions) {
                current = when (dir) {
                    'L' -> map[current]?.first!!
                    'R' -> map[current]?.second!!
                    else -> error("Neither L or R in directions")
                }
                count++
                if (current == end) {
                    return count
                }
            }
        }
    }
     */


    fun rawEntries2map(raw: List<String>): Map<String, Pair<String, String>> {
        val map: MutableMap<String, Pair<String, String>> = mutableMapOf()

        raw.forEach {
            val (current, leftAndRight) = it.split(" = ")
            val (left, right) = leftAndRight.substring(1, leftAndRight.length - 1).split(", ")
            map[current] = Pair(left, right)
        }

        return map
    }


    fun part1(input: List<String>): Int {
        val directions = input[0] // "RL"
        val rawMap = input.subList(2, input.size) // listOf("AAA = (BBB, CCC)", ...)
        val map = rawEntries2map(rawMap)

        var current = "AAA"
        val end = "ZZZ"
        var count = 0

        while (true) {
            for (dir in directions) {
                current = when (dir) {
                    'L' -> map[current]?.first!!
                    'R' -> map[current]?.second!!
                    else -> error("Neither L or R in directions")
                }
                count++
                if (current == end) {
                    return count
                }
            }
        }
    }


    fun findAllAs(input: List<String>): List<String> {
        val rawMap = input.subList(2, input.size) // listOf("AAA = (BBB, CCC)", ...)
        val nodes = rawMap.map { it.split(" = ")[0] }
        val nodes2 = nodes.filter { it[2] == 'A' }

        return nodes2
    }


    fun isEnd(current: List<String>) = current.all { it[2] == 'Z' }


    fun move(direction: Char, currentNodes: List<String>, map: Map<String, Pair<String, String>>) =
        currentNodes.map {
            when (direction) {
                'L' -> {
                    map[it]?.first!!
                }

                'R' -> {
                    map[it]?.second!!
                }

                else -> error("Neither L or R in directions")
            }
        }


    fun part2(input: List<String>): Int {
        val directions = input[0] // "RL"
        val rawMap = input.subList(2, input.size) // listOf("AAA = (BBB, CCC)", ...)
        val map = rawEntries2map(rawMap)

        var current = findAllAs(input)
        var count = 0

        while (true) {
            for (dir in directions) {
                current = move(dir, current, map)
                count++
                if (isEnd(current)) {
                    return count
                }
            }
        }
    }

    /*
        "--- 1 ---------------------------".println()
        val raw1 = "RL\n" +
                "\n" +
                "AAA = (BBB, CCC)\n" +
                "BBB = (DDD, EEE)\n" +
                "CCC = (ZZZ, GGG)\n" +
                "DDD = (DDD, DDD)\n" +
                "EEE = (EEE, EEE)\n" +
                "GGG = (GGG, GGG)\n" +
                "ZZZ = (ZZZ, ZZZ)"
        val example1 = raw1.split('\n')
        check(part1(example1) == 2)
        val raw1a = "LLR\n" +
                "\n" +
                "AAA = (BBB, BBB)\n" +
                "BBB = (AAA, ZZZ)\n" +
                "ZZZ = (ZZZ, ZZZ)"
        val example1a = raw1a.split('\n')
        check(part1(example1a) == 6)
        part1(input).println() // TODO SOLUTION day 0 part 1 = 17621
    */

    "--- 2 ---------------------------".println()
    val raw2 = "LR\n" +
            "\n" +
            "11A = (11B, XXX)\n" +
            "11B = (XXX, 11Z)\n" +
            "11Z = (11B, XXX)\n" +
            "22A = (22B, XXX)\n" +
            "22B = (22C, 22C)\n" +
            "22C = (22Z, 22Z)\n" +
            "22Z = (22B, 22B)\n" +
            "XXX = (XXX, XXX)"
    val example2 = raw2.split('\n')
    check(part2(example2) == 6)
    part2(input).println() // TODO SOLUTION day 0 part 2 =
}
