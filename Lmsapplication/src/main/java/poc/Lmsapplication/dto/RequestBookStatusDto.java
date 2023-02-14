package poc.Lmsapplication.dto;

import poc.Lmsapplication.Enum.BookRequestStatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * RequestBookStatus dto of API
 *
 * @author deeksha.singh
 */

public class RequestBookStatusDto {

    private String bookName;
    private Date rentalRequestDate;
    private Date statusResponseDate;
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private BookRequestStatusEnum bookRequestStatusEnum;

    public RequestBookStatusDto(String bookName, Date rentalRequestDate, Date statusResponseDate, Date returnDate, BookRequestStatusEnum bookRequestStatusEnum) {
        this.bookName = bookName;
        this.rentalRequestDate = rentalRequestDate;
        this.statusResponseDate = statusResponseDate;
        this.returnDate = returnDate;
        this.bookRequestStatusEnum = bookRequestStatusEnum;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public RequestBookStatusDto(BookRequestStatusEnum bookRequestStatusEnum) {
        this.bookRequestStatusEnum = bookRequestStatusEnum;
    }
}
