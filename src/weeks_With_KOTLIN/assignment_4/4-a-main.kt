package weeks_With_KOTLIN.assignment_4

val listOfDjur = listOf(Djur("Struts", 2), Djur("Häst", 4), Djur("Katt", 4))

val setOfDjur = setOf(Djur("Struts", 2), Djur("Struts", 2), Djur("Häst", 4), Djur("Katt", 4))
val otherSetOfDjur = mutableSetOf(Djur("Häst", 4), Djur("Katt", 4), Djur("Struts", 2))

fun main() {

    val hund = Djur("Hund", 4)
    val katt = Djur("Katt", 4)
    otherSetOfDjur.add(hund)
    otherSetOfDjur.add(hund)

    printInfo()
    printLegs()
    printType()

    val djurMap = mapOf("Pelle" to hund, "Ebba" to katt)
    djurMap.forEach { entry -> println(entry.key) }

    setOfDjur.forEach { djur -> println(djur.sort) }
    otherSetOfDjur.forEach { djur -> println(djur.sort) }

    println(soundOfAnimal(listOfDjur[1]))

}

fun soundOfAnimal(djur: Djur): String {
    return when (djur.sort) {
        "Struts" -> "Struts say kwakwa"
        "Häst" -> "Häst says IIhhhhhhhihihi"
        "Katt" -> "Katt says Meow"
        "Hund" -> "Hund says voff"
        else -> {
            "Okänt läte"
        }
    }
}

fun printInfo() = listOfDjur.map { djur -> djur.printInfo() }
fun printLegs() = listOfDjur.forEach { djur -> println(djur.antalBen) }
fun printType() = listOfDjur.forEach { djur -> println(djur.sort) }


/*
Uppgift 4e – When
Skapa en funktion som tar ett Djur som inparameter. Beroende på Djurets sort ska du skriva ut djurets
läte. Använd en When-sats (Kotlins motsvarighet till Switch)


Uppgift 4a– Klassen Djur
Skapa en klass ”Djur” som har en
• String ”Sort”
• Int ”antalBen”
• Skapa getters och setters för alla variabler
Gör metoder för att skriva ut
• Djurets sort
• Antal ben
• All info om ett djur
I ditt huvudprogram
• Skapa upp ett antal djur
• Skriv ut vilka djur du har och antal ben för varje djur
 */