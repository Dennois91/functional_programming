package weeks_With_KOTLIN.examination.AOC2021_02

import java.nio.file.Files
import java.nio.file.Paths

var xCordinat = 0 //Frammåt
var yCordinat = 0 //Upp ner

fun main() {
    taskA(readFromFile())
    taskB(readFromFile())

}

/**
 * På Denna lösning hittade jag inte någon lösning som jag tyckte var bättre. Jag kollade på https://todd.ginsberg.com/post/advent-of-code/2021/day2/
 * Men den kändes bara betydligt mer komplicerad och svår att följa än koden jag skrivit nedanför.
 * Min kod är ganska lik med att den använder When på samma sätt bara det att jag inte använder Command function utan räknar ut allt
 * direkt i when satsen.
 * Jag tycker min är simplare eftersom att input filen alltid har värdet efter " " vi ska flytta.
 * Och Kommandot är alltid första ordet i line.
 */

fun taskB(readFromFile: MutableList<String>?): Int {
    yCordinat=0
    xCordinat=0
    var aim = 0

    if (readFromFile != null) {
        for (line in readFromFile) {
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
    return xCordinat * yCordinat
}



fun taskA(readFromFile: MutableList<String>?): Int {
    yCordinat=0
    xCordinat=0
    if (readFromFile != null) {
        for (line in readFromFile) {
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
    return xCordinat * yCordinat
}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2021_02/data.txt")
    return Files.readAllLines(filePath)
}

//https://adventofcode.com/2021/day/2