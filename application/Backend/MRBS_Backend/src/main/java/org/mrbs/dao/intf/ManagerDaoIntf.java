package org.mrbs.dao.intf;

import org.mrbs.entity.User;
import org.mrbs.model.exceptions.ManagerNotFound;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDaoIntf {

    public List<User> findAllManagers() throws ManagerNotFound, ClassNotFoundException, SQLException;
    public void updateManager(User manager) throws ManagerNotFound , SQLException;
    public User findManagerById(int id) throws ManagerNotFound;
    public void addManager(User u);
}
