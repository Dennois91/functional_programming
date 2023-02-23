package weeks_With_KOTLIN.examination.AOC2021_01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    /**
     * 199
     * 200
     * 208
     * 210
     * 200
     * 207
     * 240
     * 269
     * 260
     * 263
     */

    @Test
    fun taskA() {
        val testList = mutableListOf("199","200","208","210","200","207","240","269","260","263")
        val result = taskA(testList)
        assert(7 == result)
    }
    @Test
    fun taskA_whenAllCountsAreIncreasing_shouldReturnTheSizeMinusOne() {
        val testList = mutableListOf("1", "2", "3", "4", "5", "6")
        val result = taskA(testList)
        assertEquals(testList.size - 1, result)
    }

    @Test
    fun taskA_whenAllCountsAreDecreasing_shouldReturnZero() {
        val testList = mutableListOf("6", "5", "4", "3", "2", "1")
        val result = taskA(testList)
        assertEquals(0, result)
    }

    @Test
    fun taskB() {
        val testList = mutableListOf("199","200","208","210","200","207","240","269","260","263")
        val result = taskB(testList)
        assert(5 == result)
    }

    @Test
    fun taskB_whenAllSumAreEqual_shouldReturnZero() {
        val testList = mutableListOf("10", "10", "10", "10", "10", "10")
        val result = taskB(testList)
        assertEquals(0, result)
    }

    @Test
    fun taskB_whenOnlyOneGroupOfMeasures_shouldReturnZero() {
        val testList = mutableListOf("20", "30", "40")
        val result = taskB(testList)
        assertEquals(0, result)
    }
}
