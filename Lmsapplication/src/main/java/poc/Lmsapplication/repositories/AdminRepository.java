package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.AdminDetails;


/**
 * @author deeksha.singh
 */

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails, Long> {



}
