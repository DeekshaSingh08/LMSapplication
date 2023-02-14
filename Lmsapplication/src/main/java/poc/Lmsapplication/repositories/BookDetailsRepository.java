package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
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


}