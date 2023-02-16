package weeks_With_KOTLIN.assignment_11

val listOfDjur = listOf(
    Djur("katt", 4), Djur("hund", 4),
    Djur("mus", 4), Djur("struts", 2), Djur("fisk", 0), Djur("hund", 4),
    Djur("spindel", 8), Djur("fluga", 6), Djur("fästing", 6)

)

fun main() {
    a()
    b()
    c()
    d()
    e()

}
fun e(){

}/*
Uppgift 11:e – Mera lambdas, fold
Gör en funktion där du adderar fram summan av dina djurs benantal
• Använd Fold
• (Varför är fold lämpligare än reduce i detta fall?)
• (lättare att jobba utifrån den ursprungliga listan än från de mappar som skapats)
 */

fun d() {
    val djurMap = listOfDjur.groupBy(Djur::sort)
    println(djurMap.containsKey("katt"))
    println(djurMap.all { it.key == "katt" })
}    /*
Uppgift 11d – Mera lambdas, mera mappar
• Skriv en funktion som kollar om du har en katt i din map
• Skriv en funktion som kollar om alla djur i mappen är katter
     */

fun c() {
    //11 a
    val djurMap = listOfDjur.groupBy(Djur::sort)
    println(djurMap.filter { it.key.startsWith("k") }.map { it.key })
    println(djurMap.filter { it.value.count() == 1 }.map { it.key })
}/*
Uppgift 11c – Mera lambdas, filtrering av mappar
• Utifrån de mappar du skapade i uppgift 11a-b
• I mappen som har djurens sorter som nyckel
• Filtrera mappen så att den bara visar de djur som börjar på bokstaven K
• Filtrera mappen så att den bara visar de djur som det finns ett ex av
*/

fun b() {
    val mapOfDjur = listOfDjur.groupBy(Djur::antalBen) { it }.mapValues { it.value.distinctBy { djur -> djur.sort } }
    println(mapOfDjur.map { "${it.value.map { e -> e.sort }} har ${it.key} ben" })
}/*
Uppgift 11b – Mera lambdas, traversing av mappar
• Skapa en lista av olika sorters djur
• Skapa en map som är grupperad på antal ben
• Nyckel: antalBen, Värde: Djur
• Skriv en utskriftsfunktion som traverse den grupperade mappen och skriver ut varje nyckel,
följt av de sorter av djur som är sorterade under varje nyckel
• Helst inga dubblerade värden på djursorter
 */

fun a() {
    val djurMap = listOfDjur.groupBy(Djur::sort)
    println(djurMap.map { it.key + "  " + it.value.count() })
}    /*
    Uppgift 11a – Mera lambdas, gruppering av mappar
• Skapa en lista av olika sorters djur
• Skapa en map som är grupperad på sort av djur
• Nyckel: sort, Värde: Djur
• Skriv en utskriftsfunktion som traverse den grupperade mappen och skriver ut varje nyckel,
följt av antal djur som ligger i nyckelns värde
     */