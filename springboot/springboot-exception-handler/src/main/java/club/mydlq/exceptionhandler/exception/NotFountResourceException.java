package club.mydlq.exceptionhandler.exception;

/**
 * @author mydlq
 */
public class NotFountResourceException extends RuntimeException{

    public NotFountResourceException() {
        super();
    }

    public NotFountResourceException(String message) {
        super(message);
    }

    public NotFountResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFountResourceException(Throwable cause) {
        super(cause);
    }

}
