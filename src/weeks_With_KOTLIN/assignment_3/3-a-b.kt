package weeks_With_KOTLIN.assignment_3

import java.lang.Exception
import java.lang.NumberFormatException

var loop: Boolean = true
var generatedNumber = generateRandom()
fun main() {

    while (loop) {
        guessNumber(generatedNumber)
    }
}

fun generateRandom() = (1..10).random()

fun guessNumber(random: Int) {
    println("Guess a number from 1-10")
    try {
        val input = readLine()!!.toInt()

        if (input > random)
            ifToHigh(input)
        else if (input < random)
            ifToLow(input)
        else {
            ifCorrect(random)
            loop = askForAnotherGame()
        }

    } catch (e: NumberFormatException) {
        println("Not a valid input. use numbers only!")

    } catch (e: Exception) {
        println("Unexpected error!")
    }
}

fun ifToLow(guess: Int) {
    println(" you guessed $guess but the number is higher then that!")
}

fun ifToHigh(guess: Int) {
    println(" you guessed $guess but the number is lower then that!")
}

fun ifCorrect(guess: Int) {
    println("you guessed correct! The number was $guess")
}

fun askForAnotherGame(): Boolean {
    println("Do you want to play again? enter Y or N")
    return when (readLine()!!.uppercase()) {
        "Y" -> {
            generatedNumber = generateRandom()
            println("New game started")
            true
        }

        "N" -> {
            println("Good bye")
            false
        }
        else -> {
            println("Invalid input")
            askForAnotherGame()
        }
    }
}

/*
Uppgift 3a - Gissa talet
Gör ett litet program där du väljer ut ett random värde mellan 1 och 10.
Låt användaren gissa talet
Skriv ut ”Högre” eller ”Lägre” tills användaren har gissat rätt.
Fråga sen användaren om hen vill spela igen
Uppgift 3b - Gissa talet, med exceptions
När ni anropar readLine()!!.toInt(), vid inläsning av tal, finns risk för att NumberFormat Exception
kommer att kastas. Detta sker om det som skrivs in inte är en siffra.
Lägg till ett try-catch-block som fångar NumberFormatException och säger åt användaren att skriva in
en siffra när detta fel har inträffat
Kolla att ditt spel funkar fint och att användaren får sitt felmeddelande och kan fortsätta spela även om
NumberFormatException har inträffat

*/
