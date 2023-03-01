package poc.Lmsapplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(IssueBookService.class);
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
            logger.error("BookId and UserId not found...");
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
                logger.info("Sending Issue Request...");
                return "Requested Successfully !";
            } else if (book.getQuantity() == 0) {
                logger.info("Book is not available...");
                return "Sorry Book is not available !";
            } else {
                return null;
            }
        }

    }

    public String addResponse(Long issueId, int responseStatus) {
        IssueBook issueBook = issueBookRepository.findById(issueId).orElse(null);
        if (issueId == null) {
            logger.error("Request doesn't exits...");
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
                logger.info("Request Approved...");
                return "Request Approved !";
            } else if (responseStatus == 2) {

                issueBook.setResponseStatus(ResponseStatus.values()[2]);
                issueBook.setIssueDate(null);
                issueBook.setReturnDate(null);
                issueBook.setReturnedDate(null);
                issueBookRepository.save(issueBook);
                logger.info("Request Rejected...");
                return "Request Rejected !";
            }
        } else {
            logger.info("Request Cancelled...");
            return "Request Cancelled";
        }
        logger.info("Request Reviewed...");
        return "Request Reviewed !";
    }

    public List<IssueBook> getAllIssues() {
        return issueBookRepository.findAll();
    }

    public List<IssueBook> getApprovedIssues() {
          return issueBookRepository.findByResponseStatus(ResponseStatus.APPROVED);
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
                logger.info("In catch block...");
                e.printStackTrace();
            }
            issueBookRepository.save(issueBook);
            logger.info("Book returned successfully...");
            return "Returned Successfully!";
        } else {
            logger.error("No issued book found...");
            return "No Issued Book Found!";
        }
    }
    public List<IssueBook> findAllBookOverdue() {

        Date currentDate = new Date();
        List<IssueBook> books = new ArrayList<>();
        issueBookRepository.findAll().forEach(n -> {

            if (n.getReturnedDate() == null) {
                if ((n.getReturnDate().before(currentDate))) {
                    books.add(n);
                }
            }
        });
        return books;
    }

}
