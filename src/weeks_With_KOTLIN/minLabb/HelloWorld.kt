package weeks_With_KOTLIN


const val nummerFem: Int = 5
const val efterNamn: String = "Plutt"
const val height: Int = 5
const val width = 5
var heightTio: Int = 10
var widthTio = 10

fun main() {
    printHelloWorld()
    println(returnInt())
    println("Hello World 1")
    println(returnIntWithCurley())
    println(returnInputInt(nummerFem))
    println(printStringWithVal(efterNamn))
    println(calculateAreaOfRectangle(heightTio, widthTio))
    println(calculateAreaOfRectangleWithCurly(height, width))
    println(calculateCircumferenceOfRectangle(heightTio, widthTio))
    println(calculateCircumferenceOfRectangleWithCurly(height, width))
}

fun printHelloWorld() {
    println("Hello World 2")
}

fun returnInt(): Int = 1

fun returnIntWithCurley(): Int {
    return 2
}

fun returnInputInt(nummer: Int) = nummer

fun printStringWithVal(text: String) = ("Pelle $text")

fun calculateAreaOfRectangle(height: Int, width: Int) = height * width

fun calculateAreaOfRectangleWithCurly(height: Int, width: Int): Int {
    return height * width
}

fun calculateCircumferenceOfRectangle(height: Int, width: Int) = (height + width) * 2

fun calculateCircumferenceOfRectangleWithCurly(height: Int, width: Int): Int {
    return (height + width) * 2
}

