package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.controller.ManagerController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.impl.ManagerDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.impl.AmenityService;
import org.mrbs.service.impl.ManagerService;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;
    private AmenityService amenityService;

    private ManagerController managerController;
    private ManagerService managerService;
    private ManagerDaoImpl managerDao;

    public BeanFactory(){
        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService);
        amenityService = new AmenityService();


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
