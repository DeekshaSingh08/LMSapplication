package poc.Lmsapplication.controllers;

import poc.Lmsapplication.dto.RequestBookDto;
import poc.Lmsapplication.entities.RequestBookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.services.RequestBookServices;

import java.util.List;

/**
 * RequestBook controller class of API
 *
 * @author deeksha.singh
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RequestBookController {
    @Autowired
    private RequestBookServices requestbookservices;

    @PostMapping("/addRequest/{id}")
    public RequestBookDetail addRequest(@RequestBody RequestBookDto requestBookDto, @PathVariable("id") long id) {
        return requestbookservices.addRequest(requestBookDto, id);
    }

    @GetMapping("/getRequests")
    public List<RequestBookDetail> getRequests() {
        return requestbookservices.getAllRequests();
    }


}
