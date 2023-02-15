package weeks_With_KOTLIN.assignment_4

class Djur(var sort : String, val antalBen : Int) {

 fun printInfo(){
     println("Djuret är av typ $sort och har $antalBen antal ben")
 }
}


/*
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