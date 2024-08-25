package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.controller.ManagerController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.dao.impl.UserDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.dao.intf.UserDaoIntf;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.impl.AmenityService;
import org.mrbs.service.impl.ManagerService;
import org.mrbs.service.impl.UserService;
import org.mrbs.service.intf.UserServiceIntf;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;
    private AmenityService amenityService;

    private ManagerController managerController;
    private ManagerService managerServiceIntf;
    private ManagerDaoImpl managerDaoIntf;
    private UserServiceIntf userServiceIntf;
    private UserDaoIntf userDao;

    public BeanFactory(){

        new AmenityService();

        userDao = new UserDaoImpl();
        userServiceIntf = new UserService(userDao);

        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService, userServiceIntf);
        amenityService = new AmenityService();

        managerDaoIntf = new ManagerDaoImpl();
        managerServiceIntf = new ManagerService(managerDaoIntf);
        managerController = new ManagerController(managerServiceIntf);

    }

    public AdminController getAdminController() {
        return adminController;
    }

    public ManagerController getManagerController(){
        return managerController;
    }
}
