package org.mrbs;

import java.util.List;
import java.util.Scanner;

import org.mrbs.beanfactory.BeanFactory;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.entity.User;
import org.mrbs.entity.Amenity;
import org.mrbs.entity.UserRole;
import org.mrbs.model.exceptions.UserXMLProcessingException;
import org.mrbs.service.impl.AmenityService;
import org.mrbs.controller.AdminController;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;
import org.mrbs.model.exceptions.MeetingRoomNotFound;

public class App {

    public static void main(String[] args) throws UserXMLProcessingException {
        Scanner sc = new Scanner(System.in);
        BeanFactory factory = new BeanFactory();
        AdminController controller = factory.getAdminController();
        User u = new User(1, "Pratyush", "pratyush.bajaj123@gmail.com", 9897476644L, UserRole.ADMIN);



        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create Meeting Room");
            System.out.println("2. Update Meeting Room");
            System.out.println("3. View All Meeting Rooms");
            System.out.println("4. Add Users");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Create Meeting Room
                        System.out.print("Enter Room ID: ");
                        String roomId = sc.nextLine();
                        System.out.print("Enter Room Type (e.g., Classroom Training, Online Training, Conference Call, Business): ");
                        String roomType = sc.nextLine();
                        System.out.print("Enter Room Capacity: ");
                        int roomCapacity = sc.nextInt();
                        sc.nextLine();  // Consume newline
                        controller.createMeetingRoom(u,roomId,roomType,roomCapacity);
                    break;

                case 2:
                        System.out.print("Enter Room ID to Update: ");
                        roomId = sc.nextLine();
                        System.out.print("Enter New Room Type: ");
                        roomType = sc.nextLine();
                        System.out.print("Enter New Room Capacity: ");
                        roomCapacity = sc.nextInt();
                        sc.nextLine();  // Consume newline

                        controller.updateMeetingRoom(u,roomId,roomType,roomCapacity);
                    break;

                case 3:
                    // View All Meeting Rooms
                    controller.viewAllMeetingRooms(u);

                    break;

                case 4:
                    System.out.println("Enter the path to UsersXML");
                    String path = sc.nextLine();
                    controller.addUsersFromXML(path);
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }
    }
}
