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
import poc.Lmsapplication.controllers.BookCategoryController;
import poc.Lmsapplication.entities.BookCategory;
import poc.Lmsapplication.services.BookCategoryService;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookCategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    BookCategoryController bookCategoryController;

    @Mock
    BookCategory bookCategory;

    @Mock
    private HashMap<String, BookCategory> bookCategoryHashMap;

    @Mock
    BookCategoryService bookCategoryService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookCategoryController)
                .build();
    }

    @Test
    public void testAddBookCategory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createCategory").contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new BookCategory(8l,"novel",15,100))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetBookCategory() throws Exception {
        BookCategory bookCategory1 = new BookCategory(8l,"novel",15,100);
        when(bookCategoryHashMap.get(8l)).thenReturn(bookCategory1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/category/{id}", 8l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateBookCategory() throws Exception {

        BookCategory bookCategory1 = new BookCategory(8l,"novel",15,100);
        when(bookCategoryHashMap.get(8l)).thenReturn(bookCategory1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateCategory/{id}", 8l).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(new BookCategory(8l,"novel",18,100))))
        .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteBookDetails() throws Exception {

        BookCategory bookCategory1 = new BookCategory(8l,"novel",15,100);
        when(bookCategoryHashMap.get(8l)).thenReturn(bookCategory1);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCategory/{id}", 8l).accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

    }
}
