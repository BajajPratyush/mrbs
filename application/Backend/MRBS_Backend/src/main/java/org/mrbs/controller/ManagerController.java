package org.mrbs.controller;


import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.ManagerNotFound;
import org.mrbs.service.impl.ManagerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ManagerController {

    private ManagerService managerService ;
    public ManagerController(ManagerService managerServiceIntf) {
        this.managerService = managerServiceIntf;
    }

    public void addManag(User u)
    {
        managerService.addM(u);
    }

    // Endpoint to book a meeting room
    public boolean bookRoom(User u, int roomCost, String roomId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> amenityIds) {
        if (u.getUserRole() != UserRole.MANAGER) {
            System.out.println("Unauthorized Access");
            System.exit(0);
        }

        try {
            boolean success = managerService.bookRoom(u.getUserId(), roomCost, roomId, startTime, endTime, amenityIds);
            if (success) {
                System.out.println("Room booked successfully");
            } else {
                System.out.println("Failed to book the room. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while booking the room: " + e.getMessage());
        }
        return false;
    }

    // Endpoint to view all available meeting rooms
    public List<User> viewAllManagers() {
        return managerService.viewAllManagers();
    }

    // Method to view a specific manager's details
    public User viewManagerById(int managerId) {
        try{
            return managerService.viewManagerById(managerId);
        }
        catch (ManagerNotFound e)
        {
            e.printStackTrace();
        }
       return null;
    }

    // Method to reset manager's credits
    public void resetManagerCredits(int managerId) throws  ManagerNotFound{
        managerService.resetManagerCredits(managerId);
    }
}

