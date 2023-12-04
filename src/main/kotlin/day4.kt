import utils.readInputLines
import kotlin.math.pow

/** [https://adventofcode.com/2023/day/4] */
class Cards : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        fun String.parseNumbers(): Set<Int> = split(" ").filter(String::isNotBlank).map { it.toInt() }.toSet()
        val cards = readInputLines("4.txt").associate { line ->
            val (card, numbers) = line.split(": ")
            val cardId = card.substringAfter("Card").trim().toInt()
            val (winning, actual) = numbers.split(" | ")
            val matchingCount = winning.parseNumbers().intersect(actual.parseNumbers()).size
            cardId to matchingCount
        }
        return if (part2) {
            val counter = cards.keys.associateWith { 1 }.toMutableMap()
            cards.forEach { (cardId, matchingCount) ->
                (cardId + 1..cardId + matchingCount).forEach { nextCardId ->
                    counter[nextCardId] = counter.getValue(nextCardId) + counter.getValue(cardId)
                }
            }
            counter.values.sum()
        } else cards.values.filter { it > 0 }.sumOf { 2.0.pow(it - 1) }.toInt()

    }
}

fun main() {
    print(Cards().run(part2 = true))
}
