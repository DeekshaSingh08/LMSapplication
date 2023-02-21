package poc.Lmsapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.Enum.ResponseStatus;
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

    public String addRequest(User user){

        user.setResponseStatus(ResponseStatus.values()[0]);
        userRepository.save(user);

        return "Request Submitted for Registration !";
    }

    public String userRequestStatus(long userid, int responseStatus){
        User user = userRepository.findById(userid).orElse(null);
        if(user == null){
            return "Register first !";
        } else if (user !=null) {
            if (responseStatus == 1){

                user.setResponseStatus(ResponseStatus.values()[1]);
                userRepository.save(user);
                return "User Request Approved !";

            } else if (responseStatus == 2) {

                user.setResponseStatus(ResponseStatus.values()[2]);
                userRepository.save(user);
                return "User Request Rejected !";

            }
        }

        return "Request Reviewed !";
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public User updateUser(User user, long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User was not found !");
        } else {
            User userdetails = userRepository.findById(id).orElse(user);
            userdetails.setUsername(user.getUsername());
            userdetails.setPassword(user.getPassword());
            userdetails.setPhoneNumber(user.getPhoneNumber());
            userdetails.setEmailId(user.getEmailId());
            userdetails.setSex(user.getSex());
            userdetails.setHometown(user.getHometown());
            userdetails.setDob(user.getDob());
            return userRepository.save(userdetails);
        }

    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("user was not found");
        } else {
            userRepository.deleteById(id);
            return "Successfully deleted !";
        }


    }

}
