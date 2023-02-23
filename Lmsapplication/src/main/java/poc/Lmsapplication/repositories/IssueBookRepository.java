package poc.Lmsapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.entities.IssueBook;

import java.util.Date;
import java.util.List;

/**
 * IssueBookRepository of API
 *
 * @author deeksha.singh
 */

@Repository
public interface IssueBookRepository extends JpaRepository<IssueBook, Long> {

    public List<IssueBook> findByReturnDate(Date date);

     List<IssueBook> findByResponseStatus(ResponseStatus responseStatus);

    //List<IssueBook> findResponseById();
}
