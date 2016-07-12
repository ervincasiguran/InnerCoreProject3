/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PatientDAO {
    
    public void editPatientDetails(Patient p){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update patientdetails " +
"set specificaddress = ?, " +
"regionid = ?, " +
"cityProvinceID = ?, " +
"residenceTelNo = ?, " +
"cellphonenum = ?, " +
"occupationname = ?, " +
"occupationaddress = ?, " +
"occupationlandline = ?, " +
"civilstatus = ? " +
"where patientid = ? ";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getSpecificaddress());
            pstmt.setString(2, p.getRegionid());
            pstmt.setString(3, p.getCityprovinceid());
            pstmt.setString(4, p.getResidencetelno());
            pstmt.setString(5, p.getCellphonenum());
            pstmt.setString(6, p.getOccupationname());
            pstmt.setString(7, p.getOccupationaddress());
            pstmt.setString(8, p.getOccupationlandline());
            pstmt.setString(9, p.getCivilstatus());
            pstmt.setInt(10, p.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
