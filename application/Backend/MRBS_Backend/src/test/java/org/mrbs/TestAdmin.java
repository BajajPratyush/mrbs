package org.mrbs.tests;

import org.junit.jupiter.api.*;
import org.mrbs.beanfactory.BeanFactory;
import org.mrbs.controller.AdminController;
import org.mrbs.entity.User;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;
import org.mrbs.model.exceptions.UserXMLProcessingException;

import static org.junit.jupiter.api.Assertions.*;

public class TestAdmin {

    private AdminController controller;
    private User adminUser;

    @BeforeEach
    void setUp() {
        BeanFactory factory = new BeanFactory();
        this.controller = factory.getAdminController();
        this.adminUser = new User(1, "Pratyush", "pratyush.bajaj123@gmail.com", 9897476644L, UserRole.ADMIN);
    }

    @Test
    @DisplayName("Test Create Meeting Room")
    void testCreateMeetingRoom() {
        String roomId = "R001";
        String roomType = "Classroom Training";
        int roomCapacity = 50;

        assertDoesNotThrow(() -> controller.createMeetingRoom(adminUser, roomId, roomType, roomCapacity));
    }

    @Test
    @DisplayName("Test Create Duplicate Meeting Room")
    void testCreateDuplicateMeetingRoom() {
        String roomId = "R001";
        String roomType = "Classroom Training";
        int roomCapacity = 50;

        controller.createMeetingRoom(adminUser, roomId, roomType, roomCapacity);

        assertThrows(MeetingRoomAlreadyPresentException.class, () -> controller.createMeetingRoom(adminUser, roomId, roomType, roomCapacity));
    }

    @Test
    @DisplayName("Test Update Meeting Room")
    void testUpdateMeetingRoom() {
        String roomId = "R002";
        String initialRoomType = "Online Training";
        int initialRoomCapacity = 30;
        
        controller.createMeetingRoom(adminUser, roomId, initialRoomType, initialRoomCapacity);
        
        String newRoomType = "Conference Call";
        int newRoomCapacity = 25;
        
        assertDoesNotThrow(() -> controller.updateMeetingRoom(adminUser, roomId, newRoomType, newRoomCapacity));
    }

    @Test
    @DisplayName("Test Update Non-existent Meeting Room")
    void testUpdateNonExistentMeetingRoom() {
        String roomId = "R999";
        String newRoomType = "Business";
        int newRoomCapacity = 10;

        assertThrows(InvalidMeetingRoomException.class, () -> controller.updateMeetingRoom(adminUser, roomId, newRoomType, newRoomCapacity));
    }

    @Test
    @DisplayName("Test View All Meeting Rooms")
    void testViewAllMeetingRooms() {
        assertDoesNotThrow(() -> controller.viewAllMeetingRooms(adminUser));
    }

    @Test
    @DisplayName("Test Add Users from XML")
    void testAddUsersFromXML() {
        String xmlPath = "path/to/users.xml"; // Provide a valid path or mock

        assertDoesNotThrow(() -> controller.addUsersFromXML(xmlPath));
    }

    @Test
    @DisplayName("Test Add Users from Invalid XML")
    void testAddUsersFromInvalidXML() {
        String invalidXmlPath = "path/to/invalid_users.xml"; // Provide an invalid path or mock

        assertThrows(UserXMLProcessingException.class, () -> controller.addUsersFromXML(invalidXmlPath));
    }
}