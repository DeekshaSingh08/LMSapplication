package poc.Lmsapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.entities.IssueBook;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.repositories.BookDetailsRepository;
import poc.Lmsapplication.repositories.IssueBookRepository;
import poc.Lmsapplication.repositories.UserRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IssueBook service class of API
 *
 * @author deeksha.singh
 *
 */
@Service
public class IssueBookService {

    @Autowired
    private IssueBookRepository issueBookRepository;

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional()
    public String addIssue(long bookid, long userid) {
        User user = userRepository.findById(bookid).orElse(null);
        BookDetails book = bookDetailsRepository.findById(userid).orElse(null);

        if (book == null || user == null) {
            return "sorry you can't!!!";
        } else {
            if (book.getQuantity() != 0) {

                book.setQuantity(book.getQuantity() - 1);
                bookDetailsRepository.save(book);
//                if(true){
//                    throw new RuntimeException("demo");
//                }
                IssueBook bookIssueDetails = new IssueBook();
                bookIssueDetails.setBookDetails(book);
                bookIssueDetails.setUser(user);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDateTime localDateTime = LocalDateTime.now();
                try {
                    bookIssueDetails.setIssueDate(formatter.parse(localDateTime.toString()));
                    bookIssueDetails.setReturnDate(formatter.parse(localDateTime.plusDays(7).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                bookIssueDetails.setReturnedDate(null);
                issueBookRepository.save(bookIssueDetails);
                return "success";
            } else if (book.getQuantity() == 0) {
                return "sorry Book is not available!!!";
            } else {
                return null;
            }
        }


    }


    public List<IssueBook> getAllIssues() {
        return issueBookRepository.findAll();
    }


    public String returnBook(long issueId) {
        IssueBook issueBook = issueBookRepository.findById(issueId).orElse(null);
        if (issueBook != null) {
            BookDetails bookDetails = issueBook.getBookDetails();
            bookDetails.setQuantity(bookDetails.getQuantity() + 1);
            bookDetailsRepository.save(bookDetails);
            LocalDateTime localDateTime = LocalDateTime.now();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                issueBook.setReturnedDate(formatter.parse(localDateTime.toString()));
            } catch (ParseException e) {

                e.printStackTrace();
            }
            issueBookRepository.save(issueBook);
            return "success";
        } else {
            return "first take the book";
        }

    }

    public List<IssueBook> findAllBookOverdue() {

        Date curentDate = new Date();
        List<IssueBook> books = new ArrayList<>();
        issueBookRepository.findAll().forEach(n -> {

            if (n.getReturnedDate() == null) {
                if (!(n.getReturnDate().before(curentDate))) {
                    books.add(n);
                }

            }
        });

        return books;

    }

}
