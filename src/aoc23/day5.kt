package aoc23


/**
 *
 * Para este dia tive que remover a última linha dos dados de entrada....
 *
 * Devia limpar isso no final do ficheiro...
 *
 */

fun main() {
    val input = inputAndGroup(2023, 5)


    data class E(val r: LongRange, val d: Long)


    fun line2Encoding(numbers: List<Long>): E {
        val dst = numbers[0]
        val src = numbers[1]
        val delta = dst - src
        val length = numbers[2]

        return E(src until src + length, delta)
    }


    fun map2Encodings(map: List<String>) =
        map.subList(1, map.size).map { line2Encoding(it.splitNoBlanks().map { it.toLong() }) }


    fun fromTo(from: Long, map: List<E>): Long {
        val e = map.find { from in it.r }

        return if (e != null) from + e.d else from
    }


    fun fromSeedToLocation(seed: Long, maps: List<List<E>>): Long {
        var current = seed
        for (map in maps) {
            current = fromTo(current, map)
        }
        return current
    }

    fun p1(seeds: List<Long>, listMaps: List<List<E>>): List<Long> {
        val result = mutableListOf<Long>()

        for (seed in seeds) {
//            result.addLast(fromSeedToLocation(seed, listMaps))
        }

        return result
    }


    fun part1(input: List<String>): Long {
        /*
        val data = input.map { it.split("\n") }
        val seeds = data[0][0].split(":")[1].splitNoBlanks().map { it.toLong() }
        val listMaps = data.subList(1, data.size).map { map2Encodings(it) }
        val result = mutableListOf<Long>()

        for (seed in seeds) {
            result.addLast(fromSeedToLocation(seed, listMaps))
        }

        return result.min()
        */

        val data = input.map { it.split("\n") }
        val seeds = data[0][0].split(":")[1].splitNoBlanks().map { it.toLong() }
        val listMaps = data.subList(1, data.size).map { map2Encodings(it) }

        return p1(seeds, listMaps).min()
    }

    fun p2(seeds: List<Long>): List<Long> {
        val itr = seeds.iterator()
        val result = mutableListOf<Long>()

        while (itr.hasNext()) {
            val start = itr.next()
            val length = itr.next()
            val end = start + length

            for (i in start until end) {
//                result.addLast(i)
            }
        }

        return result
    }

    fun part2(input: List<String>): Long {
        val data = input.map { it.split("\n") }
        val seedsRange = data[0][0].split(":")[1].splitNoBlanks().map { it.toLong() }
        val seeds = p2(seedsRange)
        val listMaps = data.subList(1, data.size).map { map2Encodings(it) }

        return p1(seeds, listMaps).min()
    }


    "--- 1 ---------------------------".println()

    val raw1 = "seeds: 79 14 55 13\n" +
            "\n" +
            "seed-to-soil map:\n" +
            "50 98 2\n" +
            "52 50 48\n" +
            "\n" +
            "soil-to-fertilizer map:\n" +
            "0 15 37\n" +
            "37 52 2\n" +
            "39 0 15\n" +
            "\n" +
            "fertilizer-to-water map:\n" +
            "49 53 8\n" +
            "0 11 42\n" +
            "42 0 7\n" +
            "57 7 4\n" +
            "\n" +
            "water-to-light map:\n" +
            "88 18 7\n" +
            "18 25 70\n" +
            "\n" +
            "light-to-temperature map:\n" +
            "45 77 23\n" +
            "81 45 19\n" +
            "68 64 13\n" +
            "\n" +
            "temperature-to-humidity map:\n" +
            "0 69 1\n" +
            "1 0 69\n" +
            "\n" +
            "humidity-to-location map:\n" +
            "60 56 37\n" +
            "56 93 4"
    val example1 = raw1.split("\n\n")
    check(part1(example1) == 35L)
    part1(input).println() // TODO SOLUTION day 5 part 1 = 309796150

    "--- 2 ---------------------------".println()

    check(part2(example1) == 46L)
    part2(input).println() // TODO SOLUTION day 5 part 2 =
}
