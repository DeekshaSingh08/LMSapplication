package poc.Lmsapplication.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import poc.Lmsapplication.Enum.ResponseStatus;
import poc.Lmsapplication.controllers.IssueBookController;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.entities.IssueBook;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.services.IssueBookService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class IssueBookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    IssueBookController issueBookController;

    @Mock
    IssueBook issueBook;

    @Mock
    private HashMap<String, IssueBook> issueBookHashMap;

    @Mock
    IssueBookService issueBookService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(issueBookController)
                .build();
    }

    @Test
    public void testAddIssueBook() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/issueBook?bookid=1&userid=1").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(
        new IssueBook(1l,new BookDetails(1l, "Business World", 10, "Alex Volkov", null),
        new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED),
        null,null,null))))
        .andReturn();
       assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetIssueBooks() throws Exception {
        IssueBook issueBook1 = new IssueBook(1l,new BookDetails(1l, "Business World", 10, "Alex Volkov", null),
                new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED),
                null,null,null);
        when(issueBookHashMap.get(1l)).thenReturn(issueBook1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getAllIssues").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
