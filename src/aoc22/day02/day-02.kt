package aoc22.day02


import aoc22.utils.*


val directory: String = object {}.javaClass.packageName // from: https://www.techiedelight.com/get-name-current-function-kotlin/


const val ROCK = 1
const val PAPER = 2
const val SCISSORS = 3


fun ABCandXYZtoNumbers(it: String) = when (it) {
    "A", "X" -> ROCK
    "B", "Y" -> PAPER
    "C", "Z" -> SCISSORS
    else -> throw IllegalArgumentException()
}


fun playerTurnScore(opponent: Int, player: Int) = when {
    opponent == player -> 3
    player == ROCK && opponent == SCISSORS -> 6
    player == SCISSORS && opponent == PAPER -> 6
    player == PAPER && opponent == ROCK -> 6
    else -> 0
}


fun turnToDraw(opponent: Int) = opponent


fun turnToLoss(opponent: Int) = when (opponent) {
    ROCK -> SCISSORS
    SCISSORS -> PAPER
    PAPER -> ROCK
    else -> throw IllegalArgumentException()
}


fun turnToWin(opponent: Int) = when (opponent) {
    SCISSORS -> ROCK
    PAPER -> SCISSORS
    ROCK -> PAPER
    else -> throw IllegalArgumentException()
}


const val LOSS = 1
const val DRAW = 2
const val WIN = 3


fun playPart2Turn(opponent: Int, player: Int) = when (player) {
    WIN -> turnToWin(opponent)
    LOSS -> turnToLoss(opponent)
    DRAW -> turnToDraw(opponent)
    else -> throw IllegalArgumentException()
}


fun main() {
    val lines = readLines(directory,"my.in")

    val common = lines
        .map {
            it.split(" ").map {
                ABCandXYZtoNumbers(it)
            }
        }

    val part1 = common
        .fold(0) { total, next ->
            total + playerTurnScore(next[0], next[1]) + next[1]
        }

    val part2 = common
        .map{
            val playerTurn = playPart2Turn(it[0], it[1])
            val score = playerTurnScore(it[0], playerTurn)
            score + playerTurn
        }
        .fold(0) { total, next ->
            total + next
        }

    printResults("15", part1, part2)
}
