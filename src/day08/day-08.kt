package day08


import utils.*


val directory = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun nrVisibleInEdge(grid: List<List<Int>>) = grid[0].size * 2 + (grid.size - 2) * 2


fun isVisibleFromLeft(grid: List<List<Int>>, line: Int, column: Int) =
    (0 until column).find { grid[line][it] >= grid[line][column] } == null


fun isVisibleFromRight(grid: List<List<Int>>, line: Int, column: Int) =
    ((column + 1) until grid[line].size).find { grid[line][it] >= grid[line][column] } == null


fun isVisibleFromTop(grid: List<List<Int>>, line: Int, column: Int) =
    (0 until line).find { grid[it][column] >= grid[line][column] } == null


fun isVisibleFromBottom(grid: List<List<Int>>, line: Int, column: Int) =
    ((line + 1) until grid[line].size).find { grid[it][column] >= grid[line][column] } == null


fun isVisible(grid: List<List<Int>>, line: Int, column: Int) =
    isVisibleFromTop(grid, line, column) ||
            isVisibleFromBottom(grid, line, column) ||
            isVisibleFromLeft(grid, line, column) ||
            isVisibleFromRight(grid, line, column)


fun nrVisibleInterior(grid: List<List<Int>>): Int {
    var result = 0

    for (l in 1 until grid.size - 1) {
        for (c in 1 until grid[l].size - 1) {
            if (isVisible(grid, l, c)) {
                result++
            }
        }
    }

    return result
}


fun viewDistanceLeft(grid: List<List<Int>>, line: Int, column: Int): Int {
    var result = 0

    for (c in column - 1 downTo 0) {
        result++
        if (grid[line][c] >= grid[line][column]) {
            return result
        }
    }

    return result
}


fun viewDistanceRight(grid: List<List<Int>>, line: Int, column: Int): Int {
    var result = 0

    for (c in column + 1 until grid[line].size) {
        result++
        if (grid[line][c] >= grid[line][column]) {
            return result
        }
    }

    return result
}


fun viewDistanceTop(grid: List<List<Int>>, line: Int, column: Int): Int {
    var result = 0

    for (l in line - 1 downTo 0) {
        result++
        if (grid[l][column] >= grid[line][column]) {
            return result
        }
    }

    return result
}


fun viewDistanceBottom(grid: List<List<Int>>, line: Int, column: Int): Int {
    var result = 0

    for (l in (line + 1) until grid[line].size) {
        result++
        if (grid[l][column] >= grid[line][column]) {
            return result
        }
    }

    return result
}


fun viewDistance(grid: List<List<Int>>, line: Int, column: Int) =
    viewDistanceTop(grid, line, column) *
            viewDistanceBottom(grid, line, column) *
            viewDistanceLeft(grid, line, column) *
            viewDistanceRight(grid, line, column)


fun maxViewDistanceInterior(grid: List<List<Int>>): Int {
    var result = 0

    for (l in 1 until grid.size - 1) {
        for (c in 1 until grid[l].size - 1) {
            val visibleDistance = viewDistance(grid, l, c)

            if (visibleDistance > result) {
                result = visibleDistance
            }
        }
    }

    return result
}


fun main() {
    val lines = readLines(directory, "my.in")

    val grid = lines.map { it.toCharArray().map { it.digitToInt() } }

    val part1 = nrVisibleInterior(grid) + nrVisibleInEdge(grid)

    val part2 = maxViewDistanceInterior(grid)

    printResults("1695", part1, part2)
}
