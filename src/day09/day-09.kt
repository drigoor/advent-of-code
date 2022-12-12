package day09


import utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


typealias Coord = Pair<Int, Int> // (x, y)


interface Cmd {
    fun exec(coord: Coord): Coord
}

object U : Cmd {
    override fun exec(coord: Coord) = Coord(coord.first, coord.second - 1)
    override fun toString() = "U"
}

object D : Cmd {
    override fun exec(coord: Coord) = Coord(coord.first, coord.second + 1)
    override fun toString() = "D"
}

object L : Cmd {
    override fun exec(coord: Coord) = Coord(coord.first - 1, coord.second)
    override fun toString() = "L"
}

object R : Cmd {
    override fun exec(coord: Coord) = Coord(coord.first + 1, coord.second)
    override fun toString() = "R"
}


val cmdsMap = mapOf("U" to U, "D" to D, "L" to L, "R" to R)


fun getTailCmds(head: Coord, tail: Coord): List<Cmd> {
    if (head == tail) { // same thing that head and tail are in the same line + column
        return listOf()
    }

    val (xHead, yHead) = head
    val (xTail, yTail) = tail

    val sameColumn = xTail == xHead
    val sameLine = yTail == yHead
    val headIsTop = yTail - 1 == yHead
    val headIsDown = yTail + 1 == yHead
    val headIsLeft = xTail - 1 == xHead
    val headIsRight = xTail + 1 == xHead

    if ((sameColumn && (headIsTop || headIsDown)) ||
        (sameLine && (headIsLeft || headIsRight)) ||
        (headIsTop && (headIsLeft || headIsRight)) ||
        (headIsDown && (headIsLeft || headIsRight))
    ) {
        return listOf()
    }

    val headAtTop = yHead < yTail
    val headAtDown = yHead > yTail
    val headhAtLeft = xHead < xTail
    val headAtRigth = xHead > xTail

    val cmds: MutableList<Cmd> = mutableListOf()

    if (headAtTop) {
        cmds += U
    }
    if (headAtDown) {
        cmds += D
    }
    if (headhAtLeft) {
        cmds += L
    }
    if (headAtRigth) {
        cmds += R
    }

    return cmds
}


// returns the positions that tail was/is
fun execute(head: Coord, tail: Coord, cmds: List<Cmd>): List<Coord> {
    if (DEBUG) {
        printGrid(head, tail)
        println()
    }

    var currentHead = head
    var currentTail = tail
    val result = mutableListOf(tail)

    for (cmd in cmds) {
        currentHead = cmd.exec(currentHead)
        val tailCmds = getTailCmds(currentHead, currentTail)

        if (tailCmds.isNotEmpty()) {
            currentTail = tailCmds
                .fold(currentTail) { currentCoord, nextCmd ->
                    nextCmd.exec(currentCoord)
                }
            result += currentTail
        }

        if (DEBUG) {
            printGrid(currentHead, currentTail)
            println()
        }
    }

    return result
}


fun execute2(head: Coord, tail: Coord, coords: List<Coord>): List<Coord> {
    if (DEBUG) {
        printGrid(head, tail)
        println()
    }

    var currentTail = tail
    val result = mutableListOf(tail)

    for (currentHead in coords) {
        val tailCmds = getTailCmds(currentHead, currentTail)

        if (tailCmds.isNotEmpty()) {
            currentTail = tailCmds
                .fold(currentTail) { currentCoord, nextCmd ->
                    nextCmd.exec(currentCoord)
                }
            result += currentTail
        }

        if (DEBUG) {
            printGrid(currentHead, currentTail)
            println()
        }
    }

    return result
}


fun printGrid(head: Coord, tail: Coord) {
    for (y in 0 until NUMBER_OF_LINES) {
        for (x in 0 until NUMBER_OF_COLUMNS) {
            val coord = Coord(x, y)

            if (coord == head) {
                print(HEAD_STR)
            } else if (coord == tail) {
                print(TAIL_STR)
            } else if (coord == ORIGIN) {
                print("s")
            } else {
                print(".")
            }
        }
        println()
    }
}


// PART 1
const val NUMBER_OF_LINES = 5
const val NUMBER_OF_COLUMNS = 6
val ORIGIN = Coord(0, 4)


//// PART 2
//const val NUMBER_OF_LINES = 21
//const val NUMBER_OF_COLUMNS = 26
//val ORIGIN = Coord(11, 15)


var DEBUG = false
var HEAD_STR = "H"
var TAIL_STR = "T"


fun main() {
    val lines = readLines(directory, "my.in")
    val cmds = lines
        .flatMap {
            val input = it.split(" ")
            val qtd = input[1].toInt()
            val cmd = cmdsMap.getOrDefault(input[0], U) // the default should never happen...
            List<Cmd>(qtd) { cmd }
        }

    var tailCoords = execute(ORIGIN, ORIGIN, cmds)

    val part1 = tailCoords.distinct().count()

    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
//    DEBUG = true
//    HEAD_STR = "2"
//    TAIL_STR = "3"
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)
    tailCoords = execute2(tailCoords.first(), tailCoords.first(), tailCoords)

    val part2 = tailCoords.distinct().count()

    printResults("13", part1, part2) // part1 -- 5513 part2 -- 2427  (in example_part2.txt  result is 36)
}
