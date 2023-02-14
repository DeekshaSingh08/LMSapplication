package poc.Lmsapplication.entities;

import poc.Lmsapplication.Enum.BookRequestStatusEnum;

import java.util.Date;
import javax.persistence.*;

/**
 * RequestBookStatus entity of API
 *
 * @author deeksha.singh
 */

@Entity
public class RequestBookStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestBookStatusId;

    @ManyToOne(targetEntity = BookDetails.class)
    @JoinColumn(name = "book_Id", referencedColumnName = "bookId")
    private BookDetails bookDetails;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId")
    private User user;
    private Date rentalRequestDate;
    private Date statusResponseDate;
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private BookRequestStatusEnum bookRequestStatusEnum;

    public RequestBookStatus() {
    }

    public Long getRequestBookStatus() {
        return requestBookStatusId;
    }

    public void setRequestBookStatus(Long requestBookStatus) {
        requestBookStatusId = requestBookStatus;
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

    public Date getRentalRequestDate() {
        return rentalRequestDate;
    }

    public void setRentalRequestDate(Date rentalRequestDate) {
        this.rentalRequestDate = rentalRequestDate;
    }

    public Date getStatusResponseDate() {
        return statusResponseDate;
    }

    public void setStatusResponseDate(Date statusResponseDate) {
        this.statusResponseDate = statusResponseDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public RequestBookStatus(Long requestBookStatus, BookDetails bookDetails, User user, Date rentalRequestDate,
                             Date statusResponseDate, Date returnDate, BookRequestStatusEnum bookRequestStatusEnum) {
        this.requestBookStatusId = requestBookStatus;
        this.bookDetails = bookDetails;
        this.user = user;
        this.rentalRequestDate = rentalRequestDate;
        this.statusResponseDate = statusResponseDate;
        this.returnDate = returnDate;
        this.bookRequestStatusEnum = bookRequestStatusEnum;
    }

    public RequestBookStatus(BookRequestStatusEnum bookRequestStatusEnum) {

        this.bookRequestStatusEnum = bookRequestStatusEnum;
    }

}
