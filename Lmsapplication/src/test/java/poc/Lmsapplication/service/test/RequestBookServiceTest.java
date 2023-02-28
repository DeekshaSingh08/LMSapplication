package poc.Lmsapplication.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.dto.RequestBookDto;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.entities.RequestBookDetail;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.repositories.BookDetailsRepository;
import poc.Lmsapplication.repositories.RequestBookDetailRepository;
import poc.Lmsapplication.repositories.UserRepository;
import poc.Lmsapplication.services.RequestBookServices;
import poc.Lmsapplication.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For RequestBookService Class
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RequestBookServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    RequestBookServices requestBookServices;

    @Mock
    UserService userService;

    @Mock
    RequestBookDetail requestBookDetail;

    @Mock
    RequestBookDto requestBookDto;

    @Mock
    private RequestBookDetailRepository requestBookDetailRepository;

    @Mock
    BookDetailsRepository bookDetailsRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestBookServices)
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
        when(userRepository.save(user1)).thenReturn(user1);
        String actualUser = userService.addUserRequest(user1);

        List<RequestBookDetail> requestBookDetailList = new ArrayList<>();
        RequestBookDetail requestBookDetail1 = new RequestBookDetail();
        requestBookDetail1.setRequestId(1l);
        requestBookDetail1.setBookCategory("Novel");
        requestBookDetail1.setBookName("Twisted Series");
        requestBookDetail1.setAuthorName("Ana");
        requestBookDetail1.setUser(user1);
        requestBookDetailList.add(requestBookDetail1);

        when(requestBookDetailRepository.findAll()).thenReturn(requestBookDetailList);
        List<RequestBookDetail> service = requestBookServices.getAllRequests();
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
        user1.setDob(null);
        user1.setAge(22);
        user1.setRole("Admin");
        user1.setResponseStatus(ResponseStatus.APPROVED);
        userList.add(user1);
        when(userRepository.save(user1)).thenReturn(user1);

        List<BookDetails> bookDetailsList= new ArrayList<>();

        RequestBookDto requestBookDto1 = new RequestBookDto("Shatter Series","Ana","Novel");

        RequestBookDetail requestBookDetail1 = new RequestBookDetail();
        requestBookDetail1.setBookCategory(requestBookDto1.getCategory());
        requestBookDetail1.setBookName(requestBookDto1.getBookName());
        requestBookDetail1.setAuthorName(requestBookDto1.getAuthorName());
        requestBookDetail1.setUser(user1);

        when(userRepository.findById(1l)).thenReturn(Optional.of(user1));
        when(bookDetailsRepository.findByBookName(requestBookDto.getBookName())).thenReturn((bookDetailsList));

        RequestBookDetail requestBookDetail2 = new RequestBookDetail();
        requestBookDetail2.setRequestId(1l);
        requestBookDetail2.setBookCategory(requestBookDto1.getCategory());
        requestBookDetail2.setBookName(requestBookDto1.getBookName());
        requestBookDetail2.setAuthorName(requestBookDto1.getAuthorName());
        requestBookDetail2.setUser(user1);

        when(requestBookDetailRepository.save(requestBookDetail1)).thenReturn(requestBookDetail2);
        RequestBookDetail actual = requestBookServices.addRequest(requestBookDto1,1l);
        assertNotNull(actual);

    }

}
