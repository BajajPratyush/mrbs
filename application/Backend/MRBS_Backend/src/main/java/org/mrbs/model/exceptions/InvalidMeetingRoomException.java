package org.mrbs.model.exceptions;

public class InvalidMeetingRoomException extends Exception{
    public InvalidMeetingRoomException(Throwable cause) {
        super(cause);
    }

    public InvalidMeetingRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMeetingRoomException(String message) {
        super(message);
    }

    public InvalidMeetingRoomException() {
        super();
    }

    protected InvalidMeetingRoomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
