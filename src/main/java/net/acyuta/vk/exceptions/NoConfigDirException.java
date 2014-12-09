package net.acyuta.vk.exceptions;

/**
 * Created by acyuta on 09.12.14.
 */
public class NoConfigDirException extends RuntimeException {
    public NoConfigDirException() {
    }

    public NoConfigDirException(String message) {
        super(message);
    }

    public NoConfigDirException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConfigDirException(Throwable cause) {
        super(cause);
    }

    public NoConfigDirException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
