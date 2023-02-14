package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.RequestBookDetail;

/**
 * RequestBookDetailRepository of API
 *
 * @author deeksha.singh
 */
@Repository
public interface RequestBookDetailRepository extends JpaRepository<RequestBookDetail, Long> {

}