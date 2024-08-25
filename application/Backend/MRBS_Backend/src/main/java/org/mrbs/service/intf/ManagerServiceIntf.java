package org.mrbs.service.intf;

import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ManagerServiceIntf {
    public boolean bookRoom(int managerId, int roomCost, String roomId, LocalDateTime startTime, LocalDateTime endTime, Set<Integer> amenityIds) throws ManagerNotFound;
    public List<User> viewAllManagers();
    public User viewManagerById(int managerId) throws ManagerNotFound;
    public void resetManagerCredits(int managerId) throws ManagerNotFound;
    public void addMan(User user);
}
