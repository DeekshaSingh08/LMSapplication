package poc.Lmsapplication.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.exceptionhandling.UserNotFoundException;
import poc.Lmsapplication.repositories.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * User service class of API
 *
 * @author deeksha.singh
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String addUserRequest(User user){

        if(findUserByUsername(user.getUsername()).size()==0){
            user.setResponseStatus(ResponseStatus.values()[0]);
            user.setRole("USER");
            user.setAge(convertDateOfBirthIntoAge(user.getDob()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            logger.info("Request Submitted for Registration...");
            return "Request Submitted for Registration !";
        }
        else{
            logger.error("Username is already taken...");
            return "Username is already taken!";
        }

    }

    public String addAdminRequest(User user){

//        user.setResponseStatus(ResponseStatus.values()[0]);
//        user.setRole("ADMIN");
//        userRepository.save(user);
//        return "Request Submitted for Registration !";
        if(findUserByUsername(user.getUsername()).size()==0){
            user.setResponseStatus(ResponseStatus.values()[0]);
            user.setRole("ADMIN");
            user.setAge(convertDateOfBirthIntoAge(user.getDob()));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            logger.info("Request Submitted for Registration...");
            return "Request Submitted for Registration !";
        }
        else{
            logger.error("Username is already taken...");
            return "Username is already taken!";
        }
    }

    public String userRequestStatus(long userid, int responseStatus){
        User user = userRepository.findById(userid).orElse(null);
        if(user == null){
            logger.error("No user record found...");
            return "Register first !";
        } else if (user !=null) {
            if (responseStatus == 1){

                user.setResponseStatus(ResponseStatus.values()[1]);
                userRepository.save(user);
                logger.info("User request approved...");
                return "User Request Approved !";

            } else if (responseStatus == 2) {

                user.setResponseStatus(ResponseStatus.values()[2]);
                userRepository.save(user);
                logger.info("User request rejected...");
                return "User Request Rejected !";
            }
        }
        logger.info("Request reviewed...");
        return "Request Reviewed !";
    }

    public String adminRequestStatus(long userid, int responseStatus){
        User user = userRepository.findById(userid).orElse(null);
        if(user == null){
            logger.error("No registration record found...");
            return "Register first !";
        } else if (user !=null) {
            if (responseStatus == 1){
                user.setResponseStatus(ResponseStatus.values()[1]);
                userRepository.save(user);
                logger.info("Admin request approved...");
                return "Admin Request Approved !";

            } else if (responseStatus == 2) {

                user.setResponseStatus(ResponseStatus.values()[2]);
                userRepository.save(user);
                logger.info("Admin request rejected...");
                return "Admin Request Rejected !";
            }
        }
        logger.info("Admin request reviewed...");
        return "Request Reviewed !";
    }

//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    public User updateUser(User user, long id) {
        if (!userRepository.existsById(id)) {
            logger.error("No user record found...");
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
            logger.info("User details updated...");
            return userRepository.save(userdetails);
        }

    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            logger.error("No user record found...");
            throw new UserNotFoundException("user was not found");
        } else {
            userRepository.deleteById(id);
            logger.info("User deleted successfully...");
            return "Successfully deleted !";
        }
    }

    public int convertDateOfBirthIntoAge(Date date){
        LocalDate birthDate=convertToLocalDateViaInstant(date);
        LocalDate currentDate=LocalDate.now();
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public List<User> getAdminCommonSearch(String search){
        return userRepository.getAdminCommonSearch(search);
    }
}
