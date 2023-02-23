package weeks_With_KOTLIN.examination.AOC2022_03

import java.nio.file.Files
import java.nio.file.Paths

var testList = mutableListOf<String>()

fun main() {
    partA()
    partB()
    improvedSolution()
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

fun improvedSolution() {
    val input = readFromFile()
    val chunkedInput = input.chunked(3)

    val points = chunkedInput.sumOf { lines ->
        val commonChars = lines.map(String::toSet).reduce { a, b -> a.intersect(b) }
        getCharValue(commonChars)
    }
    println(points)
}

fun readFromFile(): MutableList<String> {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_03/data.txt")
    return Files.readAllLines(filePath)
}
/**
 * Återigen tittade jag på en lösning som jag inte tycker är bättre än min. Jag vet inte om personens lösning jag tittat
 * på inte visste att man kan intersect fler än 2 stringar men jag tänker att jag ska pröva att använda mig av "chunk"
 * för att testa på den iallafall. Inspo ifrån https://todd.ginsberg.com/post/advent-of-code/2022/day3/
 *
 * Nu efter att ha implementerat chunk så tar jag tillbaks min första åsikt. Denna nya lösning med chunk är faktiskt
 * bättre än min egna lösning för partB och betydligt mer dynamisk om man tex skulle vilja ändra värdet på chunk size
 */
//https://adventofcode.com/2022/day/3