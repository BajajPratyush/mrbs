package org.mrbs.service.impl;


import org.mrbs.entity.User;
import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.service.intf.ManagerServiceIntf;

import java.util.List;

public class ManagerService implements ManagerServiceIntf {

    private ManagerDaoImpl managerDao = new ManagerDaoImpl();

    // Function to book a meeting room
    public boolean bookRoom(int managerId, int roomCost) {
        User manager = managerDao.findManagerById(managerId);

        if (manager.getCredits() >= roomCost) {
            manager.setCredits(manager.getCredits() - roomCost);
            managerDao.updateManager(manager);
            return true; // Room booked successfully
        }
        return false; // Insufficient credits
    }

    // Function to view all managers
    public List<User> viewAllManagers() {
        return managerDao.findAllManagers();
    }

    // Function to view a specific manager's details
    public User viewManagerById(int managerId) {
        return managerDao.findManagerById(managerId);
    }

    // Function to reset manager's credits to 2000
    public void resetManagerCredits(int managerId) {
        User manager = managerDao.findManagerById(managerId);
        manager.setCredits(2000);
        managerDao.updateManager(manager);
    }
}