package aoc23


fun main() {
    val input = input(2023, 2)


    data class RGB(var r: Int = 0, var g: Int = 0, var b: Int = 0)


    fun cube2rgb(cube: List<String>): RGB {
        val value = cube[0].toInt()
        val color = cube[1]
        return when (color) {
            "red" -> RGB(r = value)
            "green" -> RGB(g = value)
            "blue" -> RGB(b = value)
            else -> error("should not pass")
        }
    }

    fun game2sets(game: String) = game.split(": ")[1].split("; ")

    fun set2cubes(set: String) = set.split(", ")

    fun game2rgbs(game: String) = game2sets(game)
        .map { set2cubes(it) }
        .map { cubes ->
            cubes.map { cube2rgb(it.split(" ")) }
        }

    // returns a List<RGB> with the values of all sets
    fun gameFinal(game: String) = game2rgbs(game).map { set ->
        val result = RGB()
        set.forEach { rgb ->
            result.r += rgb.r
            result.g += rgb.g
            result.b += rgb.b
        }
        result
    }

    fun validRGB(rgb: RGB) = rgb.r <= 12 && rgb.g <= 13 && rgb.b <= 14


    fun part1(input: List<String>): Int {
        var result = 0

        for (idx in input.indices) {
            if (gameFinal(input[idx]).all { validRGB(it) }) {
                result += idx + 1
            }
        }

        return result
    }


    fun part2(input: List<String>) = input.map {
        val max = RGB()

        gameFinal(it).forEach {
            max.r = Math.max(max.r, it.r)
            max.g = Math.max(max.g, it.g)
            max.b = Math.max(max.b, it.b)
        }

        max
    }.sumOf { it.r * it.g * it.b }


    "--- 1 ---------------------------".println()

    val raw1 = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    val example1 = raw1.split('\n')
    check(part1(example1) == 8)
    part1(input).println()

    "--- 2 ---------------------------".println()

    val example2 = raw1.split('\n')
    check(part2(example2) == 2286)
    part2(input).println()

}
