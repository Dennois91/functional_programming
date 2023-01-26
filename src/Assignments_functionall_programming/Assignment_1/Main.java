package Assignments_functionall_programming.Assignment_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    private final Util util = new Util();
    List<Book> books = util.getBooks();

    public Main() {
        util.createBooks();

        util.getAllBooksByAuthor("J.R.R. Tolkien", books).forEach(book -> System.out.println(book.getTitel()));
        util.getAllBooksByColour("Yellow", books).forEach(book -> System.out.println(book.getTitel()));
        System.out.println(util.countBooksByOwner("Brian Wilson",books));

        System.out.println(util.getAllTitelsOfBooks(books));
        System.out.println(util.getAllAuthors(books));
        System.out.println(util.getAllBookTitelsOwnedBy(books,"Brian Wilson"));


    }

    public static void main(String[] args) {
        new Main();

    }
}
/*
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