package poc.Lmsapplication.controllers;

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
public class BookCategoryController {

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
        return bookCategoryService.createCategory(bookCategory);
    }

    @PutMapping("/updateCategory")
    public BookCategory updateCategory(@RequestBody BookCategory bookCategory) {
        return bookCategoryService.updateCategory(bookCategory);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        bookCategoryService.deleteCategory(id);
        return new ResponseEntity<>("Category is deleted successfully", HttpStatus.OK);
    }


}
