package poc.Lmsapplication.dto;

/**
 * RequestBook dto of API
 *
 * @author deeksha.singh
 */

public class RequestBookDto {

    private String bookName;
    private String authorName;
    private String category;

    public RequestBookDto(String bookName, String authorName, String category) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
