package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.User;

/**
 * UserRepository of API
 *
 * @author deeksha.singh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}