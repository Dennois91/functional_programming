package weeks_With_KOTLIN.examination.AOC2021_01

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    taskA(readFromFile())
    taskB(readFromFile())
    taskBImproved()

}


fun taskB(files: MutableList<String>?): Int {
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

    var count = 0

    for (i in 1 until dataAsIntList.size) {
        val firstGrouping = dataAsIntList[i - 1]
        val secondGrouping = dataAsIntList[i]

        if (firstGrouping.sum() < secondGrouping.sum()) {
            count += 1
        }
    }
    println(count)
    return count
}

fun taskA(files: MutableList<String>?): Int {

    val counts = files!!.map { line -> line.toInt() }.zipWithNext().count { it.first < it.second }
    println(counts)
    return counts
}
fun taskBImproved() {
    val files = readFromFile()
    val dataAsIntList = files?.map { it.toInt() }

    /**
     * Förbättrade version där jag skapar en extension function till List som tar N antal element i listan -
     * Summerar elementen, returnerar värdet i en ny list. Sedan går ett steg ner i listan och summerar N antal element -
     * Och repeterar. Resultatet blir en ny lista med de summerade värdena i grupper av storlek N
     * Inspiration ifrån https://todd.ginsberg.com/post/advent-of-code/2021/day1/
     */

    fun List<Int>.sumGroupOfSize(groupSize: Int): List<Int> {
        return this.windowed(groupSize, 1) { it.sum() }
    }

    val listOfGroups = dataAsIntList?.sumGroupOfSize(3)
    println(listOfGroups?.zipWithNext()?.count { it.first < it.second })
}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2021_01/data.txt")
    return Files.readAllLines(filePath)
}

//https://adventofcode.com/2021/day/1
