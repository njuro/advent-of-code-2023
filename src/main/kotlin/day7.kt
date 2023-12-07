import utils.readInputLines

/** [https://adventofcode.com/2023/day/7] */
class Ranks : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val strengths = mutableListOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A').apply {
            if (part2) {
                remove('J')
                add(0, 'J')
            }
        }

        data class Hand(val cards: String, val bid: Int) : Comparable<Hand> {
            override fun compareTo(other: Hand): Int =
                getTypeRank().compareTo(other.getTypeRank()).takeIf { it != 0 }
                    ?: getStrengthRank().zip(other.getStrengthRank())
                        .firstNotNullOfOrNull { (rank1, rank2) -> rank1.compareTo(rank2).takeIf { it != 0 } } ?: 0

            fun getTypeRank(): Int =
                if (part2)
                    strengths.maxOf { card -> getTypeRank(cards.replace('J', card)) }
                else
                    getTypeRank(cards)

            private fun getTypeRank(cards: String): Int {
                val counts = cards.groupBy { it }.mapValues { it.value.size }.values
                return when {
                    5 in counts -> 6
                    4 in counts -> 5
                    3 in counts && 2 in counts -> 4
                    3 in counts -> 3
                    counts.count { it == 2 } == 2 -> 2
                    2 in counts -> 1
                    else -> 0
                }
            }

            fun getStrengthRank(): List<Int> = cards.map(strengths::indexOf)
        }

        return readInputLines("7.txt").map {
            val (cards, bid) = it.split(" ")
            Hand(cards, bid.toInt())
        }.sorted().withIndex().sumOf { (index, hand) -> (index + 1) * hand.bid }
    }
}

fun main() {
    print(Ranks().run(part2 = true))
}
