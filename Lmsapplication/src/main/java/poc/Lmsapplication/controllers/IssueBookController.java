package poc.Lmsapplication.controllers;

import java.util.List;

import poc.Lmsapplication.entities.IssueBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poc.Lmsapplication.services.IssueBookService;

/**
 * IssueBook controller class of API
 *
 * @author deeksha.singh
 */

@RestController
public class IssueBookController {

    @Autowired
    private IssueBookService issueBookService;

    @PostMapping("/issueBook")
    public String addIssue(@RequestParam("bookid") long bookid, @RequestParam("userid") long userid) {
        return issueBookService.addIssue(bookid, userid);

    }

    @GetMapping("/getAllIssues")
    public List<IssueBook> getAllIssues() {

        return issueBookService.getAllIssues();

    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam("issueId") long issueId) {
        return issueBookService.returnBook(issueId);

    }

    @GetMapping("/allOverdue")
    public List<IssueBook> findAllBookOverdue() {
        return issueBookService.findAllBookOverdue();
    }


}
