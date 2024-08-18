package org.mrbs.dao.intf;

import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;

public interface AdminDaoIntf {
    public int addMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, MeetingRoomAlreadyPresentException, InvalidMeetingRoomException;
    public int manageMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, InvalidMeetingRoomException,MeetingRoomAlreadyPresentException;
}
