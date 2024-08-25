package org.mrbs.controller;


import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;
import org.mrbs.service.impl.ManagerService;

import java.util.List;

public class ManagerController {

    private ManagerService managerService;

    // Endpoint to book a meeting room
    public boolean bookRoom( int managerId, int roomId)
    {
        try {
            return managerService.bookRoom(managerId, roomId);
        }
        catch (ManagerNotFound e)
        {
            e.printStackTrace();
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

