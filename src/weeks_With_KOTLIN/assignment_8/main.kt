package weeks_With_KOTLIN.assignment_8

import java.util.*

fun main() {

    textDistortion("Pelle", ::toUpperCase)
    textDistortion("Pelle", ::reverse)
    textDistortion("Pelle123", :: removeAllDigits)
}


fun textDistortion(text: String, action: (String) -> String) {
    val distortedText = action(text)
    println(distortedText)

}

fun toUpperCase(text: String) = text.uppercase(Locale.getDefault())

fun reverse(text: String) = text.reversed()

fun removeAllDigits(text: String) : String{
    return text.filter { c -> !c.isDigit() }
}

/*
Uppgift 8 – Högre Ordningens Funktioner – Text Distortion
Skriv en metod: TextDistortion, som tar in en funktion som i sin tur tar in en String och lämnar ifrån
sig en String. I TextDistorsion anropas funktionen som skickas in som inparameter.
Skriv följande metoder som kan användas som inparametrar till TextDistorsion
• ToUpperCase(String) : String
• RemoveAllDigits(String) : String
 */