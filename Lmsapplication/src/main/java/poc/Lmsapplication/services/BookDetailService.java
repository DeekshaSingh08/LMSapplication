package poc.Lmsapplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.exceptionhandling.BookNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.repositories.BookDetailsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * BookDetail service class of API
 *
 * @author deeksha.singh
 */

@Service
@Transactional()
public class BookDetailService {

    Logger logger = LoggerFactory.getLogger(BookDetailService.class);
    @Autowired
    BookDetailsRepository bookDetailsRepository;

    public List<BookDetails> findAllBooks() {
        return bookDetailsRepository.findAll();
    }

    public BookDetails findBookById(Long id) {
        if (!bookDetailsRepository.existsById(id)) {
            logger.error("Cannot Find Book...");
            throw new BookNotFoundException("Book was not found");
        } else {
            logger.info("Finding Book By Id....");
            return bookDetailsRepository.findById(id).orElse(null);
        }
    }
    public BookDetails createBook(BookDetails books) {
        return bookDetailsRepository.save(books);
    }

    public BookDetails updateBook(BookDetails books, long id) {
        if (!bookDetailsRepository.existsById(id)) {
            logger.error("Cannot Find Book...");
            throw new BookNotFoundException("Book was not found");
        } else {
            logger.info("Updating Book Details...");
            BookDetails bookDetails = bookDetailsRepository.findById(id).orElse(books);
            bookDetails.setAuthorName(books.getAuthorName());
            bookDetails.setBookCategory(books.getBookCategory());
            bookDetails.setBookName(books.getBookName());
            bookDetails.setQuantity(books.getQuantity());
            return bookDetailsRepository.save(bookDetails);
        }

    }
    public void deleteBook(Long id) {

        if (!bookDetailsRepository.existsById(id)) {
            throw new BookNotFoundException("book was not found");
        } else {
            bookDetailsRepository.deleteById(id);
        }
    }

    public List<BookDetails> searchBooksByCategoryAndAge(String category, int minAge) {
        List<BookDetails> bookDetails=new ArrayList<>();
        bookDetailsRepository.getDetailsByAgeAndCategory(category,minAge).forEach(n->{
            bookDetails.add(bookDetailsRepository.findById(n).orElse(null));
        });
        return bookDetails;
   }


}
