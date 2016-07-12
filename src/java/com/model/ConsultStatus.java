/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author User
 */
public class ConsultStatus {
    private String statusid;
    private String statusname;
    
    public ConsultStatus(){}

    /**
     * @return the statusid
     */
    public String getStatusid() {
        return statusid;
    }

    /**
     * @param statusid the statusid to set
     */
    public void setStatusid(String statusid) {
        this.statusid = statusid;
    }

    /**
     * @return the statusname
     */
    public String getStatusname() {
        return statusname;
    }

    /**
     * @param statusname the statusname to set
     */
    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }
}
