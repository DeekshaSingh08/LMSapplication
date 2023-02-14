package poc.Lmsapplication.exceptionhandling;

/**
 * Exception handling class of API
 *
 * @author deeksha.singh
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
