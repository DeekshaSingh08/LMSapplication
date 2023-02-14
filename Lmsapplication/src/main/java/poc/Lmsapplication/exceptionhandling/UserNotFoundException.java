package poc.Lmsapplication.exceptionhandling;

/**
 * Exception handling class of API
 *
 * @author deeksha.singh
 */

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
