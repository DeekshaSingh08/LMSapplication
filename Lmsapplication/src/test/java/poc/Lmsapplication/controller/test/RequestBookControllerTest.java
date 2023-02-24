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
import poc.Lmsapplication.dto.RequestBookDto;
import poc.Lmsapplication.entities.RequestBookDetail;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.services.RequestBookServices;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RequestBookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    RequestBookControllerTest requestBookControllerTest;

    @Mock
    RequestBookDetail requestBookDetail;

    @Mock
    private HashMap<String, RequestBookDetail> requestBookDetailHashMap;

    @Mock
    RequestBookServices requestBookServices;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(requestBookControllerTest)
                .build();
    }

    @Test
    public void testAddBookRequest() throws Exception {
        RequestBookDto requestBookDto = new RequestBookDto("twisted series","ana","novel");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addRequest/{id}",requestBookDto).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new RequestBookDetail(1l,new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED),"novel","twisted series", "ana"))))
        .andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetIssueBooks() throws Exception {
        RequestBookDetail requestBookDetail1 = new RequestBookDetail(1l,new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED),"novel","twisted series", "ana");
        when(requestBookDetailHashMap.get(1l)).thenReturn(requestBookDetail1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getRequests").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

}
