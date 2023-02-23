package poc.Lmsapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookDetailController {

    Logger logger = LoggerFactory.getLogger(BookDetailController.class);
    @Autowired
    BookDetailService bookDetailService;

    @GetMapping("/book")
    public List<BookDetails> findAllBooks() {
        return bookDetailService.findAllBooks();
    }

    @GetMapping("/book/{id}")
    public BookDetails getBooks(@PathVariable("id") Long id) {
        return bookDetailService.findBookById(id);
    }

    @PostMapping("/saveBook")
    public BookDetails saveBook(@RequestBody BookDetails books) {
        logger.info("Saving Book Details..");
        return bookDetailService.createBook(books);

    }

    @GetMapping("/searchBooksByCategory&Age")
    public List<BookDetails> searchBooksByCategoryAndAge(@RequestParam("category") String category,@RequestParam("minAge") int minAge){
        return bookDetailService.searchBooksByCategoryAndAge(category,minAge);
    }

    @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateBook(@PathVariable("id") Long id, @RequestBody BookDetails book) {
        logger.info("Updating Book Details..");
        return new ResponseEntity<>(bookDetailService.updateBook(book, id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) {
        bookDetailService.deleteBook(id);
        return new ResponseEntity<>("Book was deleted successfully", HttpStatus.OK);

    }
}
