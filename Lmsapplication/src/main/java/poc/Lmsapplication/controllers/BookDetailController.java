package poc.Lmsapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.services.BookDetailService;

import java.util.List;

/**
 * BookDetails controller class of API
 *
 * @author deeksha.singh
 */

@RestController
public class BookDetailController {

    @Autowired
    BookDetailService bookDetailService;

    @GetMapping("/book")
    private List<BookDetails> findAllBooks() {
        return bookDetailService.findAllBooks();
    }

    @GetMapping("/book/{id}")
    private BookDetails getBooks(@PathVariable("id") Long id) {

        return bookDetailService.findBookById(id);
    }

    @PostMapping("/saveBook")
    private BookDetails saveBook(@RequestBody BookDetails books) {

        return bookDetailService.createBook(books);

    }

    @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") Long id, @RequestBody BookDetails book) {
        return new ResponseEntity<>(bookDetailService.updateBook(book, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    private ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) {
        bookDetailService.deleteBook(id);
        return new ResponseEntity<>("Book was deleted sucessfully", HttpStatus.OK);

    }
}
