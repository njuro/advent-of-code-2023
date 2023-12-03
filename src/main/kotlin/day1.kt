import utils.readInputLines

/** [https://adventofcode.com/2023/day/1] */
class Digits : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val digits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine").withIndex()
            .associate { it.value to it.index + 1 }

        val pattern = Regex("(?=(${if (part2) digits.keys.joinToString("|") + "|" else ""}\\d))")

        fun MatchResult.toDigit(): Int = groupValues.last().run {
            if (first().isDigit()) first().digitToInt() else digits.getValue(this)
        }

        return readInputLines("1.txt").sumOf { pattern.findAll(it).run { first().toDigit() * 10 + last().toDigit() } }
    }
}

fun main() {
    print(Digits().run(part2 = true))
}
