package day12


import utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/

const val UP = 0
const val DOWN = 1
const val LEFT = 2
const val RIGHT = 3

val directions = arrayOf("^", "v", "<", ">") // Up, Down, Left, Right

val abc = "abcdefghijklmnopqrstuvwxyz".toCharArray()

typealias Grid = Array<Array<Spot>>
typealias Coord = Pair<Int, Int>

lateinit var start: Coord
lateinit var end: Coord


var DEBUG = false

// dirs - means climbing with a given THREESHOLD or in staying in a plain (see function isClimbingOrPlain())
data class Spot(val elevation: Int, val coord: Coord, val dirs: Array<Boolean> = Array(4) { false }, var visited: Boolean = false) {
    override fun toString() = if (DEBUG) {
        "" + coord + " " + abc[elevation] + " " + dirs.contentToString()
    } else {
        elevation.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Spot

        if (elevation != other.elevation) return false
        if (!dirs.contentEquals(other.dirs)) return false
        if (visited != other.visited) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elevation
        result = 31 * result + dirs.contentHashCode()
        result = 31 * result + visited.hashCode()
        return result
    }
}


data class Step(val value: Int) {
    override fun toString() = directions[value]
}


fun parseGrid(lines: List<String>): Grid {
    return lines.mapIndexed { l, s ->
        s.mapIndexed { c, v ->
            val coord = Coord(l, c)

            when (v) {
                'S' -> {
                    start = Coord(l, c)
                    Spot(abc.indexOf('a'), coord)
                }

                'E' -> {
                    end = Coord(l, c)
                    Spot(abc.indexOf('z'), coord)
                }

                else -> Spot(abc.indexOf(v), coord)
            }
        }.toTypedArray()
    }.toTypedArray()
}


const val THRESHOLD = 1


fun isClimbingOrPlain(startElevation: Int, endElevation: Int) = (startElevation + THRESHOLD) == endElevation || startElevation == endElevation


fun isDirTopOk(grid: Grid, l: Int, c: Int) =
    (l in 1 until grid.size) && (c in 0 until grid[0].size) && isClimbingOrPlain(grid[l][c].elevation, grid[l - 1][c].elevation)


fun isDirDownOk(grid: Grid, l: Int, c: Int) =
    (l in 0 until grid.size - 1) && (c in 0 until grid[0].size) && isClimbingOrPlain(grid[l][c].elevation, grid[l + 1][c].elevation)


fun isDirLeftOk(grid: Grid, l: Int, c: Int) =
    (l in 0 until grid.size) && (c in 1 until grid[0].size) && isClimbingOrPlain(grid[l][c].elevation, grid[l][c - 1].elevation)


fun isDirRightOk(grid: Grid, l: Int, c: Int) =
    (l in 0 until grid.size) && (c in 0 until grid[0].size - 1) && isClimbingOrPlain(grid[l][c].elevation, grid[l][c + 1].elevation)


fun fillSpotDirections(grid: Grid) {
    grid.forEachIndexed { l, spots ->
        spots.forEachIndexed { c, spot ->
            val dirs = spot.dirs

            dirs[UP] = isDirTopOk(grid, l, c)
            dirs[DOWN] = isDirDownOk(grid, l, c)
            dirs[LEFT] = isDirLeftOk(grid, l, c)
            dirs[RIGHT] = isDirRightOk(grid, l, c)
        }
    }
}


fun main() {
    val lines = readLines(directory, "example.in")

    val grid = parseGrid(lines)

    fillSpotDirections(grid)

    if (DEBUG) {
        println(grid.contentDeepToString())

        grid.forEachIndexed { l, spots ->
            spots.forEachIndexed { c, spot ->
                println(spot)
            }
        }
    }

    val part1 = 42

    val part2 = 42

    printResults("", part1, part2)
}
