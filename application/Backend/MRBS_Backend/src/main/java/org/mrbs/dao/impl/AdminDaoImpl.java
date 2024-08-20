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
import java.util.Set;

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
            throw new InvalidMeetingRoomException(e);
        }
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
        String query1 = "SELECT mr.room_id, mr.room_type, mr.room_credits, mr.room_capacity, mra.amenity_name " +
                "FROM meeting_rooms mr " +
                "LEFT JOIN meeting_room_amenities mra ON mr.room_id = mra.room_id";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt1 = conn.prepareStatement(query1)){
             ResultSet rs=pstmt1.executeQuery();
            String currentRoomId = null;
            MeetingRoom currentMeetingRoom = null;
            if(!rs.next()) {
                throw new MeetingRoomNotFound();
            }
            do{
                String roomId = rs.getString("room_id");
                String roomType = rs.getString("room_type");
                int roomCredits = rs.getInt("room_credits");
                int roomCapacity = rs.getInt("room_capacity");
                String amenityName = rs.getString("amenity_name");

                if (currentRoomId == null || !currentRoomId.equals(roomId)) {
                    // Save the previous room if exists
                    if (currentMeetingRoom != null) {
                        meetingRoomList.add(currentMeetingRoom);
                    }
                    // Start a new MeetingRoom
                    currentMeetingRoom = new MeetingRoom(roomId,  roomCredits,roomType, roomCapacity);
                    currentRoomId = roomId;
                }
                // Add the amenity to the current MeetingRoom
                if (amenityName != null) {
                    Set<Amenity> lst = currentMeetingRoom.getAddedAmenities();
                    Amenity amenity = AmenityService.getAmenityByName(amenityName);
                    if(amenity!=null)
                        lst.add(amenity);
                    currentMeetingRoom.setAddedAmenities(lst);
                }
                // Add the last room
                if (currentMeetingRoom != null) {
                    meetingRoomList.add(currentMeetingRoom);
                }
            }while(rs.next());
            return meetingRoomList;
        } catch (SQLException e) {
            throw new MeetingRoomNotFound();
        }
    }
}
