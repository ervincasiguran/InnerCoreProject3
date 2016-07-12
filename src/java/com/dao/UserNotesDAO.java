/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.UserNote;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author User
 */
public class UserNotesDAO {
    public void addNote(UserNote newUserNote)
    {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into usernotes(patientid,notedby,datetimenoted,notes) values (?,?,?,?)";
            PreparedStatement pstmt =
                   conn.prepareStatement(sql);
            pstmt.setInt(1, newUserNote.getPatientid());
            pstmt.setString(2, newUserNote.getNotedby());
            pstmt.setTimestamp(3, newUserNote.getDatetimenoted());
            pstmt.setString(4, newUserNote.getNotes());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<UserNote> getNurseNotes(int patientid) {
        ArrayList<UserNote> notes = new ArrayList<UserNote>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * "
                       + "from usernotes un "
                       + "join users u on un.notedby = u.licensenum "
                       + "where patientid = ? and u.usertype = 'N' "
                       + "order by datetimenoted desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserNote u = new UserNote();
                u.setPatientid(patientid);
                u.setDatetimenoted(rs.getTimestamp("datetimenoted"));
                u.setNotedby(rs.getString("fullname"));
                u.setNotes(rs.getString("notes"));
                notes.add(u);
            }
            conn.close();
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }
    
     public ArrayList<UserNote> getDoctorNotes(int patientid) {
        ArrayList<UserNote> notes = new ArrayList<UserNote>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * "
                       + "from usernotes un "
                       + "join users u on un.notedby = u.licensenum "
                       + "where patientid = ? and u.usertype = 'M' "
                       + "order by datetimenoted desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserNote u = new UserNote();
                u.setPatientid(patientid);
                u.setDatetimenoted(rs.getTimestamp("datetimenoted"));
                u.setNotedby(rs.getString("fullname"));
                u.setNotes(rs.getString("notes"));
                notes.add(u);
            }
            conn.close();
            return notes;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }
}
