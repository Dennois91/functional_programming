package weeks_With_KOTLIN.examination.AOC2022_01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun testMapDataToInt() {
        val input = mutableListOf<List<String>>()
        val stringListOne = listOf<String>("1", "2", "3")
        val stringListTwo = listOf<String>("4", "5", "6")
        input.add(stringListOne)
        input.add(stringListTwo)

        val result = mapDataToInt(input)

        assertTrue(result is List<List<Int>>, "The result should be a list of int lists")
    }

    @Test
    fun testGetTopThree() {
        val input = listOf(
            listOf(2, 1, 5, 4),
            listOf(7, 9, 6, 8),
            listOf(10, 12, 11, 13),
            listOf(15, 16, 14, 17),
            listOf(20, 18, 19, 21),
        )

        val expectedOutput = listOf(
            listOf(20, 18, 19, 21),
            listOf(15, 16, 14, 17),
            listOf(10, 12, 11, 13),
        )

        assertEquals(expectedOutput, getTopThree(input))
    }

    @Test
    fun testGetTopThreeWithSingleList() {
        val input = listOf(
            listOf(2, 1, 5, 4)
        )

        val expectedOutput = listOf(
            listOf(2, 1, 5, 4)
        )

        assertEquals(expectedOutput, getTopThree(input))
    }

    @Test
    fun testGetTopThreeWithEmptyList() {
        val input = emptyList<List<Int>>()

        val expectedOutput = emptyList<List<Int>>()

        assertEquals(expectedOutput, getTopThree(input))
    }

    @Test
    fun testGetTopThreeWithDuplicateValues() {
        val input = listOf(
            listOf(2, 1, 5, 4),
            listOf(7, 9, 6, 8),
            listOf(10, 12, 11, 13),
            listOf(15, 16, 14, 17),
            listOf(20, 18, 19, 21),
            listOf(25, 22, 22, 23)
        )

        val expectedOutput = listOf(
            listOf(25, 22, 22, 23),
            listOf(20, 18, 19, 21),
            listOf(15, 16, 14, 17),
        )

        assertEquals(expectedOutput, getTopThree(input))
    }
}

