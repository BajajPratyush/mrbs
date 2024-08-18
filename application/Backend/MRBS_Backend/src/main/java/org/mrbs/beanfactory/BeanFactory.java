package org.mrbs.beanfactory;

import org.mrbs.controller.AdminController;
import org.mrbs.dao.impl.AdminDaoImpl;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.service.impl.AdminService;

public class BeanFactory {
    private AdminDaoIntf adminDao;
    private AdminService adminService;
    private AdminController adminController;

    public BeanFactory(){
        adminDao = new AdminDaoImpl();
        adminService = new AdminService(adminDao);
        adminController = new AdminController(adminService);
    }

    public AdminController getAdminController() {
        return adminController;
    }
}
