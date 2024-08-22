package org.mrbs.service.intf;

import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;

import java.sql.SQLException;
import java.util.List;

public interface ManagerServiceIntf {
    public boolean bookRoom(int managerId, int roomCost) throws ManagerNotFound ;
    public List<User> viewAllManagers() throws SQLException, ManagerNotFound, ClassNotFoundException;
    public User viewManagerById(int managerId) throws ManagerNotFound;
    public void resetManagerCredits(int managerId) throws ManagerNotFound;
}
