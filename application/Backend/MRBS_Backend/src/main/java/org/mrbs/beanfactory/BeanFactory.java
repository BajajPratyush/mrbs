package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.controller.ManagerController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.dao.impl.UserDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.dao.intf.ManagerDaoIntf;
import org.mrbs.dao.intf.UserDaoIntf;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.impl.AmenityService;
import org.mrbs.service.impl.ManagerService;
import org.mrbs.service.impl.UserServiceImpl;
import org.mrbs.service.intf.ManagerServiceIntf;
import org.mrbs.service.intf.UserService;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;

    private ManagerController managerController;
    private ManagerService managerService;
    private ManagerDaoIntf managerDao;
    private UserService userService;
    private UserDaoIntf userDao;

    public BeanFactory(){
        new AmenityService();

        userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);


        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService, userService);

        managerDao = new ManagerDaoImpl();
        managerService = new ManagerService(managerDao);
        managerController = new ManagerController(managerService);
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public ManagerController getManagerController(){
        return managerController;
    }
}
