package poc.Lmsapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import poc.Lmsapplication.entities.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.services.BookCategoryService;

import java.util.List;

/**
 * BookCategory controller class of API
 *
 * @author deeksha.singh
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BookCategoryController {

    Logger logger = LoggerFactory.getLogger(BookCategoryController.class);
    @Autowired
    BookCategoryService bookCategoryService;

    @GetMapping("/category")
    public List<BookCategory> findAllCategories() {
        return bookCategoryService.findAllCategories();
    }

    @GetMapping("/category/{id}")
    public List<BookCategory> findCategoryById(@PathVariable("id") Long id) {
        return bookCategoryService.findCategoryById(id);
    }

    @PostMapping("/createCategory")
    private BookCategory createCategory(@RequestBody BookCategory bookCategory) {
        logger.info("Saving Category Details..");
        return bookCategoryService.createCategory(bookCategory);
    }

    @PutMapping("/updateCategory")
    public BookCategory updateCategory( @PathVariable Long id , @RequestBody BookCategory bookCategory) {
        logger.info("Updating Category Details..");
        return bookCategoryService.updateCategory(id,bookCategory);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        bookCategoryService.deleteCategory(id);
        return new ResponseEntity<>("Category is deleted successfully", HttpStatus.OK);
    }


}
