/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UserDAO {

    public void register(User oneUser) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "insert into users(licensenum,usertype,username,password,fullname,contactnum,email) values (?,?,?,sha1(?),?,?,?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setInt(1, oneUser.getLicensenum());
            pstmt.setString(2, "" + oneUser.getType());
            pstmt.setString(3, oneUser.getUsername());
            pstmt.setString(4, oneUser.getPassword());
            pstmt.setString(5, oneUser.getFullname());
            pstmt.setString(6, oneUser.getContactnum());
            pstmt.setString(7, oneUser.getEmail());
            int rows = pstmt.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean login(User oneUser) {
        boolean successful = false;
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from users where username = ? and password = sha1(?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, oneUser.getUsername());
            pstmt.setString(2, oneUser.getPassword().concat(oneUser.getUsername()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                successful = true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return successful;
    }

    public HashMap<String, String> strictLogin(User oneUser) {
        boolean successful = false;
        HashMap<String, String> rights = new HashMap<String, String>();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql =    " select a.applicationname as rights from users u join"
                          + " applicationperusertype apu on u.usertype = apu.usertype join"
                          + " applications a on apu.applicationid = a.applicationid"
                          + " where username = ? and password = sha1(?) ";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, oneUser.getUsername());
            pstmt.setString(2, oneUser.getPassword().concat(oneUser.getUsername()));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                successful = true;
                rights.put(rs.getString("rights"), "TRUE");
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (successful) {
            return rights;
        } else {
            return null;
        }
    }

    public User getUser(User oneUser) {
        User user = new User();
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from users where username = ? and password = sha1(?)";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, oneUser.getUsername());
            pstmt.setString(2, oneUser.getPassword().concat(oneUser.getUsername()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setType(rs.getString("usertype").charAt(0));
                user.setLicensenum(rs.getInt("licensenum"));
                user.setFullname(rs.getString("fullname"));
                user.setType(rs.getString("usertype").charAt(0));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public ArrayList<User> searchUser(User oneUser) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from users where username like ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + oneUser.getUsername() + "%");
            ResultSet rs = pstmt.executeQuery();
            User resultUser;
            ArrayList<User> output = new ArrayList<User>();
            while (rs.next()) {
                resultUser = new User();
                resultUser.setUsername(rs.getString("username"));
                resultUser.setPassword(rs.getString("password"));
                resultUser.setEmail(rs.getString("email"));
                output.add(resultUser);
            }
            conn.close();
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> searchDoctors(User oneUser) {
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "select * from users where usertype = 'D' ?";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + oneUser.getUsername() + "%");
            ResultSet rs = pstmt.executeQuery();
            User resultUser;
            ArrayList<User> output = new ArrayList<User>();
            while (rs.next()) {
                resultUser = new User();
                resultUser.setFullname(rs.getString("fullname"));
                resultUser.setLicensenum(rs.getInt("licensenum"));
                output.add(resultUser);
            }
            conn.close();
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateAvailability(User oneUser){
        try {
            DBConnectionFactory myFactory = DBConnectionFactory.getInstance();
            Connection conn = myFactory.getConnection();
            String sql = "";
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + oneUser.getUsername() + "%");
            pstmt.executeQuery();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
