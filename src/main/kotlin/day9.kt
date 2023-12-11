import utils.readInputLines

/** [https://adventofcode.com/2023/day/9] */
class Sequences : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        return readInputLines("9.txt").sumOf { line ->
            val initial = line.split(" ").filter(String::isNotBlank).map(String::toInt)

            val sequences = generateSequence(initial) { previous ->
                previous.zipWithNext().map { (a, b) -> b - a }
            }.takeWhile { sequence -> sequence.any { it != 0 } }.toList()

            sequences.reversed().fold(0) { result, sequence ->
                if (part2) sequence.first() - result else result + sequence.last()
            }.toInt()
        }
    }
}

fun main() {
    print(Sequences().run(part2 = false))
}
