package org.mrbs.dao.impl;

import com.sun.tools.jdeprscan.scan.Scan;
import org.mrbs.dao.intf.AdminDaoIntf;
import org.mrbs.entity.MeetingRoom;
import org.mrbs.model.exceptions.InvalidMeetingRoomException;
import org.mrbs.model.exceptions.MeetingRoomAlreadyPresentException;

import java.sql.*;
import java.util.Scanner;

public class AdminDaoImpl implements AdminDaoIntf {

    private Connection con;
    private Scanner sc;

    @Override
    public int addMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, MeetingRoomAlreadyPresentException, InvalidMeetingRoomException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Bajaj@123";
        String queryToCheckExist = "SELECT * FROM mrbs.meeting_room WHERE ProductId=?";
        String queryToInsert = "INSERT INTO mrbs.meeting_room VALUES (?,?,?)";
        sc = new Scanner(System.in);
        try(Connection con = DriverManager.getConnection(url,user,password)){

            PreparedStatement stmt = con.prepareStatement(queryToCheckExist);
            PreparedStatement stmt1 = con.prepareStatement(queryToInsert);

            stmt.setString(1,mr.getRoomId());
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                throw new MeetingRoomAlreadyPresentException();

            stmt1.setString(1,mr.getRoomId());
        }catch (SQLException e){
            throw new InvalidMeetingRoomException();
        }
    }

    @Override
    public int manageMeetingRoom(MeetingRoom mr) throws ClassNotFoundException, InvalidMeetingRoomException {
        return 0;
    }
}
