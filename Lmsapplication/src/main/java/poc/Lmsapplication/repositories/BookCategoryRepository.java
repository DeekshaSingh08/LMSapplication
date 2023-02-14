package poc.Lmsapplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.BookCategory;

/**
 * BookCategoryRepository of API
 *
 * @author deeksha.singh
 */

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}