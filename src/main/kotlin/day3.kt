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
        var result = 0
        fun Coordinate.isPart(): Boolean =
            adjacent8().any { coord -> engine.getValue(coord).let { !it.isDigit() && it != '.' } }
        engine.filterValues { it.isDigit() }.forEach { coord, c ->
            if (coord in seen) {
                return@forEach
            }
            var isPart = coord.isPart()
            seen.add(coord)
            var buffer = c.toString()
            var current = coord
            while (true) {
                current = current.left()
                val value = engine.getValue(current)
                if (!value.isDigit()) break
                seen.add(current)
                isPart = isPart || current.isPart()
                buffer = value + buffer
            }
            current = coord
            while (true) {
                current = current.right()
                val value = engine.getValue(current)
                if (!value.isDigit()) break
                seen.add(current)
                isPart = isPart || current.isPart()
                buffer += value
            }

            if (isPart) {
                result += buffer.toInt()
            }
        }

        return result
    }
}

fun main() {
    print(Engine().run(part2 = false))
}
