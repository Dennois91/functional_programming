package weeks_With_KOTLIN.examination.AOC2022_01

import java.nio.file.Files
import java.nio.file.Paths

val data = mutableListOf<List<String>>()
var elf = mutableListOf<String>()

fun main() {

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

//https://adventofcode.com/2022/day/1

