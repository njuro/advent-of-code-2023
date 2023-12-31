import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AdventOfCodeTasksTest {

    @Test
    fun day01() {
        runTaskTest(Digits(), 54450, 54265)
    }

    @Test
    fun day02() {
        runTaskTest(Cubes(), 2795, 75561)
    }

    @Test
    fun day03() {
        runTaskTest(Engine(), 535235, 79844424)
    }

    @Test
    fun day04() {
        runTaskTest(Cards(), 24733, 5422730)
    }

    @Test
    fun day06() {
        runTaskTest(Races(), 1155175, 35961505)
    }

    @Test
    fun day07() {
        runTaskTest(Ranks(), 250946742, 251824095)
    }

    @Test
    fun day08() {
        runTaskTest(Paths(), 18727L, 18024643846273L)
    }

    @Test
    fun day09() {
        runTaskTest(Sequences(), 1987402313, 900)
    }

    private fun runTaskTest(task: AdventOfCodeTask, part1Result: Any, part2Result: Any) {
        assertEquals(part1Result, task.run())
        assertEquals(part2Result, task.run(part2 = true))
    }
}
