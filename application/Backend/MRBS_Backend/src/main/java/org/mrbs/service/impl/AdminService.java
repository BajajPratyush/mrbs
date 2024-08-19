package org.mrbs.service.impl;

import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;
import org.mrbs.model.exceptions.MeetingRoomNotFound;
import org.mrbs.service.intf.AdminServiceIntf;

import java.sql.SQLException;
import java.util.List;

public class AdminService implements AdminServiceIntf {
    private AdminDaoIntf adminDao;

    public AdminService(AdminDaoIntf adminDao){
        this.adminDao = adminDao;
    }
    @Override
    public boolean addMeetingRoom(MeetingRoom mr) {
        try{
            return (adminDao.addMeetingRoom(mr))>0;
        } catch (MeetingRoomAlreadyPresentException e) {
            System.out.println(mr.getRoomId() + " Meeting room already present");
        } catch (InvalidMeetingRoomException e) {
            System.out.println("Invalid Meeting Room");;
        } catch (ClassNotFoundException e) {
            System.out.println("Meeting Room Couldn't be added to the database");;
        }
        return false;
    }

    @Override
    public boolean manageMeetingRoom(MeetingRoom mr) {
        try{
            return adminDao.manageMeetingRoom(mr)>0;
        } catch (MeetingRoomAlreadyPresentException e) {
            System.out.println(mr.getRoomId() + " Meeting room already present");
        } catch (InvalidMeetingRoomException e) {
            System.out.println("Invalid Meeting Room");;
        } catch (ClassNotFoundException e) {
            System.out.println("Meeting Room Couldn't be added to the database");;
        }
        return false;
    }

    @Override
    public boolean listAllMeetingRooms(){
        List<MeetingRoom> meetingRoomList = null;
        try {
                meetingRoomList = adminDao.allMeetingRooms();
                for(MeetingRoom mr : meetingRoomList)
                    System.out.println(mr);
                return true;
            } catch (ClassNotFoundException | SQLException | MeetingRoomNotFound e) {
                System.out.println("Meeting Rooms Can't Be Found !");
            }
        return false;
    }
}
