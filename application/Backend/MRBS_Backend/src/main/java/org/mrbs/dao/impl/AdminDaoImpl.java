package org.mrbs.dao.impl;

import com.sun.tools.jdeprscan.scan.Scan;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.entity.Amenity;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;
import org.mrbs.service.impl.AmenityService;

import java.sql.*;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDaoIntf {

    @Override
    public int addMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, MeetingRoomAlreadyPresentException, InvalidMeetingRoomException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Bajaj@123";
        String queryToCheckExist = "SELECT * FROM meeting_rooms WHERE room_id=?";
        String queryToInsert = "INSERT INTO meeting_rooms (room_id, room_type, room_credits, room_capacity) VALUES (?,?,?,?)";
        String insertAmenityQuery = "INSERT INTO meeting_room_amenities (room_id, amenity_id) VALUES (?, ?)";
        try(Connection con = DriverManager.getConnection(url,user,password)){

            PreparedStatement stmt = con.prepareStatement(queryToCheckExist);
            PreparedStatement stmt1 = con.prepareStatement(queryToInsert);

            stmt.setString(1,mr.getRoomId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                throw new MeetingRoomAlreadyPresentException();

            stmt1.setString(1,mr.getRoomId());
            stmt1.setString(2,mr.getRoomType());
            stmt1.setInt(3,mr.getRoomCredits());
            stmt1.setInt(4,mr.getRoomCapacity());

            int rowsAffected = stmt1.executeUpdate();

            if(mr.getRoomType() == "Classroom Training"){
                mr.getAddedAmenities().add(AmenityService.amenities.get(1));
                mr.getAddedAmenities().add(AmenityService.amenities.get(4));
            } else if (mr.getRoomType() == "Online Training") {
                mr.getAddedAmenities().add(AmenityService.amenities.get(1));
                mr.getAddedAmenities().add(AmenityService.amenities.get(2));
            }else if(mr.getRoomType() == "Conference Call"){
                mr.getAddedAmenities().add(AmenityService.amenities.get(3));
            }else if (mr.getRoomType() == "Business")
                mr.getAddedAmenities().add(AmenityService.amenities.get(1));

            if (mr.getAddedAmenities() != null) {
                for (Amenity amenity : mr.getAddedAmenities()) {
                    try (PreparedStatement stmt2 = con.prepareStatement(insertAmenityQuery)) {
                        stmt2.setString(1, mr.getRoomId());
                        stmt2.setInt(2, amenity.getAmenityId());
                        stmt2.executeUpdate();
                    }
                }
            }

            return rowsAffected;
        }catch (SQLException e){
            throw new InvalidMeetingRoomException();
        }
    }

    @Override
    public int manageMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, InvalidMeetingRoomException {
        return 0;
    }
}
