package poc.Lmsapplication.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.entities.IssueBook;
import org.springframework.beans.factory.annotation.Autowired;
import poc.Lmsapplication.services.IssueBookService;

/**
 * IssueBook controller class of API
 *
 * @author deeksha.singh
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IssueBookController {

    Logger logger = LoggerFactory.getLogger(IssueBookController.class);
    @Autowired
    private IssueBookService issueBookService;

    @PostMapping("/issueBook")
    public String addIssue(@RequestParam("bookid") long bookid, @RequestParam("userid") long userid) {
        logger.info("Book issue request raised successfully...");
        return issueBookService.addIssue(bookid, userid);

    }
    @PostMapping("/addResponse")
    public String addResponse(@RequestParam("issueId") long issueId,@RequestParam("responseStatus") int responseStatus){
        logger.info("Admin response added successfully...");
        return issueBookService.addResponse(issueId,responseStatus);
    }

    @GetMapping("/getAllIssues")
    public List<IssueBook> getAllIssues() {
        return issueBookService.getAllIssues();
    }

    @GetMapping("/getApprovedIssues")
    public List<IssueBook> getApprovedIssues() {
        return issueBookService.getApprovedIssues();
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("issueId") long issueId) {
        logger.info("Book returned successfully...");
        return issueBookService.returnBook(issueId);

    }

    @GetMapping("/allOverdue")
    public List<IssueBook> findAllBookOverdue() {
        return issueBookService.findAllBookOverdue();
    }

}
