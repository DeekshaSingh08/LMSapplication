package poc.Lmsapplication.controllers;

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
//    public User findUserByUsername(@PathVariable("username") String username){ return userService.findUserByUsername(username);}
    public User getUser(@PathVariable(value="username") String username)
    {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/userRequest")
    public String userRequest(@RequestBody User user) {

        return userService.addUserRequest(user);
    }

    @PostMapping("/adminRequest")
    public String adminRequest(@RequestBody User user) {

        return userService.addAdminRequest(user);
    }
    @PostMapping("/userRequestStatus")
    public String userRequestStatus(long userid, int responseStatus){
        return userService.userRequestStatus(userid,responseStatus);
    }

    @PostMapping("/adminRequestStatus")
    public String adminRequestStatus(long userid, int responseStatus){
        return userService.adminRequestStatus(userid,responseStatus);
    }

//    @PostMapping("/saveUser")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
         userService.deleteUser(id);
        return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/getAdminCommonSearch")
    public List<User> getAdminCommonSearch(@PathVariable("search") String search){
        return userService.getAdminCommonSearch(search);
    }
}
