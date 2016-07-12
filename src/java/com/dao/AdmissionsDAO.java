/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.AdmissionRecord;
import com.model.AdmissionReport;
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
public class AdmissionsDAO {

    public void admitPatient(AdmissionRecord newRecord) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            
            int rows;
            String sql = "insert into admissionsrecord(patientid,datetimeadmitted,conditionid, admittingdiagnosis, patienttypeid, admittingnurse, admittingphysician) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, newRecord.getPatientid());
            pstmt.setTimestamp(2, newRecord.getDatetimeadmitted());
            pstmt.setString(3, newRecord.getConditionid());
            pstmt.setString(4, newRecord.getAdmittingdiagnosis());
            pstmt.setString(5, newRecord.getPatienttypeid());
            pstmt.setInt(6, newRecord.getAdmittingnurse());
            pstmt.setInt(7, newRecord.getAdmittingphysician());
            rows = pstmt.executeUpdate();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dischargePatient(AdmissionRecord oldRecord) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "update admissionsrecord set reasonfordischargeid = ?, dischargedby = ?, dischargeDate = ?, finaldiagnosis =?, recommendation = ? where patientid = ? and datetimeadmitted = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, ""+oldRecord.getReasonfordischarge().charAt(0));
            pstmt.setString(2, oldRecord.getDischargedby());
            pstmt.setTimestamp(3, oldRecord.getDischargedate());
            pstmt.setString(4, oldRecord.getFinaldiagnosis());
            pstmt.setString(5, oldRecord.getRecommendation());
            pstmt.setInt(6, oldRecord.getPatientid());
            pstmt.setTimestamp(7, oldRecord.getDatetimeadmitted());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<AdmissionRecord> getThisMonthActivePatient(int year, int month) {
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select a.*, m.conditionname as conditionname, p.birthday as birthday, p.firstname, p.middlename, p.lastname " +
                         "from admissionsrecord a " +
                         "join mentaldisorders m on a.conditionID = m.conditionid " +
                         "join patientdetails p on a.patientid = p.patientid " +
                         "where year(datetimeadmitted)= ? and month(datetimeadmitted)= ? " +
                         "and (a.patienttypeid = 'IA' || a.patienttypeid = 'OA') and dischargedate is null order by datetimeadmitted desc;";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(rs.getInt("patientid"));
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setConditionid(rs.getString("conditionname"));
                r.setAdmittingdiagnosis(rs.getString("admittingdiagnosis"));
                r.setBirthday(rs.getString("birthday"));
                r.setPatienttypeid(rs.getString("patienttypeid"));
                r.setFirstname(rs.getString("firstname"));
                r.setMiddlename(rs.getString("middlename"));
                r.setLastname(rs.getString("lastname"));
                records.add(r);
            }
            conn.close();
            return records;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<AdmissionRecord> generateYearlyReport(int year) {
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from admissionsrecord where year(datetimeadmitted) = ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<AdmissionRecord> getDischargedPatientsForThisMonth (int year, int month) {
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select a.*, m.conditionname as conditionname, p.birthday as birthday, p.firstname, p.middlename, p.lastname, r.reasonfordischargename from admissionsrecord a  join mentaldisorders m on a.conditionID = m.conditionid  join patientdetails p on a.patientid = p.patientid  join reasonfordischarge r on a.reasonfordischargeid = r.reasonfordischargeid where month(dischargedate)= ? and year(dischargedate) = ? order by dischargedate desc";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(rs.getInt("patientid"));
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setConditionid(rs.getString("conditionname"));
                r.setAdmittingdiagnosis(rs.getString("admittingdiagnosis"));
                r.setBirthday(rs.getString("birthday"));
                r.setPatienttypeid(rs.getString("patienttypeid"));
                r.setFirstname(rs.getString("firstname"));
                r.setMiddlename(rs.getString("middlename"));
                r.setLastname(rs.getString("lastname"));
                r.setDischargedate(rs.getTimestamp("dischargedate"));
                r.setReasonfordischarge(rs.getString("reasonfordischargename"));
                records.add(r);
            }
            conn.close();
            return records;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<AdmissionRecord> getThisMonthInpatient(int year, int month) {
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select a.*, m.conditionname as conditionname, p.birthday as birthday, p.firstname, p.middlename, p.lastname " +
                         "from admissionsrecord a " +
                         "join mentaldisorders m on a.conditionID = m.conditionid " +
                         "join patientdetails p on a.patientid = p.patientid " +
                         "where year(datetimeadmitted)= ? and month(datetimeadmitted)= ? " +
                         "and a.patienttypeid = 'IA' and dischargedate is null order by datetimeadmitted desc;";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(rs.getInt("patientid"));
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setConditionid(rs.getString("conditionname"));
                r.setAdmittingdiagnosis(rs.getString("admittingdiagnosis"));
                r.setBirthday(rs.getString("birthday"));
                r.setPatienttypeid(rs.getString("patienttypeid"));
                r.setFirstname(rs.getString("firstname"));
                r.setMiddlename(rs.getString("middlename"));
                r.setLastname(rs.getString("lastname"));
                records.add(r);
            }
            conn.close();
            return records;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<AdmissionRecord> getThisMonthOutpatient(int year, int month) {
        ArrayList<AdmissionRecord> records = new ArrayList<AdmissionRecord>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select a.*, m.conditionname as conditionname, p.birthday as birthday, p.firstname, p.middlename, p.lastname " +
                         "from admissionsrecord a " +
                         "join mentaldisorders m on a.conditionID = m.conditionid " +
                         "join patientdetails p on a.patientid = p.patientid " +
                         "where year(datetimeadmitted)= ? and month(datetimeadmitted)= ? " +
                         "and a.patienttypeid = 'OA' and dischargedate is null order by datetimeadmitted desc;";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AdmissionRecord r = new AdmissionRecord();
                r.setPatientid(rs.getInt("patientid"));
                r.setDatetimeadmitted(rs.getTimestamp("datetimeadmitted"));
                r.setConditionid(rs.getString("conditionname"));
                r.setAdmittingdiagnosis(rs.getString("admittingdiagnosis"));
                r.setBirthday(rs.getString("birthday"));
                r.setPatienttypeid(rs.getString("patienttypeid"));
                r.setFirstname(rs.getString("firstname"));
                r.setMiddlename(rs.getString("middlename"));
                r.setLastname(rs.getString("lastname"));
                records.add(r);
            }
            conn.close();
            return records;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    public ArrayList<AdmissionReport> getTop10Illness() {
        ArrayList<AdmissionReport> reports = new ArrayList<AdmissionReport>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select conditionname, count(patientid) totalcount from admissionsrecord a " +
"join mentaldisorders m on a.conditionID = m.conditionID " +
"group by m.conditionid " +
"order by count(patientid) desc " +
"limit 10;";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                AdmissionReport r = new AdmissionReport();
                r.setConditionid(rs.getString("conditionname"));
                r.setCount(rs.getInt("totalcount"));
                reports.add(r);
            }
            conn.close();
            return reports;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reports;
    }
}
