package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.controller.ManagerController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.dao.intf.ManagerDaoIntf;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.impl.AmenityService;
import org.mrbs.service.impl.ManagerService;
import org.mrbs.service.intf.ManagerServiceIntf;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;

    private ManagerController managerController;
    private ManagerServiceIntf managerServiceIntf;
    private ManagerDaoIntf managerDaoIntf;

    public BeanFactory(){
        new AmenityService();
        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService);


        managerController = new ManagerController();
        managerDaoIntf = new ManagerDaoImpl();
        managerServiceIntf = new ManagerService();
    }

    public AdminController getAdminController() {
        return adminController;
    }

    public ManagerController getManagerController(){
        return managerController;
    }
}
