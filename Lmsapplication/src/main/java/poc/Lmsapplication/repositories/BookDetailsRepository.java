package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.BookDetails;

import java.util.List;

/**
 * BookDetailsRepository of API
 *
 * @author deeksha.singh
 */

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
    public List<BookDetails> findByBookName(String name);
 //   @Query("select * from book_details bd inner join book_category c on bd.book_category=c.category_id where 12<=c.max_age and c.min_age<=12")
//    @Query("SELECT category FROM BookCategory category JOIN category.BookCategory category WHERE category.minAge LIKE %?1%")
//    @Query("select bd from book_details bd join bd.book_category bc where bc.category like '%?%' && bc.min_age >= ?")
    @Query( value = "select * from book_details bd inner join book_category c on bd.book_category=c.category_id where c.min_age<=12",nativeQuery = true)
    public List<BookDetails> findByCategoryAndAge(String category, int minAge);

//book_id,author_name,book_name,quality,quantity,book_category
}