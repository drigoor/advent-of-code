package aoc22.day07


import aoc22.utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


enum class ThingType {
    DIR,
    FILE
}


fun thingTypeToString(thing: Thing) = when (thing.type) {
    ThingType.DIR -> "(dir, size=${thing.size}))"
    ThingType.FILE -> "(file, size=${thing.size})"
}


fun thingToString(thing: Thing, indentation: Int = 0): String {
    val result = StringBuilder()
    val ln = System.lineSeparator()

    result
        .append("${" ".repeat(indentation)}- ${thing.name} ${thingTypeToString(thing)}")
        .append(if (thing.contents.isEmpty()) "" else ln)
        .append(thing.contents.joinToString(separator = ln, transform = { thingToString(it, indentation + 2) }))

    return result.toString()
}


data class Thing(val type: ThingType, var name: String, var size: Int) {
    lateinit var root: Thing
    val contents: MutableList<Thing> = mutableListOf() // this keeps the order between directories and files (not great)

    override fun toString() = thingToString(this)
}


fun dir(name: String) = Thing(ThingType.DIR, name, 0)


fun file(name: String, size: Int) = Thing(ThingType.FILE, name, size)


fun changeDirectory(current: Thing, name: String) =
    current.contents
        .filter { it.type == ThingType.DIR }
        .find { it.name == name } ?: current // if the directory does not exist, it remains in the currentDir


fun calculateAndUpdateTotalSize(current: Thing): Int {
    if (current.size == 0) {
        current.size = current.contents.fold(0) { total, next -> total + calculateAndUpdateTotalSize(next) }
    }
    return current.size
}


fun main() {
    val lines = readLines(directory, "my.in")

    val root = dir("/")
        .also { it.root = it } // this will allow to 'cd ..' in '/', which will become '/'

    var currentDir = root
    val dirs = mutableListOf(root)

    lines.forEach {
        val elements = it.split(" ")

        when (elements[0]) {
            "$" ->
                when (elements[1]) {
                    "cd" ->
                        when (elements[2]) {
                            "/" -> currentDir = root
                            ".." -> currentDir = currentDir.root
                            else -> currentDir = changeDirectory(currentDir, elements[2])
                        }
                }

            "dir" -> {
                val newDir = dir(elements[1])
                    .also { it.root = currentDir }

                currentDir.contents += newDir
                dirs += newDir
            }

            else -> {
                val fileSize = elements[0].toInt()
                currentDir.contents += file(elements[1], fileSize)
            }
        }
    }

    calculateAndUpdateTotalSize(root)
    /*
        "e" 584
        "a" 94853
        "d" 24933642
        "/" 48381165
    */

    val part1 = dirs.fold(0) { total, next -> total + if (next.size < 100000) next.size else 0 }

    val totalSpace = 70000000
    val usedSpace = root.size
    val freeSpace = totalSpace - usedSpace

    val desiredSpaceToFree = 30000000 - freeSpace

    dirs.sortBy { it.size }

    val part2 = dirs.find { it.size > desiredSpaceToFree }!!.size

    printResults("1427048", part1, part2) // part2 --> 2940614
}
