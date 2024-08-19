package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.service.impl.AdminService;
import org.mrbs.service.impl.AmenityService;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;
    private AmenityService amenityService;

    public BeanFactory(){
        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService);
        amenityService = new AmenityService();
    }

    public AdminController getAdminController() {
        return adminController;
    }
}
