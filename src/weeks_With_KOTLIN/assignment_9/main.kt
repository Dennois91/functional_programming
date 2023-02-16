package weeks_With_KOTLIN.assignment_9


fun main() {

    data class Person(val name: String, val age: Int)

    val people = listOf(
        Person("Pelle", 25),
        Person("Putte", 30),
        Person("Kalle", 20)
    )

    val strings = listOf("katt", "hund", "apa", "tiger")
    val numbers = listOf(1, 5, 8, 10, 2)

    strings.myFilter { it.contains("t") }.forEach { println(it) }
    numbers.myFilter { it < 5 }.forEach { println(it) }
    people.myFilter { it.age >= 25 }.forEach { println(it.name) }

    strings.myMap { it.uppercase() }.forEach { println(it) }
    numbers.myMap { it * 2 }.forEach { println(it) }
    people.myMap { it.name.plus(" Plutt") }.forEach { println(it) }

}

fun <T> List<T>.myFilter(predicate: (T) -> Boolean): List<T> {

    val result = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            result.add(item)
        }
    }
    return result
}

fun <T, S> List<T>.myMap(function: (T) -> (S)): List<S> {
    val res: MutableList<S> = mutableListOf()
    for (element in this) {
        res += function(element)
    }
    return res
}


/*
Uppgift 9 – Högre Ordningens Funktioner – Generiska funktioner
• 9:a Skriv en egen version av funktionen ”filter”

• 9b Skriv en egen version av funktionen ”map”
 o Använder inte predicate utan function (T) -> (T)
   I ditt main-program, skapa upp en lista och se att det funkar att göra filter eller map på listan
   Om generics känns svårt, börja med att definiera upp map och filter för listor som bara tar t.ex Int
   eller String
 o Nästa steg är att kunna hantera alla sorters objekt
 */