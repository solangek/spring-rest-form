package hac.restapi;

import hac.dao.Book;
import hac.dao.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

/*
 * a REST CONTROLLER with the @RestController annotation : returns JSON data
 *  To see this example, Visit the pages:
 * http://localhost:8080/api/42
 * http://localhost:8080/api/author/someone/year/2000
 * If Spring MVC receives a request which doesn't have a mapping,
 * it considers the request not to be allowed and returns
 * a 405 METHOD NOT ALLOWED back to the client.
 */
@RestController
@RequestMapping("/api")
public class SimpleBookRestController {

    /*
    a simple GET request, check out:
    http://localhost:8080/api/42 -> returns some JSON book object
    http://localhost:8080/api/55  -> returns 404
     */
    @GetMapping(value = "/{id}")
    public Book getBook(@PathVariable final Long id) {
        Book b =  BookRepository.findBookById(id);
        if (b == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found"); // valid as of Spring 5 !
        return b;
    }

    /* a multiple param URL that returns a JSON array
    * check out: http://localhost:8080/api/author/someone/year/2000
     */
    @GetMapping(value = "/author/{author}/year/{year}")
    public ArrayList<Book> getBook(@PathVariable final String author, @PathVariable final String year) {
        ArrayList<Book> res =  BookRepository.findBookByAuthorAndYear(author, year);
        return res;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") final Long id) {
        // delete code
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable("id") final Long id, @RequestBody final Book bk) {
        Book b = BookRepository.findBookById(bk.getId());
        if (b != null)
            BookRepository.updateBook(bk);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
    }



}