/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.CityRegion;
import com.model.ConsultStatus;
import com.model.ConsultTicket;
import com.model.MedicalHistory;
import com.model.Medicine;
import com.model.MentalCondition;
import com.model.PatientMedicine;
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
public class ApplicationContentDAO {

    public ArrayList<User> getDoctorIDs() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select licensenum,fullname from users where usertype = 'M'";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<User> doctors = new ArrayList<User>();
            while (rs.next()) {
                User d = new User();
                d.setLicensenum(rs.getInt("licensenum"));
                d.setFullname(rs.getString("fullname"));
                doctors.add(d);
            }
            conn.close();
            return doctors;
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getNurseIDs() {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select licensenum, fullname from users where usertype = 'N'";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<User> nurses = new ArrayList<User>();
            while (rs.next()) {
                User n = new User();
                n.setLicensenum(rs.getInt("licensenum"));
                n.setFullname(rs.getString("fullname"));
                nurses.add(n);
            }
            conn.close();
            return nurses;
        } catch (SQLException ex) {
            Logger.getLogger(MedicalHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<CityRegion> getRegionCodes() {
        ArrayList<CityRegion> regioncodes = new ArrayList<CityRegion>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select regionid, regionname from regionreference order by regionname";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CityRegion cr = new CityRegion();
                cr.setCityOrRegionID(rs.getString("regionid"));
                cr.setCityOrRegionName(rs.getString("regionname"));
                regioncodes.add(cr);
            }
            conn.close();
            return regioncodes;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return regioncodes;
    }

    public ArrayList<CityRegion> getCityCodes() {
        ArrayList<CityRegion> citycodes = new ArrayList<CityRegion>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select cityprovinceid, cityprovincename from cityprovincereference order by cityprovincename";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CityRegion cr = new CityRegion();
                cr.setCityOrRegionID(rs.getString("cityprovinceid"));
                cr.setCityOrRegionName(rs.getString("cityprovincename"));
                citycodes.add(cr);
            }
            conn.close();
            return citycodes;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return citycodes;
    }

    public ArrayList<MentalCondition> getConditions() {
        ArrayList<MentalCondition> conditions = new ArrayList<MentalCondition>();

        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select conditionid, conditionname from mentaldisorders";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MentalCondition mc = new MentalCondition();
                mc.setConditionid(rs.getString("conditionid"));
                mc.setConditionname(rs.getString("conditionname"));
                conditions.add(mc);
            }
            conn.close();
            return conditions;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conditions;
    }
    
    public ArrayList<Medicine> getMedicineList() {
        ArrayList<Medicine> medicinelist = new ArrayList<Medicine>();
        
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select medicineid, medicinename from medicinetable";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Medicine m = new Medicine();
                m.setMedicineid(rs.getString("medicineid"));
                m.setMedicinename(rs.getString("medicinename"));
                medicinelist.add(m);
            }
            conn.close();
            return medicinelist;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return medicinelist;
    }
    public ArrayList<ConsultTicket> getConsultTicket(){
        ArrayList<ConsultTicket> consultTickets = new ArrayList<ConsultTicket>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from consulttable";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ConsultTicket ct = new ConsultTicket();
            while (rs.next()) {
                ct.setPatientid(rs.getInt("patientid"));
                ct.setName(rs.getString("name"));
                ct.setAddress(rs.getString("address"));
                ct.setBirthday(rs.getString("birthday"));
            }
            conn.close();
            return consultTickets;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    
    public ArrayList<ConsultStatus> getConsultStatuses(){
        ArrayList<ConsultStatus> consultStatus = new ArrayList<ConsultStatus>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from consultationstatus";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            ConsultStatus cs = new ConsultStatus();
            while (rs.next()) {
                cs = new ConsultStatus();
                cs.setStatusid(rs.getString("statusid"));
                cs.setStatusname(rs.getString("statusname"));
                consultStatus.add(cs);
            }
            conn.close();
            return consultStatus;
        } catch (SQLException ex) {
            Logger.getLogger(AdmissionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
}
