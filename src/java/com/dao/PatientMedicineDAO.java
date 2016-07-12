/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.MedicalHistory;
import com.model.PatientMedicine;
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
public class PatientMedicineDAO {
    public void addPresribedMedicine(PatientMedicine newPatientMedicine)
    {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into medicinetreatspatient(patientid, medicineid, prescribedby, fromdate, todate, remarks) values (?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newPatientMedicine.getPatientid());
            pstmt.setString(2, newPatientMedicine.getMedicineid());
            pstmt.setString(3, newPatientMedicine.getPrescribedby());
            pstmt.setString(4, newPatientMedicine.getFromdate());
            pstmt.setString(5, newPatientMedicine.getTodate());
            pstmt.setString(6, newPatientMedicine.getRemarks());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ArrayList<PatientMedicine> getPatientMedicine(int patientid) {
        ArrayList<PatientMedicine> medicines = new ArrayList<PatientMedicine>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * "
                    + "from medicinetreatspatient mp "
                    + "join medicinetable m on mp.medicineid = m.medicineid "
                    + "join users u on mp.prescribedby = u.licensenum "
                    + "where patientid = ? "
                    + "order by fromdate desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PatientMedicine m = new PatientMedicine();
                m.setPatientid(patientid);
                m.setMedicineid(rs.getString("medicinename"));
                m.setPrescribedby(rs.getString("fullname"));
                m.setFromdate(rs.getString("fromdate"));
                m.setTodate(rs.getString("todate"));
                m.setRemarks(rs.getString("remarks"));
                medicines.add(m);
            }
            conn.close();
            return medicines;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicines;
    }
    
    public ArrayList<PatientMedicine> getPrescribedToday(int patientid, String dateToday) {
        ArrayList<PatientMedicine> medicines = new ArrayList<PatientMedicine>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * "
                    + "from medicinetreatspatient mp "
                    + "join medicinetable m on mp.medicineid = m.medicineid "
                    + "join users u on mp.prescribedby = u.licensenum "
                    + "where patientid = ? and "
                    + "fromdate = ? "
                    + "order by fromdate desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            pstmt.setString(2, dateToday);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PatientMedicine m = new PatientMedicine();
                m.setPatientid(patientid);
                m.setMedicineid(rs.getString("medicinename"));
                m.setPrescribedby(rs.getString("fullname"));
                m.setFromdate(rs.getString("fromdate"));
                m.setTodate(rs.getString("todate"));
                m.setRemarks(rs.getString("remarks"));
                medicines.add(m);
            }
            conn.close();
            return medicines;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicines;
    }
    
}
