package poc.Lmsapplication.services;

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
public class BookDetailService {

    @Autowired
    BookDetailsRepository bookDetailsRepository;

    public List<BookDetails> findAllBooks() {
        return bookDetailsRepository.findAll();
    }

    public BookDetails findBookById(Long id) {
        if (!bookDetailsRepository.existsById(id)) {
            throw new BookNotFoundException("book was not found");
        } else {
            return bookDetailsRepository.findById(id).orElse(null);
        }
    }

    public BookDetails createBook(BookDetails books) {
        return bookDetailsRepository.save(books);
    }

    public BookDetails updateBook(BookDetails books, long id) {
        if (!bookDetailsRepository.existsById(id)) {
            throw new BookNotFoundException("book was not found");
        } else {
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
      //return bookDetailsRepository.getDetailsByAgeAndCategory(category,minAge);
   }


}
