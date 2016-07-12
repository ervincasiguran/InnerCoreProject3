/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.MedicalHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class MedicalHistoryDAO {
    
    public void addMedicalHistory(MedicalHistory newMedicalHistory){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into medicalhistory(patientid,presentingproblems, historyofpresentillness, pastmedicalhistory, pastpersonalhistory, familyhistory,editedby,datelastedited) values (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newMedicalHistory.getPatientid());
            pstmt.setString(2, newMedicalHistory.getPresentingproblems());
            pstmt.setString(3, newMedicalHistory.getHistoryofpresentillness());
            pstmt.setString(4, newMedicalHistory.getPastmedicalhistory());
            pstmt.setString(5, newMedicalHistory.getPastpersonalhistory());
            pstmt.setString(6, newMedicalHistory.getFamilyhistory());
            pstmt.setString(7, newMedicalHistory.getEditedby());
            pstmt.setTimestamp(8, newMedicalHistory.getDatelastedited());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MedicalHistory searchMedicalHistory(MedicalHistory medicalHistory){
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from medicalhistory where patientid = ?";
            PreparedStatement pstmt =
                   conn.prepareStatement(sql);
            pstmt.setInt(1, medicalHistory.getPatientid());
            ResultSet rs = pstmt.executeQuery();
            MedicalHistory output = new MedicalHistory();
            output.setPresentingproblems(rs.getString("presentingproblems"));
            output.setHistoryofpresentillness(rs.getString("historyofpresentillness"));
            output.setPastmedicalhistory(rs.getString("pastmedicalhistory"));
            output.setPastpersonalhistory(rs.getString("pastpersonalhistory"));
            output.setFamilyhistory(rs.getString("familyhistory"));
            conn.close();
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public void editPresentingProblems(MedicalHistory newMedicalHistory) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update medicalhistory set presentingproblems = ?, datelastedited = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, newMedicalHistory.getPresentingproblems());
            pstmt.setTimestamp(2, newMedicalHistory.getDatelastedited());
            pstmt.setInt(3, newMedicalHistory.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editHistoryOfPresentIllness (MedicalHistory newMedicalHistory) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update medicalhistory set historyofpresentillness = ?, datelastedited = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, newMedicalHistory.getHistoryofpresentillness());
            pstmt.setTimestamp(2, newMedicalHistory.getDatelastedited());
            pstmt.setInt(3, newMedicalHistory.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editPastMedicalHistory (MedicalHistory newMedicalHistory) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update medicalhistory set pastmedicalhistory = ?, datelastedited = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, newMedicalHistory.getPastmedicalhistory());
            pstmt.setTimestamp(2, newMedicalHistory.getDatelastedited());
            pstmt.setInt(3, newMedicalHistory.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editPastPersonalHistory (MedicalHistory newMedicalHistory) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update medicalhistory set pastpersonalhistory = ?, datelastedited = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, newMedicalHistory.getPastpersonalhistory());
            pstmt.setTimestamp(2, newMedicalHistory.getDatelastedited());
            pstmt.setInt(3, newMedicalHistory.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editFamilyHistory (MedicalHistory newMedicalHistory) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update medicalhistory set familyhistory = ?, datelastedited = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, newMedicalHistory.getFamilyhistory());
            pstmt.setTimestamp(2, newMedicalHistory.getDatelastedited());
            pstmt.setInt(3, newMedicalHistory.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkPatientHistory(MedicalHistory medicalhistory) {
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from medicalhistory where patientid = ?";
            PreparedStatement pstmt =
                   conn.prepareStatement(sql);
            pstmt.setInt(1, medicalhistory.getPatientid());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                conn.close();
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
        
}
