package org.mrbs.dao.intf;

import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;
import org.mrbs.model.exceptions.MeetingRoomNotFound;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDaoIntf {

    public List<User> findAllManagers() throws ClassNotFoundException, SQLException, MeetingRoomNotFound;
    public void updateManager(User manager) throws ManagerNotFound , SQLException;
    public User findManagerById(int id) throws ManagerNotFound;
}
