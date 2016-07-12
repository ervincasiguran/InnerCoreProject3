/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author User
 */
public class PatientStatus {
    private int patientid;
    private Timestamp datetimeadmitted;
    private GregorianCalendar fromdate, todate;
    private String statusid, statusdescription, updatedby;

    /**
     * @return the patientid
     */
    public int getPatientid() {
        return patientid;
    }

    /**
     * @param patientid the patientid to set
     */
    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    /**
     * @return the datetimeadmitted
     */
    public Timestamp getDatetimeadmitted() {
        return datetimeadmitted;
    }

    /**
     * @param datetimeadmitted the datetimeadmitted to set
     */
    public void setDatetimeadmitted(Timestamp datetimeadmitted) {
        this.datetimeadmitted = datetimeadmitted;
    }

    /**
     * @return the fromdate
     */
    public GregorianCalendar getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(GregorianCalendar fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public GregorianCalendar getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(GregorianCalendar todate) {
        this.todate = todate;
    }

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
     * @return the statusdescription
     */
    public String getStatusdescription() {
        return statusdescription;
    }

    /**
     * @param statusdescription the statusdescription to set
     */
    public void setStatusdescription(String statusdescription) {
        this.statusdescription = statusdescription;
    }

    /**
     * @return the updatedby
     */
    public String getUpdatedby() {
        return updatedby;
    }

    /**
     * @param updatedby the updatedby to set
     */
    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
    
}
