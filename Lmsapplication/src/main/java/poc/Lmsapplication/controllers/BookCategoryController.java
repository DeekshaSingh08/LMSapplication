package poc.Lmsapplication.controllers;

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
    private List<BookCategory> findAllCategories() {
        return bookCategoryService.findAllCategories();
    }

    @GetMapping("/category/{id}")
    private List<BookCategory> findCategoryById(@PathVariable("id") Long id) {
        return bookCategoryService.findCategoryById(id);
    }

    @PostMapping("/createCategory")
    private BookCategory createCategory(@RequestBody BookCategory bookCategory) {
        return bookCategoryService.createCategory(bookCategory);
    }

    @PutMapping("/updateCategory")
    private BookCategory updateCategory(@RequestBody BookCategory bookCategory) {
        return bookCategoryService.updateCategory(bookCategory);
    }

    @DeleteMapping("/deleteCategory/{id}")
    private void deleteCategory(@PathVariable("id") Long id) {
        bookCategoryService.deleteCategory(id);
    }


}
