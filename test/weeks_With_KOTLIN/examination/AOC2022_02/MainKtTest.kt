package weeks_With_KOTLIN.examination.AOC2022_02

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun testPartB() {

        val input4 = listOf("A X", "B Y", "C Z")
        assertEquals(15, partB(input4))

        val input5 = listOf("A X")
        assertEquals(3, partB(input5))
    }

    @Test
    fun testPartA() {

        val input4 = listOf("B X", "B Z", "C Y", "C Z")
        assertEquals(18, partA(input4))

        val input5 = listOf("A X")
        assertEquals(4, partA(input5))
    }
}
