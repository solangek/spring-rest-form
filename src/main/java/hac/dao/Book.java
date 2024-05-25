package hac.dao;

import java.io.Serializable;

/**
 * this is our data aka the model (later replaced by a database)
 * we perform some basic validation here to ensure that the data is correct
 */
public class Book implements Serializable {

    private Long id;
    private String author;
    private String title;

    public Book() {
    }

    private void checkNotNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Null argument");
        }
    }

    public void checkNotEmpty(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException("Empty argument");
        }
    }

    public Book(Long id, String author, String title) {
        checkNotNull(id);
        checkNotEmpty(author);
        checkNotEmpty(title);
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        checkNotNull(id);
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        checkNotEmpty(author);
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        checkNotEmpty(title);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", author=" + author + ", title=" + title + "]";
    }

}
