package Assignments_functionall_programming.Assignment_1;

public class Main {
private Util util = new Util();
    public Main() {
        System.out.println("hello");

    }

    public static void main(String[] args) {

    }
}
/*
Skapa en klass Book, som minst innehåller följande medlemsvariabler (med getters och setters):
• Titel
• Författare
• Genre
• Färg
• Betyg (int)
• Ägare
• Fakta eller fiction (boolean)
Skapa en Util-klass där du skapar upp ett gäng böcker och lägger dessa i en lista. Skapa sedan funktion
getList där du returnerar din boklista. Se till att ha några böcker med samma författare, några med
samma färg, olika betyg och olika ägare till böckerna i listan.
Skriv följande funktioner som alla ska använda lambda-metoden filter:
• En funktion som söker ut alla böcker av en viss författare
• En funktion som söker ut alla böcker av en viss färg
• En funktion som räknar antal böcker som tillhör dig


Uppgift 1b– Basic strömmar, map
Jobba vidare med din bok-lista.
Skriv följande funktioner som alla ska använda lambda-metoden map:
• En funktion som returnerar en lista på alla titlar som dina böcker har
• En funktion som returnerar en lista på alla författare som dina böcker har
o Vi vill inte ha några dubletter i listan
• En funktion som listar alla titlar, men bara på böcker som tillhör dig

 */