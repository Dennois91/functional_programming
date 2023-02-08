package Weeks_With_JAVA.Assignments_functionall_programming.Assignment_1;

import java.util.List;

public class Main {
    private final Util util = new Util();
    List<Book> books = util.getBooks();

    public Main() {
        util.createBooks();

        //   util.getAllBooksByAuthor("J.R.R. Tolkien", books).forEach(book -> System.out.println(book.getTitel()));
        //   util.getAllBooksByColour("Yellow", books).forEach(book -> System.out.println(book.getTitel()));
        //   System.out.println(util.countBooksByOwner("Brian Wilson",books));

        //   System.out.println(util.getAllTitelsOfBooks(books));
        //   System.out.println(util.getAllAuthors(books));
        //   System.out.println(util.getAllBookTitelsOwnedBy(books,"Brian Wilson"));

        //   util.getTitelsOfAllBooksInListOfBooks(util.listOfBookLists);
        //   System.out.println(util.getMedianOfRaitings(books));
        //   System.out.println(util.concatAllTitelsToString(books));
        //   System.out.println(util.getMedianOfRaitingsTwo(books));
        //   System.out.println(util.getRedBooksOwnedBy(books, "Brian Wilson"));
        //   util.printBooksInTitelOrder(books);
        //   util.printBooksByRating(books);
        //   util.printAllBooksTitelsInOneString(books);

    }

    public static void main(String[] args) {
        new Main();

    }
}
/*
Skriv följande funktioner som alla ska använda sig av primitiva variabler eller anymatch:
• En funktion som räknar ut medelvärdet av dina betyg
• En funktion som räknar antalet böcker i listan som tillhör dig OCH är röda (välj nån färg och
ägare som du får träffar på)
• Skriv en funktion som ger sant om det finns några böcker av Tim Ferriss (eller annan valfri
författare) i din lista

Skriv följande funktioner som alla ska använda lambda-metoden reduce:
• En funktion som räknar ut medelvärdet av dina betyg
• En funktion som slår ihop alla titlar till en jättelång sträng. Det ska finnas ”, ”mellan varje
titel

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