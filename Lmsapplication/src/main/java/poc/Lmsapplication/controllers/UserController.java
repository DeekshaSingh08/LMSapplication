package poc.Lmsapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import poc.Lmsapplication.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poc.Lmsapplication.services.UserService;

import java.util.List;

/**
 * User controller class of API
 *
 * @author deeksha.singh
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/userById/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/user/{username}")
    public List<User> getUser(@PathVariable(value = "username") String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/userRequest")
    public String userRequest(@RequestBody User user) {
        logger.info("User request submitted successfully...");
        return userService.addUserRequest(user);
    }

    @PostMapping("/adminRequest")
    public String adminRequest(@RequestBody User user) {
        logger.info("Admin request submitted successfully...");
        return userService.addAdminRequest(user);
    }

    @PostMapping("/userRequestStatus")
    public String userRequestStatus(long userid, int responseStatus) {
        logger.info("User request status updated...");
        return userService.userRequestStatus(userid, responseStatus);
    }

    @PostMapping("/adminRequestStatus")
    public String adminRequestStatus(long userid, int responseStatus) {
        logger.info("Admin request status updated...");
        return userService.adminRequestStatus(userid, responseStatus);
    }

//    @PostMapping("/saveUser")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.info("User details updated successfully...");
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        logger.info("User deleted successfully...");
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/getAdminCommonSearch")
    public List<User> getAdminCommonSearch(@PathVariable("search") String search) {
        return userService.getAdminCommonSearch(search);
    }
}
