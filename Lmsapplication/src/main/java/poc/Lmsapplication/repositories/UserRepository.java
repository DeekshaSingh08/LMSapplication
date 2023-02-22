package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.User;

import java.util.List;

/**
 * UserRepository of API
 *
 * @author deeksha.singh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "{call getAdminCommonSearch(?1)}", nativeQuery = true)
    public List<User> getAdminCommonSearch(String search);

}