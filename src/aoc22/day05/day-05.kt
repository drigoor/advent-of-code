package aoc22.day05


import aoc22.utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun parseStackLine(line: String): Array<String> {
    val data = mutableListOf<String>()

//    [S] [Z] [M] [T] [P] [C] [D] [C] [D]
//     1   2   3   4   5   6   7   8   9
//     ^           ^...^
//    index         step
    var index = 1
    val step = 4

    while (index < line.length) {
        //    for (i in 0 until line.length) {
        data.add(line[index].toString())
        index += step
    }

    return data.toTypedArray()
}


data class Move(val nrOfMoves: Int, val origin: Int, val destination: Int)


fun parseMoveLine(line: String): Move {
    val data = line.split(" ")

    val nrOfMoves = data[1].toInt()
    val origin = data[3].toInt()
    val destination = data[5].toInt()

    return Move(nrOfMoves, origin, destination)
}


fun processMovePart1(move: Move, stacks: Array<MutableList<String>>, part1: Boolean = true) {
    if (part1) {
        for (i in 0 until move.nrOfMoves) {
            val crate = stacks[move.origin - 1].removeFirst()

            stacks[move.destination - 1].add(0, crate)
        }
    } else {
        val crates = stacks[move.origin - 1].take(move.nrOfMoves)

        for (j in 0 until move.nrOfMoves) {
            stacks[move.origin - 1].removeFirst()
        }

        stacks[move.destination - 1].addAll(0, crates)
    }
}


fun processMovePart2(move: Move, stacks: Array<MutableList<String>>) {
    if (move.nrOfMoves == 1) {
        processMovePart1(move, stacks, true)
    } else {
        processMovePart1(move, stacks, false)
    }
//    processMovePart1
//
//    for (i in 0 until move.nrOfMoves) {
//        val crate = stacks[move.origin - 1].removeFirst()
//
//        stacks[move.destination - 1].add(0, crate)
//    }
}


fun main() {
    val lines = readLines(directory, "my.in")

    val data: MutableList<MutableList<String>> = mutableListOf(mutableListOf())

    lines.forEach {
        if (it.isBlank()) {
            data.last().removeLast() // remove legend " 1 2 3 "
            data.add(mutableListOf())
        } else {
            data.last().add(it)
        }
    }

    val stacksData: List<Array<String>> = data
        .first()
        .map {
            parseStackLine(it)
        }

    val numberOfStacks = stacksData
        .map { it.size }
        .max()


    val stacks: Array<MutableList<String>> = Array(numberOfStacks) { mutableListOf() }

    stacksData
        .map {
            for (i in 0 until it.size) {
                if (it[i].isNotBlank()) {
                    stacks[i].add(it[i])
                }
            }
        }

    val moves = data[1]
        .map {
            parseMoveLine(it)
        }

    moves
        .map {
            processMovePart2(it, stacks)
        }


    // TODO
    // TODO
    // TODO é preciso copiar os stacks para a part 2
    // TODO
    // TODO

    val part1 = stacks
        .map { it.first() }
        .joinToString("")

    val part2 = "-"

    printResults("CMZ", part1, part2)
}
