package main;

import apps.Monitor;

public class Main {
    Monitor monitor = new Monitor();
    Thread thread = new Thread(monitor);

    public Main() {
            thread.start();
            System.out.println("Thred started");
    }

    public static void main(String[] args) {
        new Main();
    }
}
/*
Uppgift 1 – Gör en console-dashboard till tomten
Tomten behöver hålla koll på läget. Gör därför ett java-program som läser ut antalet barn, antalet
nissar, namnen på alla nissarna och medelvärdet på ”snällhet” (alt hur många barn, procentuellt sett är
snälla) och skriver ut dessa värden i consolen, med repetition varje 2 sekunder.

Uppgift 2a – Skriv in namnet på ett barn, få veta naughty or nice
Tomten känner att det inte räcker att veta genomsnittet för barnens snällhet utan har även behov att få
veta snällheten för varje barn för sig
Skriv ett javaprogram där tomten kan skriva in ett barns namn, sedan slås snällheten upp i databasen
och skrivs ut på consolen

 */