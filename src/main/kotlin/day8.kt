import utils.lcm
import utils.readInputBlock

/** [https://adventofcode.com/2023/day/8] */
class Paths : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val (instructions, nodes) = readInputBlock("8.txt").split("\n\n")
        val pattern = Regex("(\\w+) = \\((\\w+), (\\w+)\\)")
        val paths = nodes.lines().filter(String::isNotBlank).associate {
            val (node, left, right) = pattern.matchEntire(it)!!.destructured
            node to (left to right)
        }

        val starts = if (part2) paths.keys.filter { it.endsWith("A") }.toSet() else setOf("AAA")
        val ends = if (part2) paths.keys.filter { it.endsWith("Z") }.toSet() else setOf("ZZZ")
        return starts.map { start ->
            generateSequence(start to 0) { (current, steps) ->
                val instruction = instructions[steps % instructions.length]
                val (left, right) = paths.getValue(current)
                val next = if (instruction == 'L') left else right
                next to steps + 1
            }.first { (current, _) -> current in ends }.second.toLong()
        }.reduce(::lcm)
    }
}

fun main() {
    print(Paths().run(part2 = true))
}
