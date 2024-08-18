package org.mrbs.model.exceptions;

public class MeetingRoomNotFound extends Exception{
    public MeetingRoomNotFound() {
        super();
    }

    public MeetingRoomNotFound(String message) {
        super(message);
    }

    public MeetingRoomNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetingRoomNotFound(Throwable cause) {
        super(cause);
    }

    protected MeetingRoomNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
