package aoc22.utils

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt



/*





fun coord2Coordinates(coord: Coord) = Coordinates(coord.l.toDouble(), coord.c.toDouble())

fun coords2Route(start: Coord, end: Coord) = Route(coord2Coordinates(start), coord2Coordinates(end))

fun gridToRoute(grid: Grid): List<Route> {
    val routes = mutableListOf<Route>()

    grid.forEachIndexed { l, spots ->
        spots.forEachIndexed { c, spot ->
            val dirs = spot.dirs
            val topInfo = dirs[UP.ordinal]
            val downInfo = dirs[DOWN.ordinal]
            val leftInfo = dirs[LEFT.ordinal]
            val rightInfo = dirs[RIGHT.ordinal]

            if (topInfo.first) {
                routes.add(coords2Route(spot.coord, topInfo.second))
            }
            if (downInfo.first) {
                routes.add(coords2Route(spot.coord, downInfo.second))
            }
            if (leftInfo.first) {
                routes.add(coords2Route(spot.coord, leftInfo.second))
            }
            if (rightInfo.first) {
                routes.add(coords2Route(spot.coord, rightInfo.second))
            }
        }
    }

    return routes
}


fun main() {

    val routes = gridToRoute(grid)

    for (l in 0 until grid.size) {
        for (c in 0 until grid[l].size) {
            val result = routes.filter {
                it.a == coord2Coordinates(grid[l][c].coord)
            }
            val resultStr = result.map {
                it.b
            }

            println("($l $c  $resultStr)")
        }
    }


    val result = AlgorithmAStarImpl(routes)
        .findPath(
            begin = coord2Coordinates(start),
            end = coord2Coordinates(end)
        )

    val pathString = result.first.joinToString(separator = ", ") { "[${it.lat}, ${it.lon}]" }

    println("Result:")
    println("  Path: $pathString")
    println("  Cost: ${result.second}")

    val part1 = result.second.toInt()

}


    /*
    println("---")
    // COLUMNS
    print("    ")
    for (c in 0 until grid[0].size) {
        if (c < 10) {
            print(" ")
        }
        print("          $c ")
        print("         |")
    }
    println()

    // LINES
    for (l in 0 until grid.size) {
        if (l < 10) {
            print(" ")
        }
        print("$l | ")
        for (c in 0 until grid[l].size) {
            print(routes[l * c])
            if (c != grid[l].size - 1) {
                print(" | ")
            }
        }
        println()
    }
    */





 */


data class Coordinates(
    val lat: Double,
    val lon: Double
) : Graph.Vertex {
    override fun toString(): String {
        val a = (if (lat < 10) " " else "") + lat.toInt()
        val b = (if (lon < 10) " " else "") + lon.toInt()
        return "($a, $b)"
    }
}

data class Route(
    override val a: Coordinates,
    override val b: Coordinates
) : Graph.Edge<Coordinates> {
    val distance: Double
        get() {
            val dLon = abs(a.lon - b.lon)
            val dLat = abs(a.lat - b.lat)
            return sqrt(dLon.pow(2) + dLat.pow(2))
        }

    override fun toString() = "$a -> $b"}

class AlgorithmAStarImpl(edges: List<Route>) : AlgorithmAStar<Coordinates, Route>(edges) {
    override fun costToMoveThrough(edge: Route): Double {
        return edge.distance
    }

    override fun createEdge(from: Coordinates, to: Coordinates): Route {
        return Route(from, to)
    }
}

fun main() {
    val routes = listOf(
        Route(Coordinates(1.0, 1.0), Coordinates(1.0, 3.0)),
        Route(Coordinates(1.0, 1.0), Coordinates(5.0, 1.0)),
        Route(Coordinates(5.0, 1.0), Coordinates(2.0, 2.0)),
        Route(Coordinates(1.0, 3.0), Coordinates(2.0, 2.0))
    )

    val result = AlgorithmAStarImpl(routes)
        .findPath(
            begin = Coordinates(1.0, 1.0),
            end = Coordinates(2.0, 2.0)
        )

    val pathString = result.first.joinToString(separator = ", ") { "[${it.lat}, ${it.lon}]" }

    println("Result:")
    println("  Path: $pathString")
    println("  Cost: ${result.second}")
}
