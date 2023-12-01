package aoc22.day10


import aoc22.utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


var DEBUG = false


data class Cpu(val x: Int = 1, val xInTheEndOfCycle: Int = 1, val cycles: Int = 0) {
    fun run(instrs: List<Instr>) = instrs.fold(this) { cpu, instr ->
        val result = instr.exec(cpu)

        if (DEBUG) {
            println(result)
        }

        result
    }

    fun signalStrengthOf(instrs: List<Instr>): Int {
        val cpu = run(instrs)

        return cpu.x * cpu.cycles
    }

    fun part1(instrs: List<Instr>): List<Int> {
        val result: MutableList<Int> = mutableListOf()

        var n = 20

        while (n <= instrs.size) {
            result += signalStrengthOf(instrs.take(n))
            n += 40
        }
//        result += signalStrengthOf(instrs.take(20))
//        result += signalStrengthOf(instrs.take(60))
//        result += signalStrengthOf(instrs.take(100))
//        result += signalStrengthOf(instrs.take(140))
//        result += signalStrengthOf(instrs.take(180))
//        result += signalStrengthOf(instrs.take(220))

        return result
    }

    override fun toString() = "$cycles x $x"
}


interface Instr {
    fun exec(cpu: Cpu): Cpu
}


// 1 noop --> 1 tick
// 1 addx --> 1 tick
//            1 add N


object Tick : Instr {
    override fun exec(cpu: Cpu) = Cpu(cpu.xInTheEndOfCycle, cpu.xInTheEndOfCycle, cpu.cycles + 1)
    override fun toString() = "Tick"
}


class Add(private val value: Int) : Instr {
    override fun exec(cpu: Cpu) = Cpu(cpu.xInTheEndOfCycle, cpu.xInTheEndOfCycle + value, cpu.cycles + 1)
    override fun toString() = "Add $value"
}


const val LIT = "#"
const val DARK = " "


fun xInPos(cpu: Cpu): Boolean {
    val pos = (cpu.cycles - 1) % 40
    return cpu.x in pos - 1..pos + 1
}


fun main() {
    val lines = readLines(directory, "my.in")

    val instrs = lines.flatMap {
        val instrData = it.split(" ")
        when (instrData[0]) {
            "addx" -> listOf(Tick, Add(instrData[1].toInt()))
            else -> listOf(Tick)
        }
    }

//    instrs.forEachIndexed { index, instr ->
//        println("${index + 1} $instr")
//    }

    val result = Cpu().part1(instrs)

    val part1 = result.sum()

    val part2 = 42

    // part 1 example: 13140
    // part 1: 10760
    // part 2: FPGPHFGH
    printResults("10760", part1, part2)

    println("========")

    var values = instrs.fold(mutableListOf(Cpu())) { acc, instr ->
        acc += instr.exec(acc.last())
        acc
    }

    values = values.subList(1, values.size)

    values.forEachIndexed { index, cpu ->
        val pos = (cpu.cycles - 1) % 40
        val inPos = cpu.x in pos - 1..pos + 1

        if (pos == 0) { // not perfect because it will draw an extra new line
            println()
        }
        print(if (inPos) LIT else DARK)
    }

}
