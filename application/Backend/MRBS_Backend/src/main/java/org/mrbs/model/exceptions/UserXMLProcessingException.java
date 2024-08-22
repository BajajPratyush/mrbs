package org.mrbs.model.exceptions;

public class UserXMLProcessingException extends Exception {
    public UserXMLProcessingException(String message) {
        super(message);
    }

    public UserXMLProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
