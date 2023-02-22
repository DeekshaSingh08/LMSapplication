package poc.Lmsapplication.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poc.Lmsapplication.dto.UserDto;
import poc.Lmsapplication.entities.JwtRequest;
import poc.Lmsapplication.repositories.UserRepository;

/**
 * JwtUserDetails service class of API for jwt
 *
 * @author deeksha.singh
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

 //        poc.Lmsapplication.entities.User user = userRepository.findByUsername(username);
//        JwtRequest jwtRequest = new JwtRequest();
//        UserDto userDto;
//        jwtRequest.setUsername(user.getUsername());
//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        jwtRequest.setPassword(encodedPassword);
//        userDto = new UserDto(jwtRequest.getUsername(),jwtRequest.getPassword(),new ArrayList<>());
//        userDto.setJwtRequest(jwtRequest);
//        if (user != null) {
//            return userDto;
//
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

        if ("javajwt".equals(username)) {
            return new User("javajwt", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
