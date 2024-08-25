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
import org.mrbs.service.impl.UserServiceImpl;
import org.mrbs.service.intf.UserService;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;
    private AmenityService amenityService;

    private ManagerController managerController;
    private ManagerService managerServiceIntf;
    private ManagerDaoImpl managerDaoIntf;
    private UserService userService;
    private UserDaoIntf userDao;

    public BeanFactory(){

        new AmenityService();

        userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);

        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService,userService);
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
