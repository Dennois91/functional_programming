package weeks_With_KOTLIN.examination.AOC2021_02

import java.nio.file.Files
import java.nio.file.Paths

var xCordinat = 0 //Frammåt
var yCordinat = 0 //Upp ner

fun main() {
    taskA()
    taskB()

}

fun taskB() {
    yCordinat=0
    xCordinat=0
    var aim = 0

    val listOfFile = readFromFile()
    if (listOfFile != null) {
        for (line in listOfFile) {
            when {
                "forward" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    xCordinat += value
                    val depthIncrement = aim * value
                    yCordinat += depthIncrement
                }
                "up" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    aim -= value
                }
                "down" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    aim += value
                }
            }
        }
    }
    val depth = yCordinat
    println("x = $xCordinat, y = $yCordinat, depth = $depth, result = ${xCordinat * yCordinat}")
}



fun taskA() {
    yCordinat=0
    xCordinat=0
    val listOfFile = readFromFile()
    if (listOfFile != null) {
        for (line in listOfFile) {
            when {
                "forward" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    xCordinat += value
                }

                "up" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    yCordinat -= value
                }

                "down" in line -> {
                    val value = line.substringAfterLast(" ").toInt()
                    yCordinat += value
                }
            }
        }
    }
    println("x = $xCordinat     y = $yCordinat    ${xCordinat * yCordinat}")
}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2021_02/data.txt")
    return Files.readAllLines(filePath)
}

//https://adventofcode.com/2021/day/2