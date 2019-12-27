package club.mydlq.exceptionhandler.exception;

/**
 * @author mydlq
 */
public class MyException extends RuntimeException{

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

}
