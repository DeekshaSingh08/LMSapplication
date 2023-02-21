package poc.Lmsapplication.exceptionhandling;

/**
 * @author deeksha.singh
 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

}
