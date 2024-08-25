package org.mrbs.controller;

import org.mrbs.entity.MeetingRoom;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.UserXMLProcessingException;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.intf.AdminServiceIntf;
import org.mrbs.service.intf.UserServiceIntf;

import java.util.HashSet;
import java.util.TreeSet;

public class AdminController {
    private AdminServiceIntf service;
    private UserServiceIntf userServiceIntf;
    public AdminController(AdminService service, UserServiceIntf userServiceIntf){
        this.service = service;
        this.userServiceIntf = userServiceIntf;
    }

    public void createMeetingRoom(User u, String roomId, String roomType, int roomCapacity){
        if(u.getUserRole() != UserRole.ADMIN){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        int roomCredits=0;
        if(roomCapacity>5 && roomCapacity <=10)
            roomCredits =10;
        else
            roomCredits = 20;
        MeetingRoom mr = new MeetingRoom(roomId,roomType,roomCredits, roomCapacity, new HashSet<>(), new TreeSet<>());
        if(service.addMeetingRoom(mr))
            System.out.println("Room added Successfully");
        else
            System.out.println("Sorry for the inconvenience");
    }

    public void updateMeetingRoom(User u, String roomId, String roomType, int roomCapacity){
        if(u.getUserRole() != UserRole.ADMIN){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        int roomCredits=0;
        if(roomCapacity>5 && roomCapacity <=10)
            roomCredits =10;
        else
            roomCredits = 20;
        MeetingRoom mr = new MeetingRoom(roomId,roomType,roomCredits, roomCapacity, new HashSet<>(), new TreeSet<>());
        if (service.manageMeetingRoom(mr))
            System.out.println("Meeting room updated successfully");
        else
            System.out.println("Sorry for the inconvenience");
    }

    public void viewAllMeetingRooms(User u){
        if(u.getUserRole() != UserRole.ADMIN){
            System.out.println("Unauthorized Access");
            System.exit(0);
        }
        System.out.println("Displaying All Meeting rooms");
        if(service.listAllMeetingRooms()){
            System.out.println("Thank You !");
        }
        else
            System.out.println("Sorry For The Inconvenience");
    }

    public void addUsersFromXML(String xmlFilePath)  {
        try {
            userServiceIntf.addUsersFromXML(xmlFilePath);
        } catch (UserXMLProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
