package poc.Lmsapplication.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * BookCategory entity of API
 *
 * @author deeksha.singh
 */

@Entity
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String category;

    private int minAge;
    private int maxAge;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public BookCategory(Long categoryId, String category, int minAge, int maxAge) {
        super();
        this.categoryId = categoryId;
        this.category = category;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public BookCategory() {

        // TODO Auto-generated constructor stub
    }


    public void setId(Long id) {
    }
}
