package poc.Lmsapplication.entities;

import javax.persistence.*;

/**
 * BookDetails entity of API
 *
 * @author deeksha.singh
 */

@Entity
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private int quantity;
    private String authorName;


    @ManyToOne(targetEntity = BookCategory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_category", referencedColumnName = "categoryId")
    private BookCategory bookCategory;


    public Long getBookId() {
        return bookId;
    }


    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }


    public String getBookName() {
        return bookName;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getAuthorName() {
        return authorName;
    }


    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public BookCategory getBookCategory() {
        return bookCategory;
    }


    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }


    public BookDetails() {

        // TODO Auto-generated constructor stub
    }


    public BookDetails(Long bookId, String bookName, int quantity, String authorName, BookCategory bookCategory) {

        this.bookId = bookId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.authorName = authorName;
        this.bookCategory = bookCategory;
    }


    public void setId(Long id) {

    }
}
