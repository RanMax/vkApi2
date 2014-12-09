package net.acyuta.vk.exceptions;

/**
 * Created by acyuta on 09.12.14.
 */
public class UnrecognizedResponse extends RuntimeException {
    public UnrecognizedResponse() {
    }

    public UnrecognizedResponse(String message) {
        super(message);
    }

    public UnrecognizedResponse(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrecognizedResponse(Throwable cause) {
        super(cause);
    }

    public UnrecognizedResponse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
