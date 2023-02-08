package Weeks_With_JAVA.Assignments_functionall_programming.Assignment_1;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class Util {
    List<Book> books = new ArrayList<>();
    List<Book> booksByTolkien = new ArrayList<>();
    List<Book> booksByRowinlg = new ArrayList<>();
    List<List<Book>> listOfBookLists = new ArrayList<>();

    public void createBooks() {
        books.add(new Book("LOTRO", "J.R.R. Tolkien", "Fantasy", "Green", 5, "Brian Wilson", false));
        books.add(new Book("Harry Potter,Chamber of secrets", "J.K. Rowling", "Fantasy", "Red", 4, "Brian Wilson", false));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Novel", "Green", 5, "John Doe", false));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", "Yellow", 4, "Jane Smith", false));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Novel", "Blue", 5, "Bob Johnson", false));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Novel", "Orange", 3, "Mary Williams", false));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "Red", 4, "Mike Brown", false));
        books.add(new Book("The Alchemist", "Paulo Coelho", "Fiction", "Gold", 5, "Emily Davis", false));
        books.add(new Book("The Girl on the Train", "Paula Hawkins", "Mystery", "Black", 4, "Daniel Rodriguez", true));
        books.add(new Book("The Art of War", "Sun Tzu", "Strategy", "Yellow", 5, "Jessica Thompson", true));
        books.add(new Book("The Divine Comedy", "Dante Alighieri", "Poetry", "White", 3, "Brian Wilson", true));
        books.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", "Novel", "Silver", 4, "Emily Johnson", true));

        booksByTolkien.add(books.get(0));
        booksByTolkien.add(books.get(6));
        booksByRowinlg.add(books.get(1));
        booksByRowinlg.add(books.get(3));
        listOfBookLists.add(booksByTolkien);
        listOfBookLists.add(booksByRowinlg);
    }

    public void printAllBooksTitelsInOneString(List<Book> books) {
        System.out.println(books.stream().
                map(Book::getTitel).
                collect(Collectors.joining(", ", "Start of string:  ", " End of string")));
    }

    public void printBooksByRating(List<Book> books) {
        books.stream().sorted(Comparator.comparingInt(Book::getRating)).
                forEach(a -> System.out.println(a.getTitel()));
    }

    public void printBooksInTitelOrder(List<Book> books) {
        Collator collator = Collator.getInstance(new Locale("sv", "SV"));
        books.stream().map(Book::getTitel).sorted(collator).
                forEach(System.out::println);
    }

    public String concatAllTitelsToString(List<Book> books) {
        return books.isEmpty() ? "No books in list" : books.stream().
                map(Book::getTitel).
                reduce((str, elem) -> str + " ," + elem).
                orElse("");
    }

    public float getMedianOfRaitings(List<Book> books) {
        return books.isEmpty() ? 0f : (float) books.stream().
                map(Book::getRating).
                reduce(0, Integer::sum) / books.size();
    }

    public double getMedianOfRaitingsTwo(List<Book> books) {
        return books.isEmpty() ? 0.0 : books.stream().
                map(Book::getRating).
                mapToInt(e -> e).
                average().
                orElse(0.0);
    }

    public int getRedBooksOwnedBy(List<Book> books, String ownerName) {
        return (int) books.stream().
                filter(book -> book.getOwner().equals(ownerName)).
                filter(book -> book.getColour().equals("Red")).
                count();
    }

    public void getTitelsOfAllBooksInListOfBooks(List<List<Book>> ListOfListOfBooks) {
        listOfBookLists.stream().flatMap(Collection::stream).
                forEach(book -> System.out.println(book.getTitel()));
    }

    public List<String> getAllBookTitelsOwnedBy(List<Book> list, String owner) {
        return list.stream().filter(Book -> Book.getOwner().equalsIgnoreCase(owner)).
                map(Book::getTitel).
                collect(Collectors.toList());
    }

    public List<String> getAllAuthors(List<Book> list) {
        return list.stream().map(Book::getAuthor).
                distinct().
                toList();
    }

    public List<String> getAllTitelsOfBooks(List<Book> list) {
        return list.stream().map(Book::getTitel).
                toList();
    }

    public long countBooksByOwner(String owner, List<Book> list) {
        return list.stream().filter(s -> s.getOwner().equalsIgnoreCase(owner)).
                count();
    }

    public List<Book> getAllBooksByColour(String colour, List<Book> list) {
        return list.stream().filter(s -> s.getColour().equalsIgnoreCase(colour))
                .toList();
    }

    public List<Book> getAllBooksByAuthor(String author, List<Book> list) {
        return list.stream().
                filter(s -> s.getAuthor().equalsIgnoreCase(author)).
                toList();
    }


    public List<Book> getBooks() {
        return books;
    }
}
/*
• Titel
• Författare
• Genre
• Färg
• Betyg (int)
• Ägare
• Fakta eller fiction (boolean)

  Skriv följande funktioner som alla ska använda sig av primitiva variabler eller anymatch:
• En funktion som räknar ut medelvärdet av dina betyg
• En funktion som räknar antalet böcker i listan som tillhör dig OCH är röda (välj nån färg och
ägare som du får träffar på)
• Skriv en funktion som ger sant om det finns några böcker av Tim Ferriss (eller annan valfri
författare) i din lista


  Skriv följande funktioner som alla ska använda lambda-metoden reduce:
• En funktion som räknar ut medelvärdet av dina betyg
• En funktion som slår ihop alla titlar till en jättelång sträng. Det ska finnas ”, ”mellan varje titel

En funktion som returnerar en lista på alla titlar som dina böcker har
En funktion som returnerar en lista på alla författare som dina böcker har
Vi vill inte ha några dubletter i listan
En funktion som listar alla titlar, men bara på böcker som tillhör dig
En funktion som returnerar en lista på alla titlar av böcker som finns i listorna i din boklist

Skriv följande funktioner som alla ska använda sig av sortering:
• En funktion som skriver ut böckerna i rating-ordning
• En funktion som skriver ut titlarna i (svensk) bokstavsordning

    Skriv följande funktioner som alla ska använda sig av joining:
    • En funktion som gör en jättelång sträng av alla titlarna, med ”, ”mellan varje bok, men inte
    före första eller efter sista titeln


*/
