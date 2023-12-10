package aoc23


import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException


// from: https://github.com/kotlin-hands-on/advent-of-code-kotlin-template/blob/main/src/Utils.kt
fun Any?.println() = println(this)


// from a String with \n\n to nothing
fun String.splitNoBlanks() = this.split("\\s+".toRegex()).filter { it.isNotBlank() }


fun List<String>.splitNoBlanks() = this.map { it.splitNoBlanks() }


fun List<String>.splitNoBlanks2Int() = this.map { it.splitNoBlanks().map { it.toInt() } }


// [0, 3, 6, 9, 12, 15] -> [3, 3, 3, 3, 3]
fun List<Int>.reduceIt(): List<Int> {
    var previous = this[0]
    return this.subList(1, this.size).map {
        val tmp = it - previous
        previous = it
        tmp
    }
}


// [2, 0, 3, 10] --> 5 (which is 10-3-0-2 = 5)
fun Iterable<Int>.subOfReversed(): Int {
    var sub: Int = 0
    for (element in this.reversed()) {
        sub = element - sub
    }
    return sub
}


fun List<Int>.allZeros() = this.all { it == 0 }


private val client = OkHttpClient()


private fun fetch(year: Int, day: Int): String {
    val request = Request.Builder()
        .url("https://adventofcode.com/$year/day/$day/input")
        .addHeader("Cookie", "session=${System.getenv("AOC_SESSION")}")
        .build()

    return client.newCall(request).execute().let { r ->
        if (!r.isSuccessful) throw IOException("Unexpected code $r")
        r.body!!.string()
    }
}


fun input(year: Int, day: Int): List<String> {
    val dir = "resources/$year/"
    File(dir).mkdirs()

    val f = File("$dir/${"%02d".format(day)}.txt") // converts 1 -> 01
    if (!f.exists()) {
            f.createNewFile()
        f.printWriter().use { out ->
            out.print(fetch(year, day))
        }
    }

    return f.readLines()
}


fun inputAndGroup(year: Int, day: Int): List<String> {
    val dir = "resources/$year/"
    File(dir).mkdirs()

    val f = File("$dir/${"%02d".format(day)}.txt") // converts 1 -> 01
    if (!f.exists()) {
        f.createNewFile()
        f.printWriter().use { out ->
            out.print(fetch(year, day))
        }
    }

    return f.readText().split("\n\n")
}
