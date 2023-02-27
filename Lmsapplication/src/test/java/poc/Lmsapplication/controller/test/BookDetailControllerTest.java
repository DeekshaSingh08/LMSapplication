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
import poc.Lmsapplication.controllers.BookDetailController;
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.services.BookDetailService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 * @author deeksha.singh
 * Test Cases For BookDetailController Class
 */

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookDetailControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    BookDetailController bookDetailController;

    @Mock
    BookDetails bookDetails;

    @Mock
    private HashMap<String, BookDetails> bookDetailsHashMap;

    @Mock
    BookDetailService bookDetailService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookDetailController)
                .build();
    }

    @Test
    public void testAddBookDetails() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/saveBook").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new BookDetails(10l, "Business World", 10, "Alex Volkov", null))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetBookDetails() throws Exception {
        BookDetails bookDetails1 = new BookDetails(1l, "Business World", 10, "Alex Volkov", null);
        when(bookDetailsHashMap.get(1l)).thenReturn(bookDetails1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/book/{id}", 1l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateBookDetails() throws Exception {

        BookDetails bookDetails1 = new BookDetails(1l, "Business World", 10, "Alex Volkov", null);
        when(bookDetailsHashMap.get(1l)).thenReturn(bookDetails1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateBook/{id}", 1l).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new BookDetails(1l, "Business World", 10, "Christian Harper", null))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteBookDetails() throws Exception {

        BookDetails bookDetails1 = new BookDetails(1l, "Business World", 10, "Alex Volkov", null);
        when(bookDetailsHashMap.get(1l)).thenReturn(bookDetails1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteBook/{id}", 1l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
