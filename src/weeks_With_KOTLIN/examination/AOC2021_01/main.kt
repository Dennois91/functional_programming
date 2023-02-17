package weeks_With_KOTLIN.examination.AOC2021_01

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    taskA()
    taskB()

}

fun List<Int>.sumGroupOfSize(groupSize: Int): List<Int> {

    return this.windowed(groupSize, 1) { it.sum() }
}

fun taskB() {
    val files = readFromFile()
    val listOfGroups = mutableListOf<List<String>>()

    if (files != null) {
        for (i in 0..files.size - 3) {

            val groupOfMeasures = mutableListOf<String>()

            groupOfMeasures.add(files[i])
            groupOfMeasures.add(files[i + 1])
            groupOfMeasures.add(files[i + 2])
            listOfGroups.add(groupOfMeasures)

        }
    }

    val dataAsIntList = listOfGroups!!.map { group -> group.map { line -> line.toInt() } }

    val value = dataAsIntList.map { l -> l.sumGroupOfSize(3) }
    //Todo Get the extention on List working and use the sum of groups in list of list of int. need to make to List of Int

    var count = 0

    for (i in 1 until dataAsIntList.size) {
        val firstGrouping = dataAsIntList[i - 1]
        val secondGrouping = dataAsIntList[i]

        if (firstGrouping.sum() < secondGrouping.sum()) {
            count += 1
        }

    }
    println(count)

}

fun taskA() {
    val files = readFromFile()
    val counts = files!!.map { line -> line.toInt() }.zipWithNext().count { it.first < it.second }
    println(counts)
}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2021_01/data.txt")
    return Files.readAllLines(filePath)
}