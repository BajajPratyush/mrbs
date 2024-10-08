package org.mrbs.service.intf;

import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;

import java.util.List;

public interface ManagerServiceIntf {
    public List<User> viewAllManagers();
    public User viewManagerById(int managerId) throws ManagerNotFound;
    public void resetManagerCredits(int managerId) throws ManagerNotFound;
}
