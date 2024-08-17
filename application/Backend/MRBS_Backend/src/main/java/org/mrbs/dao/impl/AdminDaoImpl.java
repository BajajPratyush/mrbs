package org.mrbs.dao.impl;

import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;

import java.sql.Connection;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDaoIntf {

    private Connection con;
    private Scanner sc;

    @Override
    public int addMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, MeetingRoomAlreadyPresentException, InvalidMeetingRoomException {
        return 0;
    }

    @Override
    public int manageMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, InvalidMeetingRoomException {
        return 0;
    }
}
