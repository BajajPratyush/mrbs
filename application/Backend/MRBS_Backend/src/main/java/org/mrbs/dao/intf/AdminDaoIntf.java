package org.mrbs.dao.intf;

import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;

public interface AdminDaoIntf {
    public int addMeetingRoom(MeetingRoom mr) throws MeetingRoomAlreadyPresentException, InvalidMeetingRoomException;
    public int manageMeetingRoom(MeetingRoom mr);

}
