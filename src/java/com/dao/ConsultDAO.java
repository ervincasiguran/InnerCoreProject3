/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.ConsultTicket;
import com.model.User;
import java.sql.Connection;
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
public class ConsultDAO {

    public ConsultTicket getTicket(ConsultTicket ct, String datetoday) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "SELECT * FROM consulttable WHERE patientid= ? and dateofconsultation = ? ;";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, ct.getPatientid());
            pstmt.setString(2, datetoday);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ct.setPatientid(rs.getInt("patientid"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
            }
            conn.close();
            return ct;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addTicket2(ConsultTicket ct) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            int rows;
            String sql;
            PreparedStatement pstmt;
            ResultSet rs;


            sql = "insert into consulttable(patientid, dateofconsultation, complaints, addedby, consultedby, datetimeadded) values (?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ct.getPatientid());
            pstmt.setString(2, ct.getDateofconsultation());
            pstmt.setString(3, ct.getComplaints());
            pstmt.setInt(4, ct.getAddedby());
            pstmt.setInt(5, ct.getConsultedby());
            pstmt.setTimestamp(6, ct.getDatetimadded());
            rows = pstmt.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ConsultTicket> getDoctorSchedule(User loggedInUser, String fromdate, String todate) {
        ArrayList<ConsultTicket> schedule = new ArrayList<ConsultTicket>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = " select * from consulttable c join patientdetails p "
                    + "on p.patientid = c.patientid "
                    + "where consultedby = ? "
                    + "and dateofconsultation between date(?) and date(?) order by dateofconsultation desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, loggedInUser.getLicensenum());
            pstmt.setString(2, fromdate);
            pstmt.setString(3, todate);
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct;
            while (rs.next()) {
                ct = new ConsultTicket();
                ct.setPatientid(rs.getInt("patientid"));
                ct.setName(rs.getString("firstname") + " " + rs.getString("middlename") + " " + rs.getString("lastname"));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
                schedule.add(ct);
            }
            conn.close();
            return schedule;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ConsultTicket> searchTicket(String name) {
        ArrayList<ConsultTicket> tickets = new ArrayList<ConsultTicket>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = " select * from consulttable where name like ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct;
            while (rs.next()) {
                ct = new ConsultTicket();
                ct.setPatientid(rs.getInt("patientid"));
                ct.setName(rs.getString("name"));
                if (rs.getString("address") != null)
                    ct.setAddress(rs.getString("address"));
                if(rs.getString("birthday") != null)
                    ct.setBirthday(rs.getString("birthday"));
                if(rs.getString("sex") != null) 
                    ct.setSex(rs.getString("sex").charAt(0));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
                tickets.add(ct);
            }
            conn.close();
            return tickets;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<ConsultTicket> getSchedule(String fromdate, String todate) {
        ArrayList<ConsultTicket> schedule = new ArrayList<ConsultTicket>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "select * from consulttable ct join consultationstatus c on ct.consultstatus = c.statusid " +
"join patientdetails pt on ct.patientid = pt.patientid " +
"where dateofconsultation between date(?) and date(?) order by dateofconsultation desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, fromdate);
            pstmt.setString(2, todate);
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct;
            while (rs.next()) {
                ct = new ConsultTicket();
                ct.setPatientid(rs.getInt("patientid"));
                ct.setName(rs.getString("firstname") + " " + rs.getString("middlename") + " " + rs.getString("lastname"));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
                ct.setConsultstatus(rs.getString("statusname"));
                schedule.add(ct);
            }
            conn.close();
            return schedule;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }

    public ArrayList<ConsultTicket> getTodaySchedule(User user, String dateToday) {
        ArrayList<ConsultTicket> schedule = new ArrayList<ConsultTicket>();
        try {
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "select * from consulttable ct " +
"join consultationstatus c on ct.consultstatus = c.statusid "+ 
"join patientdetails p on ct.patientid = p.patientid " +
"where consultedby = ? and dateofconsultation = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getLicensenum());
            pstmt.setString(2, dateToday);
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct;
            while (rs.next()) {
                ct = new ConsultTicket();
                ct.setPatientid(rs.getInt("patientid"));
                ct.setName(rs.getString("firstname") + " " + rs.getString("middlename") + " " + rs.getString("lastname"));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
                ct.setConsultstatus(rs.getString("statusname"));
                schedule.add(ct);
            }
            conn.close();
            return schedule;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateConsultation(ConsultTicket ct) {
        try {
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "update consulttable set consultstatus = ? where patientid = ? and dateofconsultation = ? and consultedby = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, ct.getConsultstatus().substring(0, 1));
            pstmt.setInt(2, ct.getPatientid());
            pstmt.setString(3, ct.getDateofconsultation());
            pstmt.setInt(4, ct.getConsultedby());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rescheduleConsultation (String reschedDate, ConsultTicket ct) {
        try {
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "UPDATE consulttable SET dateofconsultation = ? WHERE patientid= ? and dateofconsultation= ? and consultedby = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, reschedDate);
            pstmt.setInt(2, ct.getPatientid());
            pstmt.setString(3, ct.getDateofconsultation());
            pstmt.setInt(4, ct.getConsultedby());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<ConsultTicket> getConsultationHistory (int patientid) {
        ArrayList<ConsultTicket> consultationHistory = new ArrayList<ConsultTicket>();
        try {
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();

            String sql = "select * from consulttable ct where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct;
            while (rs.next()) {
                ct = new ConsultTicket();
                ct.setPatientid(rs.getInt("patientid"));
                ct.setConsultedby(rs.getInt("consultedby"));
                ct.setComplaints(rs.getString("complaints"));
                ct.setAddedby(rs.getInt("addedby"));
                ct.setDateofconsultation(rs.getString("dateofconsultation"));
                consultationHistory.add(ct);
            }
            conn.close();
            return consultationHistory;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
