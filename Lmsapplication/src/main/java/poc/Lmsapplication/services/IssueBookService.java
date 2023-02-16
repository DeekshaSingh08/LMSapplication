package poc.Lmsapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poc.Lmsapplication.Enum.ResponseStatus;
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
        User user = userRepository.findById(userid).orElse(null);
        BookDetails book = bookDetailsRepository.findById(bookid).orElse(null);

        if (book == null || user == null) {
            return "Enter BookId and UserId !";
        } else {
            if (book.getQuantity() != 0) {

                IssueBook bookIssueDetails = new IssueBook();
                bookIssueDetails.setBookDetails(book);
                bookIssueDetails.setUser(user);
                bookIssueDetails.setResponseStatus(ResponseStatus.values()[0]);
                bookIssueDetails.setIssueDate(null);
                bookIssueDetails.setReturnDate(null);
                bookIssueDetails.setReturnedDate(null);
                issueBookRepository.save(bookIssueDetails);
                return "Requested Successfully !";
            } else if (book.getQuantity() == 0) {
                return "Sorry Book is not available !";
            } else {
                return null;
            }
        }

    }

    public String addResponse(Long issueId, int responseStatus) {
        IssueBook issueBook = issueBookRepository.findById(issueId).orElse(null);
        if (issueId == null) {
            return "Request does not exits !";
        } else if (issueId != null) {
            if (responseStatus == 1) {
                BookDetails bookDetails = issueBook.getBookDetails();
                bookDetails.setQuantity(bookDetails.getQuantity() - 1);
                bookDetailsRepository.save(bookDetails);
                issueBook.setResponseStatus(ResponseStatus.values()[1]);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                LocalDateTime localDateTime = LocalDateTime.now();
                try {
                    issueBook.setIssueDate(formatter.parse(localDateTime.toString()));
                    issueBook.setReturnDate(formatter.parse(localDateTime.plusDays(7).toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                issueBook.setReturnedDate(null);
                issueBookRepository.save(issueBook);
                return "Request Approved !";
            } else if (responseStatus == 2) {

                issueBook.setResponseStatus(ResponseStatus.values()[2]);
                issueBook.setIssueDate(null);
                issueBook.setReturnDate(null);
                issueBook.setReturnedDate(null);
                issueBookRepository.save(issueBook);
                return "Request Rejected !";

            }
        } else {
            return "Request Cancelled";
        }

        return "Request Reviewed !";
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
            return "Return successfully!";
        } else {
            return "First take the book !";
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
