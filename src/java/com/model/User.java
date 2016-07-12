/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author Garfield
 */
public class User {
    
    private int licensenum;
    private char type;
    private String username,password,fullname,contactnum,email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the licensenum
     */
    public int getLicensenum() {
        return licensenum;
    }

    /**
     * @param licensenum the licensenum to set
     */
    public void setLicensenum(int licensenum) {
        this.licensenum = licensenum;
    }

    /**
     * @return the type
     */
    public char getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the contactnum
     */
    public String getContactnum() {
        return contactnum;
    }

    /**
     * @param contactnum the contactnum to set
     */
    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }
    
}
