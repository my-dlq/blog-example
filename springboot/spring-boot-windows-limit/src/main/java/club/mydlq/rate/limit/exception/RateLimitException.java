package club.mydlq.rate.limit.exception;

/**
 * 限流异常
 *
 * @author mydlq
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }

}
