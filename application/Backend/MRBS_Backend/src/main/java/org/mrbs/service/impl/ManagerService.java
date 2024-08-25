package org.mrbs.service.impl;


import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;
import org.mrbs.service.intf.ManagerServiceIntf;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ManagerService implements ManagerServiceIntf {

    private ManagerDaoImpl managerDao;

    public ManagerService(ManagerDaoImpl managerDaoImpl) {
        this.managerDao = managerDaoImpl;
    }

    public void addM(User u){
        managerDao.addManager(u);
    }

    // Function to book a meeting room
    public boolean bookRoom(int managerId, int roomCost, String roomId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> amenityIds)throws ManagerNotFound{
//    public boolean bookRoom(int managerId, int roomCost,) throws ManagerNotFound {
        try {
            return managerDao.bookRoomById(managerId,roomCost,roomId,startTime,endTime,amenityIds);
        }
        catch (ManagerNotFound e)
        {
            throw new ManagerNotFound(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Insufficient credits
    }



    // Function to view all managers
    public List<User> viewAllManagers() {
        return managerDao.findAllManagers();
    }

    // Function to view a specific manager's details
    public User viewManagerById(int managerId) throws ManagerNotFound {
        try {
            return managerDao.findManagerById(managerId);
        }
        catch (ManagerNotFound e)
        {
            throw new ManagerNotFound(e);
        }
    }

    // Function to reset manager's credits to 2000
    public void resetManagerCredits(int managerId) throws ManagerNotFound{
        try {
            User manager = managerDao.findManagerById(managerId);
            manager.setCredits(2000);
            managerDao.updateManager(manager);
        }
        catch (ManagerNotFound e)
        {
            throw new ManagerNotFound(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}