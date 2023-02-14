package poc.Lmsapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.exceptionhandling.UserNotFoundException;
import poc.Lmsapplication.repositories.UserRepository;

import java.util.List;

/**
 * User service class of API
 *
 * @author deeksha.singh
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("user was not found");
        } else {
            User userdetails = userRepository.findById(id).orElse(user);
            userdetails.setEmailId(user.getEmailId());
            return userRepository.save(userdetails);
        }

    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("user was not found");
        } else {
            userRepository.deleteById(id);
            return "yes deleted";
        }


    }

}
