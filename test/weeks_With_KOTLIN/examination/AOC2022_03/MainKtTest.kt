package weeks_With_KOTLIN.examination.AOC2022_03

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun testPartA() {
        val testInput = listOf("aaaab", "aaaaab", "aaaab")
        assertEquals(3, partA(testInput))
    }

    @Test
    fun testPartB() {
        val testInput = listOf("abcdeexdcba", "xyz", "zxy", "lmn"," lwe","lrw")
        assertEquals(36, partB(testInput))
    }


    @Test
    fun testPartB_emptyInput() {
        val testList = emptyList<String>()
        val expectedPoints = 0
        assertEquals(expectedPoints, partB(testList))
    }

    @Test
    fun testPartA_emptyInput() {
        val testList = emptyList<String>()
        val expectedPoints = 0
        assertEquals(expectedPoints, partA(testList))
    }
}