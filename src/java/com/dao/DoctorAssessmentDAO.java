/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.DoctorAssessment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DoctorAssessmentDAO {

    public void assessPatient(DoctorAssessment newDoctorAssessment) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "inser into doctorassessment (patientid, datetimeadmitted, fromdate, recommendation, assessedby) values (?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newDoctorAssessment.getPatientid());
            pstmt.setTimestamp(2, newDoctorAssessment.getDatetimeadmitted());
            pstmt.setDate(3, new java.sql.Date(newDoctorAssessment.getFromdate().getTime().getTime()));
            pstmt.setString(4, newDoctorAssessment.getRecommendation());
            pstmt.setString(5, newDoctorAssessment.getAssessedby());
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
