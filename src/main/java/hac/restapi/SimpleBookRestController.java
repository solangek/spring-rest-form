package hac.restapi;

import hac.dao.Book;
import hac.dao.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

/*
 * a REST CONTROLLER with the @RestController annotation : returns JSON data.
 * Based on PathVariable, RequestMapping, GetMapping, PostMapping, PutMapping, DeleteMapping annotations
 *
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

    private static final Logger logger = LogManager.getLogger(SimpleBookRestController.class);


    @GetMapping(value = "")
    public ArrayList<Book> getRoot() {
        return BookRepository.getAllBooks();
    }
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

    @PostMapping(value = "")
    public ResponseEntity<Book> create(@RequestBody final Book bk) {
        BookRepository.addBook(bk);
        return ResponseEntity.ok(bk);
    }

    /* a multiple param URL that returns a JSON array
    * check out: http://localhost:8080/api/author/someone/year/2000
     */
    @GetMapping(value = "/author/{author}/year/{year}")
    public ArrayList<Book> getBook(@PathVariable final String author, @PathVariable final String year) {
        ArrayList<Book> res =  BookRepository.findBookByAuthorAndYear(author, year);
        logger.info("getBook: " + res);
        return res;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") final Long id) {
        // delete code

        // return a response entity: a class that encapsulates the HTTP response
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") final Long id, @RequestBody final Book bk) {
        Book b = BookRepository.findBookById(bk.getId());
        if (b != null) {
            BookRepository.updateBook(bk);
            return  ResponseEntity.ok(HttpStatus.OK);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
    }

    /** a special error handler for MethodArgumentTypeMismatchException such as /api/author/someone/year/2000a,
     * see https://www.baeldung.com/exception-handling-for-rest-with-spring
     * @param ex - the exception
     * @return a response entity with a bad request
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
    }

}
