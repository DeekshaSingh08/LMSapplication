package poc.Lmsapplication.exceptionhandling;

/**
 * @author deeksha.singh
 */
public class AdminNotFoundException extends RuntimeException {

    public AdminNotFoundException(String message) {
        super(message);
    }

}
