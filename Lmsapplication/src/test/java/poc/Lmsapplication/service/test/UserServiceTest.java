package poc.Lmsapplication.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.repositories.UserRepository;
import poc.Lmsapplication.services.UserService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For UserService Class
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    UserService userService;

    @Mock
    User user;

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userService)
                .build();
    }

    @Test
    public void testToFetch(){

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setUserId(1l);
        user1.setUsername("Deeksha");
        user1.setPassword("jdkajsd");
        user1.setPhoneNumber(87538030l);
        user1.setEmailId("jsdoiua@jfkljs");
        user1.setSex("Female");
        user1.setHometown("Banaras");
        user1.setDob(null);
        user1.setAge(22);
        user1.setRole("Admin");
        user1.setResponseStatus(ResponseStatus.APPROVED);
        userList.add(user1);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> service = userService.findAllUsers();
        assertEquals(1,service.size());
    }

    @Test
    public void testToSave(){

        List<User> userList = new ArrayList<>();

        User user1 = new User();
        user1.setUserId(1l);
        user1.setUsername("Deeksha");
        user1.setPassword("jdkajsd");
        user1.setPhoneNumber(87538030l);
        user1.setEmailId("jsdoiua@jfkljs");
        user1.setSex("Female");
        user1.setHometown("Banaras");
        user1.setAge(22);
        user1.setDob(Date.from(Instant.now()));
        user1.setRole("User");
        user1.setResponseStatus(ResponseStatus.PENDING);
        userList.add(user1);

        when(userRepository.save(user1)).thenReturn(user1);
        String actual = userService.addUserRequest(user1);
        assertNotNull(actual);
    }

}
