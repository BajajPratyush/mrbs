package org.mrbs.dao.intf;

import org.mrbs.entity.User;

import java.util.List;

public interface ManagerDaoIntf {

    public List<User> findAllManagers();
    public void updateManager(User manager);
    public User findManagerById(int id);
}
