/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.AdmissionRecord;
import com.model.ContactPerson;
import com.model.Patient;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class RegisterPatientDAO {

    public Patient addPatient(Patient newPatient) throws SQLException{
            
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into patientdetails(firstname, lastname, middlename, regionID, cityprovinceID, specificaddress, birthday, birthplace, sex, residencetelno, cellphonenum, occupationname, occupationaddress, occupationlandline, nationality, civilstatus, religion, education) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            int rows;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPatient.getFirstname());
            pstmt.setString(2, newPatient.getLastname());
            pstmt.setString(3, newPatient.getMiddlename());
            pstmt.setString(4, newPatient.getRegionid());
            pstmt.setString(5, newPatient.getCityprovinceid());
            pstmt.setString(6, newPatient.getSpecificaddress());
            pstmt.setString(7, newPatient.getBirthday());
            pstmt.setString(8, newPatient.getBirthplace());
            pstmt.setString(9, "" + newPatient.getSex());
            pstmt.setString(10, newPatient.getResidencetelno());
            pstmt.setString(11, newPatient.getCellphonenum());
            pstmt.setString(12, newPatient.getOccupationname());
            pstmt.setString(13, newPatient.getOccupationaddress());
            pstmt.setString(14, newPatient.getOccupationlandline());
            pstmt.setString(15, newPatient.getNationality());
            pstmt.setString(16, newPatient.getCivilstatus());
            pstmt.setString(17, newPatient.getReligion());
            pstmt.setString(18, newPatient.getEducation());
            rows = pstmt.executeUpdate();
            Patient p = new Patient();
            sql = "select max(patientid) from patientdetails limit 1";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                p.setPatientid(rs.getInt("max(patientid)"));
            }
            conn.close();
            return p;
    }

    public AdmissionRecord registerPatient(Patient newPatient, AdmissionRecord newRecord, ContactPerson newContactPerson) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into patientdetails(patientid, firstname, lastname, middlename, regionID, cityprovinceID, specificaddress, birthday, birthplace, sex, residencetelno, cellphonenum, occupationname, occupationaddress, occupationlandline, nationality, civilstatus, religion, education) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            int rows;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newPatient.getPatientid());
            pstmt.setString(2, newPatient.getFirstname());
            pstmt.setString(3, newPatient.getLastname());
            pstmt.setString(4, newPatient.getMiddlename());
            pstmt.setString(5, newPatient.getRegionid());
            pstmt.setString(6, newPatient.getCityprovinceid());
            pstmt.setString(7, newPatient.getSpecificaddress());
            pstmt.setString(8, newPatient.getBirthday());
            pstmt.setString(9, newPatient.getBirthplace());
            pstmt.setString(10, "" + newPatient.getSex());
            pstmt.setString(11, newPatient.getResidencetelno());
            pstmt.setString(12, newPatient.getCellphonenum());
            pstmt.setString(13, newPatient.getOccupationname());
            pstmt.setString(14, newPatient.getOccupationaddress());
            pstmt.setString(15, newPatient.getOccupationlandline());
            pstmt.setString(16, newPatient.getNationality());
            pstmt.setString(17, newPatient.getCivilstatus());
            pstmt.setString(18, newPatient.getReligion());
            pstmt.setString(19, newPatient.getEducation());
            rows = pstmt.executeUpdate();
            
            newContactPerson.setPatientid(newPatient.getPatientid());
            newRecord.setPatientid(newPatient.getPatientid());
            
            sql = "insert into admissionsrecord(patientid,datetimeadmitted,conditionid, admittingdiagnosis, patienttypeid, admittingnurse, admittingphysician) values (?,?,?,?,?,?,?)";
            pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newRecord.getPatientid());
            pstmt.setTimestamp(2, newRecord.getDatetimeadmitted());
            pstmt.setString(3, newRecord.getConditionid());
            pstmt.setString(4, newRecord.getAdmittingdiagnosis());
            pstmt.setString(5, newRecord.getPatienttypeid());
            pstmt.setInt(6, newRecord.getAdmittingnurse());
            pstmt.setInt(7, newRecord.getAdmittingphysician());
            rows = pstmt.executeUpdate();

            addContactPerson(newContactPerson);
            conn.close();
            
            return newRecord;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    public void editContactNum(Patient oldPatient) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update patientdetails set residencetelno = ?,  cellphonenum = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, oldPatient.getResidencetelno());
            pstmt.setString(2, oldPatient.getCellphonenum());
            pstmt.setInt(3, oldPatient.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editAddress(Patient oldPatient) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update patientdetails set regionid = ?, cityprovinceid = ?, specificaddress = ? where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, oldPatient.getRegionid());
            pstmt.setString(2, oldPatient.getCityprovinceid());
            pstmt.setString(3, oldPatient.getSpecificaddress());
            pstmt.setInt(4, oldPatient.getPatientid());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addContactPerson(ContactPerson newContactPerson) throws SQLException {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into contactPersons(patientid,contactpersonname,relationship,contactnumber,contactaddress,ispartytopaybill) values (?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newContactPerson.getPatientid());
            pstmt.setString(2, newContactPerson.getContactpersonname());
            pstmt.setString(3, newContactPerson.getRelationship());
            pstmt.setString(4, newContactPerson.getContactnumber());
            pstmt.setString(5, newContactPerson.getContactaddress());
            pstmt.setBoolean(6, newContactPerson.isPartytopaybill());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }

    }
    
    public Patient getPatientid()
    {
        Patient p = new Patient();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select max(patientid) from patientdetails limit 1";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                
            }
            conn.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
