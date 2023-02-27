package poc.Lmsapplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.Lmsapplication.dto.RequestBookDto;
import poc.Lmsapplication.entities.RequestBookDetail;
import poc.Lmsapplication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.repositories.BookDetailsRepository;
import poc.Lmsapplication.repositories.RequestBookDetailRepository;
import poc.Lmsapplication.repositories.UserRepository;

import java.util.List;

/**
 * RequestBook service class of API
 *
 * @author deeksha.singh
 */

@Service
public class RequestBookServices {

    Logger logger = LoggerFactory.getLogger(RequestBookServices.class);
    @Autowired
    private RequestBookDetailRepository requestBookDetailRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    public RequestBookDetail addRequest(RequestBookDto requestBookDto, long id) {
        RequestBookDetail requestBookDetail = new RequestBookDetail();

        User user = userRepository.findById(id).orElse(null);
        int size = bookDetailsRepository.findByBookName(requestBookDto.getBookName()).size();
        if (size == 0 & user != null) {
            requestBookDetail.setBookCategory(requestBookDto.getCategory());
            requestBookDetail.setAuthorName(requestBookDto.getAuthorName());
            requestBookDetail.setBookName(requestBookDto.getBookName());
            requestBookDetail.setUser(user);
            logger.info("Book Requested Successfully...");
            return requestBookDetailRepository.save(requestBookDetail);
        } else {
            logger.info("Cannot Create Request...");
            return null;

        }
    }

    public List<RequestBookDetail> getAllRequests() {
        return requestBookDetailRepository.findAll();
    }

}
