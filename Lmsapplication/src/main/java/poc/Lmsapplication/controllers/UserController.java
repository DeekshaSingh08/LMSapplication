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
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/userRequest")
    public String userRequest(@RequestBody User user) {
        return userService.addRequest(user);
    }

    @PostMapping("/userRequestStatus")
    public String userRequestStatus(long userid, int responseStatus){
        return userService.userRequestStatus(userid,responseStatus);
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

}
