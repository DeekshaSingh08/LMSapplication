package poc.Lmsapplication.entities;


import javax.persistence.*;
import java.util.Objects;

/**
 * RequestBookDetail entity of API
 *
 * @author deeksha.singh
 */
@Entity
public class RequestBookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_Id", referencedColumnName = "userId")
    private User user;

    private String bookCategory;

    private String bookName;

    private String authorName;

    public RequestBookDetail() {

    }

    public RequestBookDetail(Long requestId, User user, String bookCategory, String bookName, String authorName) {
        this.requestId = requestId;
        this.user = user;
        this.bookCategory = bookCategory;
        this.bookName = bookName;
        this.authorName = authorName;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "RequestBookDetail{" +
                "requestId=" + requestId +
                ", user=" + user +
                ", bookCategory='" + bookCategory + '\'' +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestBookDetail that = (RequestBookDetail) o;
        return Objects.equals(requestId, that.requestId) && Objects.equals(user, that.user) && Objects.equals(bookCategory, that.bookCategory) && Objects.equals(bookName, that.bookName) && Objects.equals(authorName, that.authorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, user, bookCategory, bookName, authorName);
    }
}
