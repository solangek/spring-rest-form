package hac.dao;

import java.util.ArrayList;
import java.util.Arrays;

public class BookRepository {

    /*** our dummy database access ****/

    // declare and initialize with 3 books
    private static ArrayList<Book> list = new ArrayList<>(Arrays.asList(
            new Book(1L, "J.K. Rowling", "Harry Potter and the Philosopher's Stone (1997)"),
            new Book(2L, "J.K. Rowling", "Harry Potter and the Chamber of Secrets (1998)"),
            new Book(42L, "Douglas Adamas", "Hitchhiker's guide to the galaxy (2000)"),
            new Book(3L, "someone", "A book (2000)")

            ));

    public static Book findBookById(Long id) {
        Book book = null;
        // some code to find a book in our database
        for (Book b : list) {
            if (b.getId().equals(id)) {
                book = b;
                break;
            }
        }
        return book;
    }

    public static Book updateBook(Book b) {
        // find and update a book in our database
        for (Book book : list) {
            if (book.getId().equals(b.getId())) {
                book.setAuthor(b.getAuthor());
                book.setTitle(b.getTitle());
                break;
            }
        }

        return b;
    }

    public  static ArrayList<Book> getAllBooks() {
        return list;
    }

    public static ArrayList<Book> findBookByAuthorAndYear(String author, String year) {
        // this code is only for building a random response
        // in reality it should search some database/datastructure

        // find all books by author and year
        ArrayList<Book> res = new  ArrayList<>();
        for (Book b : list) {
            if (b.getAuthor().equals(author) && b.getTitle().contains(year)) {
                res.add(b);
            }
        }

        return res;
    }

    public static void addBook(Book bk) {
        // generate some id to add a new book to our database
        bk.setId((long) list.size());
        list.add(bk);
    }
}
