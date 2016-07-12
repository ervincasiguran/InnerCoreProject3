/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Patient;
import com.model.PatientStatus;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PatientStatusDAO {

    public void newStatus(PatientStatus newPatientStatus) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "inser into patient_has_status (patientid, datetimeadmitted, fromdate, statusid, statusdescription, updatedby) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newPatientStatus.getPatientid());
            pstmt.setTimestamp(2, newPatientStatus.getDatetimeadmitted());
            pstmt.setString(3, newPatientStatus.getStatusid());
            pstmt.setDate(4, new java.sql.Date(newPatientStatus.getFromdate().getTime().getTime()));
            pstmt.setString(5, newPatientStatus.getStatusdescription());
            pstmt.setString(6, newPatientStatus.getUpdatedby());
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void endStatus(PatientStatus newPatientStatus) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update patient_has_status set toDate = ? , statusdescription =?,  updatedby = ? where patientid = ?, datetimeadmitted = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setDate(1, new java.sql.Date(newPatientStatus.getTodate().getTime().getTime()));
            pstmt.setString(2, newPatientStatus.getStatusdescription());
            pstmt.setString(3, newPatientStatus.getUpdatedby());
            pstmt.setInt(4, newPatientStatus.getPatientid());
            pstmt.setTimestamp(5, newPatientStatus.getDatetimeadmitted());
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<PatientStatus> getPatientStatus(Patient newPatient) {
        ArrayList<PatientStatus> patientArray = new ArrayList<PatientStatus>();
        PatientStatus result = null;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from patient_has_status where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newPatient.getPatientid());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result = new PatientStatus();
                result.setPatientid(rs.getInt("patientid"));
                result.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                result.setStatusid(rs.getString("statusid"));
                java.util.Date fromdate = rs.getTimestamp("fromDate");
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(fromdate);
                result.setFromdate(gc);
                java.util.Date todate = rs.getTimestamp("toDate");
                gc = new GregorianCalendar();
                gc.setTime(todate);
                result.setTodate(gc);
                result.setStatusdescription(rs.getString("statusdescription"));
                patientArray.add(result);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patientArray;
    }

}
