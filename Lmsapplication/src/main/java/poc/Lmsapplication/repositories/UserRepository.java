package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository of API
 *
 * @author deeksha.singh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    @Query(value = "{call getAdminCommonSearch(?1)}", nativeQuery = true)
    public List<User> getAdminCommonSearch(String search);

    List<User> findByusername(String username);
}