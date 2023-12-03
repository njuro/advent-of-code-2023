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
        runTaskTest(Engine(), 535235, -1)
    }

    private fun runTaskTest(task: AdventOfCodeTask, part1Result: Any, part2Result: Any) {
        assertEquals(part1Result, task.run())
        assertEquals(part2Result, task.run(part2 = true))
    }
}
