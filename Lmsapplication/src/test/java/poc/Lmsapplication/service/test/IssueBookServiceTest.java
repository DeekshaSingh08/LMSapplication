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
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.entities.IssueBook;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.repositories.BookDetailsRepository;
import poc.Lmsapplication.repositories.IssueBookRepository;
import poc.Lmsapplication.repositories.UserRepository;
import poc.Lmsapplication.services.BookDetailService;
import poc.Lmsapplication.services.IssueBookService;
import poc.Lmsapplication.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For IssueBookService Class
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class IssueBookServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    IssueBookService issueBookService;

    @Mock
    BookDetailService bookDetailService;

    @Mock
    UserService userService;

    @Mock
    IssueBook issueBook;

    @Mock
    private IssueBookRepository issueBookRepository;

    @Mock
    private BookDetailsRepository bookDetailsRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(issueBookService)
                .build();
    }

    @Test
    public void testToFetch(){

        List<BookDetails> bookDetailsList= new ArrayList<>();
        BookDetails bookDetails1 = new BookDetails();
        bookDetails1.setBookId(1l);
        bookDetails1.setBookName("Twisted Series");
        bookDetails1.setBookCategory(null);
        bookDetails1.setQuantity(10);
        bookDetails1.setAuthorName("Ana");
        bookDetailsList.add(bookDetails1);
        when(bookDetailsRepository.save(bookDetails1)).thenReturn(bookDetails1);
        BookDetails actual = bookDetailService.createBook(bookDetails1);

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

        List<IssueBook> issueBookList = new ArrayList<>();
        IssueBook issueBook1 = new IssueBook();
        issueBook1.setIssueId(1l);
        issueBook1.setBookDetails(actual);
        issueBook1.setUser(user1);
        issueBook1.setIssueDate(null);
        issueBook1.setReturnDate(null);
        issueBook1.setReturnedDate(null);
        issueBookList.add(issueBook1);

        when(issueBookRepository.findAll()).thenReturn(issueBookList);
        List<IssueBook> service = issueBookService.getAllIssues();
        assertEquals(1,service.size());

    }

    @Test
    public void testToSave(){

        List<BookDetails> bookDetailsList= new ArrayList<>();
        BookDetails bookDetails1 = new BookDetails();
        bookDetails1.setBookId(1l);
        bookDetails1.setBookName("Twisted Series");
        bookDetails1.setBookCategory(null);
        bookDetails1.setQuantity(10);
        bookDetails1.setAuthorName("Ana");
        bookDetailsList.add(bookDetails1);
        when(bookDetailsRepository.save(bookDetails1)).thenReturn(bookDetails1);
        BookDetails actual = bookDetailService.createBook(bookDetails1);

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

        List<IssueBook> issueBookList = new ArrayList<>();
        IssueBook issueBook1 = new IssueBook();
        issueBook1.setIssueId(1l);
        issueBook1.setBookDetails(actual);
        issueBook1.setUser(user1);
        issueBook1.setIssueDate(null);
        issueBook1.setReturnDate(null);
        issueBook1.setReturnedDate(null);
        issueBookList.add(issueBook1);

        when(issueBookRepository.save(issueBook1)).thenReturn(issueBook1);
        String actualIssueBook = issueBookService.addIssue(1l,1l);
        assertNotNull(actualIssueBook);

    }

}
