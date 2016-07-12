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
public class PatientMedicine {
    private int patientid;
    private String datetimeadmitted;
    private String fromdate, todate;
    private String medicineid, prescribedby, remarks;

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
    public String getDatetimeadmitted() {
        return datetimeadmitted;
    }

    /**
     * @param datetimeadmitted the datetimeadmitted to set
     */
    public void setDatetimeadmitted(String datetimeadmitted) {
        this.datetimeadmitted = datetimeadmitted;
    }

    /**
     * @return the fromdate
     */
    public String getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public String getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(String todate) {
        this.todate = todate;
    }

    /**
     * @return the medicineid
     */
    public String getMedicineid() {
        return medicineid;
    }

    /**
     * @param medicineid the medicineid to set
     */
    public void setMedicineid(String medicineid) {
        this.medicineid = medicineid;
    }

    /**
     * @return the prescribedby
     */
    public String getPrescribedby() {
        return prescribedby;
    }

    /**
     * @param prescribedby the prescribedby to set
     */
    public void setPrescribedby(String prescribedby) {
        this.prescribedby = prescribedby;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
