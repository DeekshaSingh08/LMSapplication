package poc.Lmsapplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.Lmsapplication.controllers.BookCategoryController;
import poc.Lmsapplication.entities.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.exceptionhandling.BookNotFoundException;
import poc.Lmsapplication.repositories.BookCategoryRepository;

import java.util.List;

/**
 * BookCategory service class of API
 *
 * @author deeksha.singh
 */
@Service
public class BookCategoryService {

    Logger logger = LoggerFactory.getLogger(BookCategoryService.class);
    @Autowired
    BookCategoryRepository bookCategoryRepository;

    public List<BookCategory> findAllCategories() {
        return bookCategoryRepository.findAll();
    }

    public List<BookCategory> findCategoryById(Long id) {
        return (List<BookCategory>) bookCategoryRepository.findById(id).orElse(null);
    }

    public BookCategory createCategory(BookCategory category) {
        return bookCategoryRepository.save(category);
    }

    public BookCategory updateCategory(Long id, BookCategory category) {
        if (!bookCategoryRepository.existsById(id)) {
            logger.error("Cannot Find Book Category...");
            throw new BookNotFoundException("Book Category was not found");
        } else {
            logger.info("Updating Book Category....");
            BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(null);
            bookCategory.setCategory(category.getCategory());
            bookCategory.setMaxAge(category.getMaxAge());
            bookCategory.setMinAge(category.getMinAge());
            return bookCategoryRepository.save(bookCategory);
        }
    }

    public void deleteCategory(Long id) {
        bookCategoryRepository.deleteById(id);
    }

}
