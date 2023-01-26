package Assignments_functionall_programming.Assignment_1;

import java.util.ArrayList;
import java.util.List;

public class Util {
    List<Book> books = new ArrayList<>();

    public void createBooks() {
        books.add(new Book("LOTRO", "Tolkien", "Fantasy", "Green", 5, "Brian Wilson", false));
        books.add(new Book("Harry Potter,Chamber of secrets", "J.K.Rowling", "Fantasy", "Red", 4, "Brian Wilson", false));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Novel", "Green", 5, "John Doe", true));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", "Yellow", 4, "Jane Smith", false));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", "Novel", "Blue", 5, "Bob Johnson", true));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", "Novel", "Orange", 3, "Mary Williams", true));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", "Red", 4, "Mike Brown", false));
        books.add(new Book("The Alchemist", "Paulo Coelho", "Fiction", "Gold", 5, "Emily Davis", false));
        books.add(new Book("The Girl on the Train", "Paula Hawkins", "Mystery", "Black", 4, "Daniel Rodriguez", true));
        books.add(new Book("The Art of War", "Sun Tzu", "Strategy", "Yellow", 5, "Jessica Thompson", true));
        books.add(new Book("The Divine Comedy", "Dante Alighieri", "Poetry", "White", 3, "Brian Wilson", true));
        books.add(new Book("The Picture of Dorian Gray", "Oscar Wilde", "Novel", "Silver", 4, "Emily Johnson", true));
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
 */
