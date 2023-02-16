package poc.Lmsapplication.entities;

import poc.Lmsapplication.Enum.ResponseStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * IssueBook entity of API
 *
 * @author deeksha.singh
 */

@Entity
public class IssueBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @ManyToOne(targetEntity = BookDetails.class)
    @JoinColumn(name = "book_Id", referencedColumnName = "bookId")
    private BookDetails bookDetails;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId")
    private User user;

    private Date issueDate;
    private Date returnDate;
    private Date returnedDate;
    @Enumerated(EnumType.STRING)
    private ResponseStatus responseStatus;

    public IssueBook(Long issueId, BookDetails bookDetails, User user, Date issueDate, Date returnDate, Date returnedDate) {
        this.issueId = issueId;
        this.bookDetails = bookDetails;
        this.user = user;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.returnedDate = returnedDate;

    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public BookDetails getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetails bookDetails) {
        this.bookDetails = bookDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public IssueBook() {

    }


}