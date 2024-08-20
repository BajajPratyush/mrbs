package org.mrbs.model.exceptions;

public class ManagerNotFound extends Exception{
    public ManagerNotFound() {
    }

    public ManagerNotFound(String message) {
        super(message);
    }

    public ManagerNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerNotFound(Throwable cause) {
        super(cause);
    }

    public ManagerNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
