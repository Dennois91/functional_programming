package weeks_With_KOTLIN.examination.AOC2022_03

import java.nio.file.Files
import java.nio.file.Paths

var testList = mutableListOf<String>()

fun main() {
    partA()
    partB()
}

fun partB() {
    var points = 0
    testList = readFromFile()
    while (testList.isNotEmpty()) {
        val stringOne = testList[0].toSet()
        val stringTwo = testList[1].toSet()
        val stringThree = testList[2].toSet()
        testList = testList.drop(3).toMutableList()

        val commonChars = stringOne.intersect(stringTwo.intersect(stringThree))
        points += getCharValue(commonChars)
    }
    println(points)
}

fun partA() {
    var points = 0
    testList = readFromFile()

    testList.forEach { string ->
        val firstHalf = string.substring(0, string.length / 2).toSet()
        val secondHalf = string.substring(string.length / 2).toSet()
        val commonChars = firstHalf.intersect(secondHalf)
        points += getCharValue(commonChars)
    }
    println(points)
}

fun getCharValue(char: Set<Char>): Int {
    val charValue = char.first().code
    return if (char.first().isUpperCase()) {
        charValue - 'A'.code + 27
    } else {
        charValue - 'a'.code + 1
    }
}

fun readFromFile(): MutableList<String> {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_03/data.txt")
    return Files.readAllLines(filePath)
}
/**
 * Implement chunk
 */


//https://adventofcode.com/2022/day/3