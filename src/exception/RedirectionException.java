package exception;

/**
 *
 */
public class RedirectionException extends RuntimeException {
    private String msg;

    public RedirectionException(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
