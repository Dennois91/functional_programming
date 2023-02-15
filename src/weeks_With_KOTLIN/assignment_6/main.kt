package weeks_With_KOTLIN.assignment_6

import weeks_With_KOTLIN.assignment_4.Djur

val listOfDjur = listOf(
    Djur("Struts", 2), Djur("Häst", 5), Djur("Katt", 4),
    Djur("Spindel", 6)
)

fun main() {

    lambdas6h()
    lambdas6f()
    lambdas6e()
    lambdas6d()
    lambdas6c()
    lambdas6b()
    lambdas6a()
}

fun lambdas6h() {
    listOfDjur.flatMap { djur -> listOf(djur, djur) }.forEach { djur -> djur.printInfo() }
    /*
Uppgift 6h – Lambdas
Skriv en funktion som fördubblar antalet djur i den lista den returnerar. (Kräver ingen lambda)
 */
}

fun lambdas6g() {
    println(listOfDjur.count { djur -> djur.antalBen >= 6 })
    /*
    Uppgift 6g – Lambdas
Skriv en funktion som berättar hur många insekter som finns i listan (det är djur med fler än 4 ben)
     */
}

fun lambdas6f() {
    println(listOfDjur.sumOf { djur -> djur.antalBen })
    /*
    Uppgift 6f – Lambdas
Skriv en funktion som summerar antal ben djuren har (viss googling kan behövas för funktionen som
hittar max-värdet)
     */
}


fun lambdas6e() {
    listOfDjur.maxBy { djur -> djur.antalBen }.printInfo()

    val mostLeggs = listOfDjur.maxByOrNull { djur -> djur.antalBen }
    if (mostLeggs != null) {
        println("in this list ${mostLeggs.sort} has most amount of leggs with the number ${mostLeggs.antalBen}")
    }

    println(listOfDjur.maxOf { djur -> djur.antalBen })
    /*
    Uppgift 6:e – Lambdas
Skriv en funktion som hittar det högsta antalet ben som ett djur i listan har (viss googling kan behövas
för funktionen som hittar max-värdet)
     */
}


fun lambdas6d() {
    var condition = { djur: Djur -> djur.sort == "Katt" }
    println(listOfDjur.any(condition))

    condition = { djur -> djur.sort == "Groda" }
    println(listOfDjur.any(condition))
    /*
    Uppgift 6d – Lambdas
Skriv en funktion som kollar om det finns några katter i din lista. Testa både med en sorts djur som
finns i listan och en sort som inte finns
     */
}

fun lambdas6c() {
    listOfDjur.filter { djur -> djur.sort == "Struts" }
        .forEach { match -> println(match.sort + " found") }

    listOfDjur.filter { djur -> djur.sort != "Struts" }
        .forEach { missMatched -> println(missMatched.sort + " is not Struts") }
    /*
Uppgift 6c – Lambdas
Skriv en funktion som tar en lista av djur och sorterar bort alla spindlar (Eller någon annan sorts djur
som du har i din lista)
 */
}


fun lambdas6b() {
    //Immutable
    val listOfGrodor = listOfDjur.map { djur -> Djur("Groda", djur.antalBen) }
    listOfGrodor.forEach { println("${it.sort} och har " + "${it.antalBen} Ben.") }

    //Changing existing value
    listOfDjur.forEach { djur -> djur.sort = "Groda" }
    println(listOfDjur.map { it.sort + "  " + it.antalBen })
    println(listOfDjur.map { "${it.sort} och har ${it.antalBen} ben." })
    /*
    Uppgift 6b – Lambdas
Skriv en funktion som tar en lista av olika djur och gör om alla djuren till grodor. Använd lambdas för
detta
     */
}

fun lambdas6a() {
    listOfDjur.forEach { print("${it.sort} ") }
    println(listOfDjur.map { i -> i.sort })
    /*
    Uppgift 6:a – Lambdas
Bygg ut djurklasserna i övningsuppgift 4:
Gör om utskriften av listan så att du använder en lambda för att skriva ut djuren
Testa minst 2 olika sätt att skriva ut.
     */
}