package day06


import utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


fun myContains(str: String, start: Int, end: Int): Int {
    for (i in start until end) {
        val subStr = str.substring(i + 1, end)
        if (subStr.contains(str[i])) {
            return -1
        }
    }

    return end
}


fun findMarker(str: String, markerSize: Int): Int {
    var start = 0
    var end = markerSize

    while (end < str.length) {
        val marker = myContains(str, start, end)

        if (marker == -1) {
            start++
            end++
        } else {
            return marker
        }
    }

    return -1
}


fun main() {
    val lines = readLines(directory, "my.in")

    val part1 = lines
        .map {
            findMarker(it, 4)
        }

    println(part1)

    val part2 = lines
        .map {
            findMarker(it, 14)
        }

    println(part2)
}
