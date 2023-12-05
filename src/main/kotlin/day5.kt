import utils.readInputBlock

/** [https://adventofcode.com/2023/day/5] */
class Day5 : AdventOfCodeTask {
    private data class Mapping(val source: String, val destination: String, val ranges: Set<MappingRange>) {
        data class MappingRange(val sourceFrom: Long, val destinationFrom: Long, val length: Long) {
            operator fun contains(value: Long): Boolean = value in (sourceFrom..<sourceFrom + length)
        }
    }

    override fun run(part2: Boolean): Any {
        val input = readInputBlock("5.txt").split("\n\n")
        val seeds = input.first().substringAfter("seeds: ").split(" ").map { it.toLong() }
        val mappings = input.drop(1).map { it.split("\n") }.map { block ->
            val (source, destination) = block.first().substringBefore(" map:").split("-to-")
            val ranges = block.drop(1).filter(String::isNotBlank).map { range ->
                val (destinationFrom, sourceFrom, length) = range.split(" ").filter(String::isNotBlank)
                    .map { it.toLong() }
                Mapping.MappingRange(sourceFrom, destinationFrom, length)
            }
            Mapping(source, destination, ranges.toSet())
        }

        return seeds.minOf { seed ->
            mappings.fold(seed) { current, mapping ->
                mapping.ranges.firstOrNull { range -> current in range }
                    ?.let { range -> range.destinationFrom + (current - range.sourceFrom) }
                    ?: current
            }
        }
    }
}

fun main() {
    print(Day5().run(part2 = false))
}
