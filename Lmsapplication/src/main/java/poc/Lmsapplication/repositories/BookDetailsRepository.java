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
    @Query(value = "{call searchBooksByCategoryAndAge(?1,?2)}", nativeQuery = true)
    public List<Long> getDetailsByAgeAndCategory(String category, int minAge);

}