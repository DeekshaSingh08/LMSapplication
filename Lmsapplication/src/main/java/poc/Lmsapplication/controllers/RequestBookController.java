package poc.Lmsapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poc.Lmsapplication.dto.RequestBookDto;
import poc.Lmsapplication.entities.RequestBookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.services.RequestBookServices;
import poc.Lmsapplication.services.UserService;

import java.util.List;

/**
 * RequestBook controller class of API
 *
 * @author deeksha.singh
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestBookController {

    Logger logger = LoggerFactory.getLogger(RequestBookController.class);
    @Autowired
    private RequestBookServices requestbookservices;

    @PostMapping("/addRequest/{id}")
    public RequestBookDetail addRequest(@RequestBody RequestBookDto requestBookDto, @PathVariable("id") long id) {
        logger.info("Book request submitted...");
        return requestbookservices.addRequest(requestBookDto, id);
    }

    @GetMapping("/getRequests")
    public List<RequestBookDetail> getRequests() {
        return requestbookservices.getAllRequests();
    }

}
