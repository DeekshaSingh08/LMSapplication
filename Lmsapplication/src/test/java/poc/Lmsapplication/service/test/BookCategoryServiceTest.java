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
import poc.Lmsapplication.entities.BookCategory;
import poc.Lmsapplication.repositories.BookCategoryRepository;
import poc.Lmsapplication.services.BookCategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For BookCategoryService Class
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookCategoryServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    BookCategoryService bookCategoryService;

    @Mock
    BookCategory bookCategory;

    @Mock
    private BookCategoryRepository bookCategoryRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookCategoryService)
                .build();
    }

    @Test
    public void testToFetch() {

        List<BookCategory> bookCategoryList= new ArrayList<>();

        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setCategoryId(1l);
        bookCategory1.setCategory("Novel");
        bookCategory1.setMinAge(18);
        bookCategory1.setMaxAge(100);
        bookCategoryList.add(bookCategory1);

        when(bookCategoryRepository.findAll()).thenReturn(bookCategoryList);
        List<BookCategory> service = bookCategoryService.findAllCategories();
        assertEquals(1,service.size());
    }

    @Test
    public void testToSave(){

        List<BookCategory> bookCategoryList= new ArrayList<>();

        BookCategory bookCategory1 = new BookCategory();
        bookCategory1.setCategoryId(1l);
        bookCategory1.setCategory("Novel");
        bookCategory1.setMinAge(18);
        bookCategory1.setMaxAge(100);
        bookCategoryList.add(bookCategory1);

        when(bookCategoryRepository.save(bookCategory1)).thenReturn(bookCategory1);
        BookCategory actual = bookCategoryService.createCategory(bookCategory1);
        assertNotNull(actual);

    }


}
