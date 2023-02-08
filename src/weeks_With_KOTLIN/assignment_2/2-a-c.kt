package weeks_With_KOTLIN.assignment_2

import kotlin.math.sqrt

/*
Uppgift 2a– For-loopar
Skriv en funktion som listar alla jämna tal mellan 1 och 20, baklänges. Använd modulu
Uppgift 2b– For-loopar
Skriv en funktion som listar godtycklig del av godtycklig multiplikationstabell
Inparametrar ska vara:
• En int x som talar om vilken multiplikationstabell som ska listas
• Det lägsta tal som x ska multipliceras med
• Det högsta tal som x ska multipliceras med
Uppgift 2c– For-loopar
Skriv en funktion som räknar ut om ett givet tal är ett primtal. Returnera true eller false.
*/

fun main() {
    println(listEvenNumbersReversed())
    println(listEvenNumbersReversedFunctionally(20).reversed())
    println(multiplicationTable(9,2,4))
    println(multiplicationTableUsingFold(9,2,4))
    println(isPrime(17.0))
}

fun listEvenNumbersReversed(): List<Int> {
    return (2..20)
        .filter { (it % 2) == 0 }
        .reversed()
}

fun listEvenNumbersReversedFunctionally(n: Int): List<Int> {
    return (1..n).filter { it % 2 == 0 }
}

fun multiplicationTable(table: Int, from: Int, to: Int): List<Int> {
    return (from..to).map { i -> i.times(table) }
}

fun multiplicationTableUsingFold(table: Int, from: Int, to: Int): List<Int> {
    return (from..to).fold(emptyList()) { acc, i -> acc + i.times(table) }
}

fun isPrime(talToCheck: Double): Boolean {
    if (talToCheck <= 1) return false
    val sqrtOfTal = sqrt(talToCheck)
    for (i in 2..sqrtOfTal.toInt()) {
        if (talToCheck.toInt() % i == 0) return false
    }
    return true
}