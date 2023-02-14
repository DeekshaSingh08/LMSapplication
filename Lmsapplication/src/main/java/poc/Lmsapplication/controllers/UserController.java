package poc.Lmsapplication.controllers;

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

    @PostMapping("/saveUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userService.updateUser(user, id);

    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);

    }

}
