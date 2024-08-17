package org.mrbs.model.exceptions;

public class MeetingRoomAlreadyPresentException extends Exception {
    public MeetingRoomAlreadyPresentException() {
        super();
    }

    public MeetingRoomAlreadyPresentException(String message) {
        super(message);
    }

    public MeetingRoomAlreadyPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetingRoomAlreadyPresentException(Throwable cause) {
        super(cause);
    }

    protected MeetingRoomAlreadyPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
