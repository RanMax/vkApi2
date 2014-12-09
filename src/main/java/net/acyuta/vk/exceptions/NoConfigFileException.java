package net.acyuta.vk.exceptions;

/**
 * Created by acyuta on 09.12.14.
 */
public class NoConfigFileException extends RuntimeException {
    public NoConfigFileException() {
    }

    public NoConfigFileException(String message) {
        super(message);
    }

    public NoConfigFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConfigFileException(Throwable cause) {
        super(cause);
    }

    public NoConfigFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
