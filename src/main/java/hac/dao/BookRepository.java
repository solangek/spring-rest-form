package hac.dao;

import java.util.ArrayList;

public class BookRepository {

    /*** our database access ****/

    public static Book findBookById(Long id) {
        Book book = null;
        if (id == 42) {
            book = new Book();
            book.setId(id);
            book.setAuthor("Douglas Adamas");
            book.setTitle("Hitchhiker's guide to the galaxy");
        }
        return book;
    }

    public static Book updateBook(Book b) {
        // some code to update an existing book on our daatabase
        return b;
    }

    public static ArrayList<Book> findBookByAuthorAndYear(String suthor, String year) {
        // this code is only for building a random response
        // in reality it should search some database/datastructure

        ArrayList<Book> list = new  ArrayList<Book>();
        list.add(new Book(new Long(123), "Random Author1", "Random title1"));
        list.add(new Book(new Long(234), "Random Author2", "Random title2"));
        return list;
    }
}
