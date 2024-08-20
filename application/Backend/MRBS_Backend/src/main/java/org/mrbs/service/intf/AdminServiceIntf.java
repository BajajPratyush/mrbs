package org.mrbs.service.intf;

import org.mrbs.entity.MeetingRoom;

public interface AdminServiceIntf {
    public boolean addMeetingRoom(MeetingRoom mr);
    public boolean manageMeetingRoom(MeetingRoom mr);
    public boolean listAllMeetingRooms();
}
