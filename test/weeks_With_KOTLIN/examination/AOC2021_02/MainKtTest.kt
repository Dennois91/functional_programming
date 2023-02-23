package weeks_With_KOTLIN.examination.AOC2021_02

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {
    /**
     * forward 5
     * down 5
     * forward 8
     * up 3
     * down 8
     * forward 2
     */

    @Test
    fun taskA_test() {
        val input = mutableListOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        val result = taskA(input)
        assertEquals(150, result)
    }

    @Test
    fun taskA_test_emptyList() {
        val input = mutableListOf<String>()
        val result = taskA(input)
        assertEquals(0, result)
    }

    @Test
    fun taskB_test() {
        val input = mutableListOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        val result = taskB(input)
        assertEquals(900, result)
    }

    @Test
    fun taskB_test_emptyList() {
        val input = mutableListOf<String>()
        val result = taskB(input)
        assertEquals(0, result)
    }
}
