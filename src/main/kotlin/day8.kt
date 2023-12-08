import utils.readInputBlock

/** [https://adventofcode.com/2023/day/8] */
class Day8 : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val (instructions, nodes) = readInputBlock("8.txt").split("\n\n")
        val pattern = Regex("(\\w+) = \\((\\w+), (\\w+)\\)")
        val map = nodes.lines().filter(String::isNotBlank).associate {
            val (node, left, right) = pattern.matchEntire(it)!!.destructured
            node to (left to right)
        }

        var steps = 0
        var current = "AAA"
        while (current != "ZZZ") {
            val instruction = instructions[steps % instructions.length]
            val next = map.getValue(current)
            current = if (instruction == 'L') next.first else next.second
            steps += 1
        }

        return steps
    }
}

fun main() {
    print(Day8().run(part2 = false))
}
