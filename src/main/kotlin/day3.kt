import utils.Coordinate
import utils.readInputLines

/** [https://adventofcode.com/2023/day/3] */
class Engine : AdventOfCodeTask {
    override fun run(part2: Boolean): Any {
        val engine = readInputLines("3.txt").flatMapIndexed { y, row ->
            row.mapIndexed { x, c ->
                Coordinate(x, y) to c
            }
        }.toMap().withDefault { '.' }

        val seen = mutableSetOf<Coordinate>()
        val numbers = mutableMapOf<List<Coordinate>, Int>()

        engine.filterValues { it.isDigit() }.forEach { (coord, c) ->
            if (coord in seen) {
                return@forEach
            }
            seen.add(coord)
            var numberBuffer = c.toString()
            val digits = mutableListOf(coord)
            var current = coord
            while (true) {
                current = current.left()
                val value = engine.getValue(current)
                if (!value.isDigit()) break
                seen.add(current)
                numberBuffer = value + numberBuffer
                digits.add(0, current)
            }
            current = coord
            while (true) {
                current = current.right()
                val value = engine.getValue(current)
                if (!value.isDigit()) break
                seen.add(current)
                numberBuffer += value
                digits.add(current)
            }
            numbers[digits] = numberBuffer.toInt()
        }

        return if (part2) {
            engine.filterValues { it == '*' }.keys.sumOf { coord ->
                val adjacentCoords = coord.adjacent8()
                val adjacentNumbers = numbers.filterKeys { digits ->
                    digits.intersect(adjacentCoords).isNotEmpty()
                }.values
                if (adjacentNumbers.size == 2) {
                    adjacentNumbers.first() * adjacentNumbers.last()
                } else 0
            }
        } else {
            numbers.filterKeys { digits ->
                digits.any { digit ->
                    digit.adjacent8().any { coord -> engine.getValue(coord).let { c -> !c.isDigit() && c != '.' } }
                }
            }.values.sum()
        }
    }
}

fun main() {
    print(Engine().run(part2 = true))
}
