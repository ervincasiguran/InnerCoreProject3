/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.AdmissionRecord;
import com.model.ContactPerson;
import com.model.MedicalHistory;
import com.model.Patient;
import com.model.PatientMedicine;
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
public class SearchPatientDAO {

    public ArrayList<Patient> searchPatientByName(String name) {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from patientdetails where firstname like ? or lastname like ? order by firstname";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name + "%");
            pstmt.setString(2, name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setPatientid(rs.getInt("patientid"));
                p.setFirstname(rs.getString("firstname"));
                p.setLastname(rs.getString("lastname"));
                p.setMiddlename(rs.getString("middlename"));
                p.setSex(rs.getString("sex").charAt(0));
                p.setBirthday(rs.getString("birthday"));
                p.setSpecificaddress(rs.getString("specificaddress"));
                patients.add(p);
            }
            conn.close();
            return patients;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<AdmissionRecord> searchPatientRecord(int patientid) {
        ArrayList<AdmissionRecord> record = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select a.*, m.conditionname as conditionname, p.patienttypename as patienttypename, pd.firstname as firstname, pd.lastname as lastname, pd.middlename as middlename, u.fullname as admittingphysicianname, u2.fullname as admittingnursename, r.reasonfordischargename "
                    + "from admissionsrecord a join mentaldisorders m on a.conditionid = m.conditionid "
                    + "join patienttype p on a.patienttypeid = p.patienttypeid "
                    + "join patientdetails pd on a.patientid = pd.patientid "
                    + "join users u on a.admittingphysician = u.licensenum "
                    + "join users u2 on a.admittingnurse = u2.licensenum "
                    + "left join reasonfordischarge r on r.reasonfordischargeID = a.reasonfordischargeID "
                    + "where a.patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(patientid);
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setDischargedate(rs.getTimestamp("dischargedate"));
                r.setConditionid(rs.getString("conditionname"));
                r.setPatienttypeid(rs.getString("patienttypename"));
                r.setFirstname(rs.getString("firstname"));
                r.setMiddlename(rs.getString("middlename"));
                r.setLastname(rs.getString("lastname"));
                r.setAdmittingdiagnosis(rs.getString("admittingdiagnosis"));
                r.setAdmittingnursename(rs.getString("admittingnursename"));
                r.setAdmittingphysicianname(rs.getString("admittingphysicianname"));
                r.setReasonfordischarge(rs.getString("reasonfordischargename"));
                r.setRecommendation(rs.getString("recommendation"));
                r.setFinaldiagnosis(rs.getString("finaldiagnosis"));
                record.add(r);
            }
            conn.close();
            return record;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getConditionName(String conditionid) {
        String conditionname = "";
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select conditionname from mentaldisorders where conditionid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, conditionid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                conditionname = rs.getString("conditionname");
            }
            conn.close();
            return conditionname;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conditionname;
    }

    public Patient getPatient(int patientid) {
        Patient p = new Patient();
        p.setPatientid(patientid);
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from patientdetails p join cityprovincereference c on "
                    + "p.cityprovinceid = c.cityprovinceid join regionreference r on c.regionid = "
                    + "r.regionid "
                    + "where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                p.setFirstname(rs.getString("firstname"));
                p.setLastname(rs.getString("lastname"));
                p.setMiddlename(rs.getString("middlename"));
                p.setRegionid(rs.getString("regionid"));
                p.setCityprovinceid(rs.getString("cityprovinceid"));
                p.setSpecificaddress(rs.getString("specificaddress"));
                p.setBirthplace(rs.getString("birthplace"));
                p.setBirthday(rs.getString("birthday"));
                p.setSex(rs.getString("sex").charAt(0));
                p.setResidencetelno(rs.getString("residencetelno"));
                p.setCellphonenum(rs.getString("cellphonenum"));
                p.setOccupationname(rs.getString("occupationname"));
                p.setOccupationlandline(rs.getString("occupationlandline"));
                p.setOccupationaddress(rs.getString("occupationaddress"));
                p.setNationality(rs.getString("nationality"));
                p.setReligion(rs.getString("religion"));
                p.setCivilstatus(rs.getString("civilstatus"));
                p.setEducation(rs.getString("education"));
            }
            conn.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public AdmissionRecord getAdmissionRecord(int patientid) {
        AdmissionRecord record = new AdmissionRecord();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from admissionsrecord where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(patientid);
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setDischargedate(rs.getTimestamp("dischargedate"));
                r.setConditionid(rs.getString("conditionname"));
                r.setPatienttypeid(rs.getString("patienttypename"));
                record = r;
            }
            conn.close();
            return record;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return record;
    }
    
    public ArrayList<ContactPerson> getContactPersons(int patientid) {
        ArrayList<ContactPerson> contacts = new ArrayList<ContactPerson>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from contactpersons where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ContactPerson c = new ContactPerson();
                c.setPatientid(patientid);
                c.setContactaddress(rs.getString("contactaddress"));
                c.setContactnumber(rs.getString("contactnumber"));
                c.setContactpersonname(rs.getString("contactpersonname"));
                c.setPartytopaybill(rs.getBoolean("ispartytopaybill"));
                c.setRelationship(rs.getString("relationship"));
                contacts.add(c);
            }
            conn.close();
            return contacts;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }
    
    public MedicalHistory getMedicalHistory(int patientid) {
        MedicalHistory m = new MedicalHistory();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from medicalhistory m join users u on m.editedby = u.licensenum where patientid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, patientid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                m.setPatientid(patientid);
                m.setFamilyhistory(rs.getString("familyhistory"));
                m.setHistoryofpresentillness(rs.getString("historyofpresentillness"));
                m.setPastmedicalhistory(rs.getString("pastmedicalhistory"));
                m.setPastpersonalhistory(rs.getString("pastpersonalhistory"));
                m.setPresentingproblems(rs.getString("presentingproblems"));
                m.setDatelastedited(rs.getTimestamp("datelastedited"));
                m.setEditedby(rs.getString("fullname"));
            }
            conn.close();
            return m;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public ArrayList<AdmissionRecord> advanceSearch(String conditionid, String fromdate, String todate) {
        ArrayList<AdmissionRecord> advancesearchresults = new ArrayList<AdmissionRecord>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "SELECT * FROM admissionsrecord a join (select firstname,lastname,middlename, patientid from patientdetails) p on p.patientID = a.patientID join mentaldisorders m on a.conditionid = m.conditionid where date(datetimeadmitted) between ? and ? and a.conditionid = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fromdate);
            pstmt.setString(2, todate);
            pstmt.setString(3, conditionid);
            ResultSet rs = pstmt.executeQuery();
            AdmissionRecord a;
            while (rs.next()) {
                a = new AdmissionRecord();
                a.setPatientid(rs.getInt("patientid"));
                a.setFirstname(rs.getString("firstname"));
                a.setMiddlename(rs.getString("middlename"));
                a.setLastname(rs.getString("lastname"));
                a.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                a.setConditionid(rs.getString("conditionname"));
                advancesearchresults.add(a);
            }
            conn.close();
            return advancesearchresults;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterPatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return advancesearchresults;
    }
    
    
}
