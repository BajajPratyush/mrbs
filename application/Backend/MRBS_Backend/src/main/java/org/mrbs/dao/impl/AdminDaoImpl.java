package org.mrbs.dao.impl;

import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.entity.Amenity;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;
import org.mrbs.model.exceptions.MeetingRoomNotFound;
import org.mrbs.service.impl.AmenityService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDaoIntf {

    @Override
    public int addMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, MeetingRoomAlreadyPresentException, InvalidMeetingRoomException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Bajaj@123";
        String queryToCheckExist = "SELECT * FROM mrbs.meeting_rooms WHERE room_id=?";
        String queryToInsert = "INSERT INTO mrbs.meeting_rooms (room_id, room_type, room_credits, room_capacity) VALUES (?,?,?,?)";
        String insertAmenityQuery = "INSERT INTO mrbs.meeting_room_amenities (room_id, amenity_name) VALUES (?, ?)";
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

            switch (mr.getRoomType()) {
                case "Classroom Training":
                    mr.getAddedAmenities().add(AmenityService.amenities.get(1));
                    mr.getAddedAmenities().add(AmenityService.amenities.get(4));
                    break;
                case "Online Training":
                    mr.getAddedAmenities().add(AmenityService.amenities.get(1));
                    mr.getAddedAmenities().add(AmenityService.amenities.get(2));
                    break;
                case "Conference Call":
                    mr.getAddedAmenities().add(AmenityService.amenities.get(3));
                    break;
                case "Business":
                    mr.getAddedAmenities().add(AmenityService.amenities.get(1));
                    break;
            }
            if (mr.getAddedAmenities() != null) {
                for (Amenity amenity : mr.getAddedAmenities()) {
                    try (PreparedStatement stmt2 = con.prepareStatement(insertAmenityQuery)) {
                        stmt2.setString(1, mr.getRoomId());
                        stmt2.setString(2, amenity.getAmenityName());
                        stmt2.executeUpdate();
                    }
                }
            }

            return rowsAffected;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int manageMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, InvalidMeetingRoomException, MeetingRoomAlreadyPresentException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Bajaj@123";
        String queryToCheckExist = "SELECT * FROM mrbs.meeting_rooms WHERE room_id=?";
        //String queryToUpdateAmenity = "UPDATE meeting_room_amenities SET room_id=?,amenity_id=? ";
        String queryToUpdateMeetingRoom = "UPDATE mrbs.meeting_room_amenities SET room_id=?,room_type=?,room_credits=?,room_capacity=?";
        try(Connection con = DriverManager.getConnection(url,user,password)){

            PreparedStatement stmt = con.prepareStatement(queryToCheckExist);
            PreparedStatement stmt1 = con.prepareStatement(queryToUpdateMeetingRoom);

            stmt.setString(1,mr.getRoomId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                throw new MeetingRoomAlreadyPresentException();

            stmt1.setString(1,mr.getRoomId());
            stmt1.setString(2,mr.getRoomType());
            stmt1.setInt(3,mr.getRoomCredits());
            stmt1.setInt(4,mr.getRoomCapacity());
            System.out.println("Hello");
            return stmt1.executeUpdate();

        }catch (SQLException e){
            throw new InvalidMeetingRoomException();
        }
    }

    @Override
    public List<MeetingRoom> allMeetingRooms() throws ClassNotFoundException, SQLException, MeetingRoomNotFound {
        List<MeetingRoom> meetingRoomList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Bajaj@123";
        String query1 = "SELECT * FROM mrbs.meeting_rooms";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)){
            ResultSet rs=pstmt1.executeQuery();
            if(!rs.next()) {
                throw new MeetingRoomNotFound();
            }
            do{
                String id=rs.getString(1);
                String type=rs.getString(2);
                int credits=rs.getInt(3);
                int capacity = rs.getInt(4);
                MeetingRoom meetingRoom=new MeetingRoom(id,credits,type,capacity);
                meetingRoomList.add(meetingRoom);
            }while(rs.next());
            return meetingRoomList;
        } catch (SQLException e) {
            throw new MeetingRoomNotFound();
        }
    }
}
