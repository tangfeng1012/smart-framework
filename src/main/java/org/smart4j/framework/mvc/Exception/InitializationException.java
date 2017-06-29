package org.smart4j.framework.mvc.Exception;

/**
 * @author tf
 * @create 2017-06-28 16:23
 **/
public class InitializationException extends RuntimeException{
    public InitializationException() {
    }

    public InitializationException(String message) {
        super(message);
    }

    public InitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationException(Throwable cause) {
        super(cause);
    }
}
