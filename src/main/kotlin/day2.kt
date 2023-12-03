import utils.readInputLines
import kotlin.math.max

/** [https://adventofcode.com/2023/day/2] */
class Cubes : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        return readInputLines("2.txt").sumOf { line ->
            val (game, rounds) = line.split(": ")
            val cubes = mutableMapOf<String, Int>().withDefault { 0 }
            rounds.split("; ").forEach { round ->
                round.split(", ").forEach { hint ->
                    val (amount, color) = hint.split(" ")
                    cubes[color] = max(amount.toInt(), cubes.getValue(color))
                }
            }
            if (part2) {
                cubes.values.reduce { a, b -> a * b }
            } else {
                val maxCubes = mapOf("red" to 12, "green" to 13, "blue" to 14)
                val gameId = game.split(" ").last().toInt()
                gameId.takeIf { maxCubes.all { (color, amount) -> amount >= cubes.getValue(color) } } ?: 0
            }
        }
    }
}

fun main() {
    print(Cubes().run(part2 = true))
}
