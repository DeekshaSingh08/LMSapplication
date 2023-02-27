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
import poc.Lmsapplication.controllers.UserController;
import poc.Lmsapplication.entities.BookCategory;
import poc.Lmsapplication.entities.User;
import poc.Lmsapplication.services.UserService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For UserController Class
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    UserController userController;

    @Mock
    User user;

    @Mock
    private HashMap<String, User>  userHashMap;

    @Mock
    UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void testAddBookCategory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/userRequest").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetBookCategory() throws Exception {
        User user1 = new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED);
        when(userHashMap.get(1l)).thenReturn(user1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/userById/{id}", 1l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateBookCategory() throws Exception {

        User user1 = new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED);
        when(userHashMap.get(1l)).thenReturn(user1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateUser/{id}", 1l).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new User(1l,"Deeksha","test123",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteBookDetails() throws Exception {

        User user1 = new User(1l,"Deeksha","test",8759398l,"jshds","female","varanasi",null,22,"Admin", ResponseStatus.APPROVED);
        when(userHashMap.get(1l)).thenReturn(user1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteUser/{id}", 1l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

    }

}
