package weeks_With_KOTLIN.examination.AOC2022_01

import java.nio.file.Files
import java.nio.file.Paths

val data = mutableListOf<List<String>>()
var elf = mutableListOf<String>()

fun main() {

    improvedSolution()  // Inspired by https://todd.ginsberg.com/post/advent-of-code/2022/day1/

    readFromFile()?.let { populateLists(it) }
    val dataAsInt = mapDataToInt(data)
    val topElfs = getTopThree(dataAsInt)
    printTopThree(topElfs, dataAsInt)
    sumOfTopThree(topElfs)


}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_01/data.txt")
    return Files.readAllLines(filePath)
}

fun populateLists(lines: List<String>) {
    lines.forEach { line ->
        if (line.isBlank()) {
            data.add(elf.toList())
            elf = mutableListOf()
        } else {
            elf.add(line)
        }
    }

    if (elf.isNotEmpty()) {
        data.add(elf.toList())
    }
}

fun mapDataToInt(data: List<List<String>>): List<List<Int>> {
    return data.map { e -> e.map { s -> s.toIntOrNull() ?: 0 } }
}

fun getTopThree(dataAsInt: List<List<Int>>): List<List<Int>> {
    return dataAsInt.sortedByDescending { elf ->
        elf.sum()
    }.take(3)
}

fun printTopThree(topElfs: List<List<Int>>, dataAsInt: List<List<Int>>) {
    topElfs.forEachIndexed { index, elf ->
        val sum = elf.sum()
        val groupIndex = dataAsInt.indexOf(elf)
        println("Elf $groupIndex is in position ${index + 1} with sum $sum")
    }
}

fun sumOfTopThree(topThree: List<List<Int>>) {
    val sum = topThree.flatten().sum()
    println("Sum of top three: $sum")
}

fun improvedSolution() {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_01/data.txt")
    val input = Files.readString(filePath)

    val sums = input.split("\n\r")
        .map { l -> l.trim().split("\\s+".toRegex())
            .sumOf { it.toInt() } }

    val topThree = sums.sortedDescending().take(3)
    println("Sums of top three groups: ${topThree.sortedDescending()}")
}


/**
 * Jag hittade denna lösning som jag försökte implementera men av någon anledning finns de något dolt tecken i min
 * input fil så jag kunde inte konvertera mina strings till ints med denna lösning nedan utan jag var tvungen att split
 * på detta sätt: val numbers = split("\\s+".toRegex()).map { it.toInt() }
 *
 * class Day01(input: String) {
 *
 *     private val calories = parseInput(input)
 *
 *     private fun parseInput(input: String): List<Int> =
 *         input
 *             .trim()
 *             .split("\n\n")
 *             .map { it.lines().sumOf(String::toInt) }
 *             .sortedDescending()
 * }
 *
 * https://adventofcode.com/2022/day/1
 */
