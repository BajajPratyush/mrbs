//package org.mrbs.controller;
//
//
//import org.mrbs.entity.MeetingRoom;
//import org.mrbs.entity.User;
//import org.mrbs.service.impl.ManagerService;
//
//import java.util.List;
//
//public class ManagerController {
//
//    private ManagerService managerService;
//
//    // Endpoint to book a meeting room
//    public boolean bookRoom( int managerId, int roomId) {
//        return managerService.bookRoom(managerId, roomId);
//    }
//
//    // Endpoint to view all available meeting rooms
//    public List<User> viewAllManagers() {
//        return managerService.viewAllManagers();
//    }
//
//    // Method to view a specific manager's details
//    public User viewManagerById(int managerId) {
//        return managerService.viewManagerById(managerId);
//    }
//
//    // Method to reset manager's credits
//    public void resetManagerCredits(int managerId) {
//        managerService.resetManagerCredits(managerId);
//    }
//}
//
