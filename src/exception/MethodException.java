package exception;

import method.Get;
import method.Help;
import method.Method;
import method.Post;

public class MethodException extends RuntimeException {
    private Method h;

    public MethodException() {
        h = new Help();
    }

    public MethodException(Method m) {
        h = m;
    }

    public MethodException(String msg) {
        if(msg.equalsIgnoreCase("post")) {
            h = new Post();
        } else if(msg.equalsIgnoreCase("get")) {
            h = new Get();
        } else {
            h = new Help();
        }
    }

    public String getMessage() {
        return h.help();
    }
}
