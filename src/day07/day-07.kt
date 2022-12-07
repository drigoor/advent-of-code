package day07


import utils.*


val directory = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


abstract class yCommon {
    abstract var size: Int
    abstract var name: String
}

data class yFile(
    override var size: Int = 0,
    override var name: String = "",
) : yCommon()

data class yDir(
    override var size: Int = 0,
    override var name: String = "",
    var next: yDir? = null,
    var previous: yDir? = null,
    val files: List<yFile> = listOf()
) : yCommon()


fun main() {
    val lines = readLines(directory, "example.in")

    val roor = yDir(name = "/")

    lateinit var currentDir: yDir

    lines.forEach {
        val elements = it.split(" ")

        when (elements[0]) {
            "$" -> {
                if (elements[1] == "cd") {
                    if (currentDir == null) {
                        currentDir = yDir()
                        currentDir.name = elements[2]

                    } else {
                    }
                }
                when (elements[1]) {
                    "cd" -> {

                    }
                }
            }
        }

    }


    val part1 = 42

    val part2 = 42

    printResults("", part1, part2)
}
