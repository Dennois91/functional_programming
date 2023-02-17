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
        val stringOne = testList[0]
        val stringTwo = testList[1]
        val stringThree = testList[2]
        testList = testList.drop(3).toMutableList()

        val commonChars = stringOne.toSet().intersect(stringTwo.toSet().intersect(stringThree.toSet()))
        points += getCharValue(commonChars)
    }
    println(points)
}

fun partA() {
    var points = 0
    testList = readFromFile()

    testList.forEach { string ->
        val firstHalf = string.substring(0, string.length / 2)
        val secondHalf = string.substring(string.length / 2)
        val firstHalfChars = firstHalf.toSet()
        val secondHalfChars = secondHalf.toSet()
        val commonChars = firstHalfChars.intersect(secondHalfChars)
        points += getCharValue(commonChars)
    }
    println(points)
}

fun readFromFile(): MutableList<String> {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_03/data.txt")
    return Files.readAllLines(filePath)
}

fun getCharValue(char: Set<Char>): Int {
    val charValue = char.first().code
    return if (char.first().isUpperCase()) {
        charValue - 'A'.code + 27
    } else {
        charValue - 'a'.code + 1
    }
}
