package weeks_With_KOTLIN.assignment_7

const val initialValue = 10000.0
const val yearsToAccumulate = 100
const val interestRate = 0.07
const val parenthesis = "(()())())"
val listOfInts = listOf(1, 2, 3)

fun main() {
    printInterestOnInterest()
    println(recursiveSummarize(listOfInts))
    println(findMaxValueRecursivelyInListOfInts(listOfInts))
    println(recursivelyReturnAStringReversed("Pele"))
    println(checkBalanceOfParenthesis(parenthesis))
    println(fib(1, 2, 30))

}

fun interestOnInterest(initialValue: Double, yearsToAccumulate: Int, interestRate: Double): Double {

    tailrec fun interestAcc(acc: Double, years: Int, interestRate: Double): Double {
        return if (years == 0) acc
        else interestAcc(acc * (1 + interestRate), years - 1, interestRate)
    }
    return interestAcc(initialValue, yearsToAccumulate, interestRate)

}

fun printInterestOnInterest() {
    val accumulatedValue =
        interestOnInterest(initialValue, yearsToAccumulate, interestRate)
    println("value after $yearsToAccumulate is $accumulatedValue")
}

fun recursiveSummarize(listOfInts: List<Int>): Int {
    return when {
        listOfInts.isEmpty() -> 0
        else -> listOfInts.first() + recursiveSummarize(listOfInts.drop(1))
    }
}

fun findMaxValueRecursivelyInListOfInts(listOfInts: List<Int>): Int {

    tailrec fun findMaxValue(listOfInts: List<Int>, maxValue: Int): Int {
        return when {
            listOfInts.isEmpty() -> maxValue
            else -> {
                val currentValue = listOfInts[0]
                val newMaxValue = if (currentValue > maxValue) currentValue else maxValue
                findMaxValue(listOfInts.drop(1), newMaxValue)
            }
        }
    }
    return when {
        listOfInts.isEmpty() -> 0
        else -> findMaxValue(listOfInts, listOfInts[0])
    }
}

fun recursivelyReturnAStringReversed(text: String): String {
    return when {
        text.isEmpty() -> ""
        else -> {
            recursivelyReturnAStringReversed(text.takeLast(text.length - 1)) + text[0]
        }
    }
}

fun checkBalanceOfParenthesis(text: String): Boolean {

    tailrec fun checkBalanceOfParenthesis(text: String, count: Int): Boolean {
        if (count < 0) return false
        if (text.isEmpty()) return count == 0
        return when (text[0]) {
            '(' -> checkBalanceOfParenthesis(text.substring(1), count + 1)
            ')' -> checkBalanceOfParenthesis(text.substring(1), count - 1)
            else -> checkBalanceOfParenthesis(text.substring(1), count)
        }
    }
    return checkBalanceOfParenthesis(text, 0)
}

    tailrec fun fib(prevPrev: Int, prev: Int, i : Int) {
        val next = prevPrev + prev
        when  {
            (i == 0) -> println(1)
            (i == 1) -> println(2)
            (i-2 == 0) -> println(next)
        }
        if (i <= 2) System.exit(0)
        fib(prev, next, i -1)
    }
