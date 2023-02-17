package weeks_With_KOTLIN.examination.AOC2022_02

import java.nio.file.Files
import java.nio.file.Paths

// OPPONENT DRAW
// A ==  ROCK   == X
// B ==  PAPER  == Y
// C == SCISSOR == Z

// WIN == 6           // MODEL "A X"
// DRAW == 3          // IF SECOND COLUMN
// LOSS == 0          X means you need to lose.
// ROCK == 1          Y means you need to end the round in a draw.
// PAPER == 2         Z means you need to win.
// SCISSOR == 3

fun main() {
    partA()
    partB()
}

fun partB() {
    var points = 0

    readFromFile()?.forEach { line ->
        val opponent = line.substring(0, 1)
        val player = line.substring(2, 3)

        when {
            opponent == "A" && player == "X" -> points += 3 // you lose with scissors
            opponent == "A" && player == "Y" -> points += 4 // you draw with rock
            opponent == "A" && player == "Z" -> points += 8 // you win with paper
            opponent == "B" && player == "X" -> points += 1 // you lose with rock
            opponent == "B" && player == "Y" -> points += 5 // you draw with paper
            opponent == "B" && player == "Z" -> points += 9 // you win with scissors
            opponent == "C" && player == "X" -> points += 2 // you lose with paper
            opponent == "C" && player == "Y" -> points += 6 // you draw with scissors
            opponent == "C" && player == "Z" -> points += 7 // you win with rock
        }
    }
    println(points)
}

fun partA() {
    var points = 0

    readFromFile()?.forEach { line ->
        if (line.startsWith("A") && line.endsWith("X"))
            points += 4
        else if (line.startsWith("A") && line.endsWith("Y"))
            points += 8
        else if (line.startsWith("A") && line.endsWith("Z"))
            points += 3
        else if (line.startsWith("B") && line.endsWith("X"))
            points += 1
        else if (line.startsWith("B") && line.endsWith("Y"))
            points += 5
        else if (line.startsWith("B") && line.endsWith("Z"))
            points += 9
        else if (line.startsWith("C") && line.endsWith("X"))
            points += 7
        else if (line.startsWith("C") && line.endsWith("Y"))
            points += 2
        else if (line.startsWith("C") && line.endsWith("Z"))
            points += 6
    }
    println(points)
}

fun readFromFile(): MutableList<String>? {
    val filePath = Paths.get("src/weeks_With_KOTLIN/examination/AOC2022_02/data.txt")
    return Files.readAllLines(filePath)
}


