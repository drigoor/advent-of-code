package aoc22.day11


import aoc22.day11.IntArithmetics.*
import aoc22.utils.*
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator { // from: https://kotlinlang.org/docs/enum-classes.html#working-with-enum-constants
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)

    fun run(a: Int, b: Int) = apply(a, b)
}


interface Operand


object Old : Operand


data class Value(val value: Int) : Operand


data class Operation(val operator: IntArithmetics, val operands: Pair<Operand, Operand>)


fun parseOperand(input: String) = when (input) {
    "old" -> Old
    else -> Value(input.toInt())
}


fun parseOperator(input: String) = when (input) {
    "*" -> TIMES
    else -> PLUS
}


fun parseOperation(input: List<String>) = Operation(parseOperator(input[1]), Pair(parseOperand(input[0]), parseOperand(input[2])))


data class Monkey(val items: MutableList<Int>, val operation: Operation, val divisible: Int, val monkeys: Pair<Int, Int>) {
    var counts = 0

    fun turn(items: List<Int>): List<Pair<Int, Int>> {
        return items.map {
            val level = it
            val leftOperand = operation.operands.first
            val left = when (leftOperand) {
                Old -> level
                else -> (leftOperand as Value).value
            }
            val rightOperand = operation.operands.second
            val right = when (rightOperand) {
                Old -> level
                else -> (rightOperand as Value).value
            }
            val currentLevel = operation.operator.apply(left, right)
            val newLevel = currentLevel // 3
            val isBored = (newLevel % divisible) == 0
            val monkey = if (isBored) {
                monkeys.first
            } else {
                monkeys.second
            }
            counts++

            Pair(newLevel, monkey)
        }
    }

    override fun toString(): String {
        return items.toString()
    }
}


fun parseMonkey(input: List<String>): Monkey {
    val items = input[1].split(": ")[1].split(", ").map { it.toInt() } as MutableList
    val operation = parseOperation(input[2].split(" = ")[1].split(" "))
    val divisible = input[3].split("by ")[1].toInt()
    val monkey1 = input[4].split("    If true: throw to monkey ")[1].toInt()
    val monkey2 = input[5].split("    If false: throw to monkey ")[1].toInt()

    return Monkey(items, operation, divisible, Pair(monkey1, monkey2))
}


fun processTurn(monkeys: List<Monkey>) {
    monkeys.forEachIndexed { i, it ->
        val turns = it.turn(it.items)
        it.items.clear()
        turns.forEach {
            monkeys[it.second].items.add(it.first)
        }
    }

    if (DEBUG) {
        monkeys.forEachIndexed { index, monkey ->
            println("Monkey $index: ${monkey.items.joinToString()}")
        }.also { println("---------------------------") }
    }
}


var DEBUG = true


fun main() {
    val lines = readLinesAndGroup(directory, "example.in")

    val monkeys = lines.map { parseMonkey(it) }

    for (i in 1..20) {
        processTurn(monkeys)

        println("== After round $i ==")
        monkeys.forEachIndexed { i, m ->
            println("Monkey $i inspected items ${m.counts} times.")
        }
    }


    // PART 1 -- after 20 rounds
    //    Monkey 0: 101
    //    Monkey 1: 95
    //    Monkey 2: 7
    //    Monkey 3: 105
    //
    // part1 example = 10605
    //       my      = 112815

    val part1 = monkeys.sortedByDescending { it.counts }.take(2).fold(1) { result, next -> result * next.counts }

    val part2 = 42
    printResults("10605", part1, part2)
}
