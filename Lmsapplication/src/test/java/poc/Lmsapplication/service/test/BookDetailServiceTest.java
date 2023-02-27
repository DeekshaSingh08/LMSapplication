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
import poc.Lmsapplication.entities.BookDetails;
import poc.Lmsapplication.repositories.BookDetailsRepository;
import poc.Lmsapplication.services.BookDetailService;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author deeksha.singh
 * Test Cases For BookDetailService Class
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookDetailServiceTest {

    private MockMvc mockMvc;
    @InjectMocks
    BookDetailService bookDetailService;

    @Mock
    BookDetails bookDetails;

    @Mock
    private BookDetailsRepository bookDetailsRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookDetailService)
                .build();
    }

    @Test
    public void testToFetch() {

        List<BookDetails> bookDetailsList= new ArrayList<>();

        BookDetails bookDetails1 = new BookDetails();
        bookDetails1.setBookId(1l);
        bookDetails1.setBookName("Twisted Series");
        bookDetails1.setBookCategory(null);
        bookDetails1.setQuantity(10);
        bookDetails1.setAuthorName("Ana");
        bookDetailsList.add(bookDetails1);

        when(bookDetailsRepository.findAll()).thenReturn(bookDetailsList);
        List<BookDetails> service = bookDetailService.findAllBooks();
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
        assertNotNull(actual);
    }

//    @Test
//    void testToDelete() throws Exception {
//
//        List<BookDetails> bookDetailsList= new ArrayList<>();
//
//        BookDetails bookDetails1 = new BookDetails();
//        bookDetails1.setId(1l);
//        bookDetails1.setBookId(1l);
//        bookDetails1.setBookName("Twisted Series");
//        bookDetails1.setBookCategory(null);
//        bookDetails1.setQuantity(10);
//        bookDetails1.setAuthorName("Ana");
//        bookDetailsList.add(bookDetails1);
//
//        when(bookDetailsRepository.save(bookDetails1)).thenReturn(bookDetails1);
//        BookDetails actual = bookDetailService.createBook(bookDetails1);
//        bookDetailService.deleteBook(bookDetails1.getBookId());
//        Mockito.verify(bookDetailsRepository).delete(actual);
//    }

}
