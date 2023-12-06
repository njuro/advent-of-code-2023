import utils.readInputLines

/** [https://adventofcode.com/2023/day/6] */
class Races : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val (times, distances) = readInputLines("6.txt").map { line ->
            line.substringAfter(":").let {
                if (part2)
                    listOf(it.replace(" ", "").toLong())
                else
                    it.split(" ").filter(String::isNotBlank).map(String::toLong)
            }
        }

        return times.zip(distances).map { (time, distance) ->
            (1..<time).count { holding ->
                (time - holding) * holding > distance
            }
        }.reduce { a, b -> a * b }
    }
}

fun main() {
    print(Races().run(part2 = true))
}
