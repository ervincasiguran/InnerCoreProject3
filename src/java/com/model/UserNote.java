/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author User
 */
public class UserNote {
    private int patientid;
    private String datetimeadmitted;
    private Timestamp datetimenoted;
    private String notedby;
    private String notes;

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
     * @return the datetimenoted
     */
    public Timestamp getDatetimenoted() {
        return datetimenoted;
    }

    /**
     * @param datetimenoted the datetimenoted to set
     */
    public void setDatetimenoted(Timestamp datetimenoted) {
        this.datetimenoted = datetimenoted;
    }

    /**
     * @return the licensenum
     */
    public String getNotedby() {
        return notedby;
    }

    /**
     * @param licensenum the licensenum to set
     */
    public void setNotedby(String notedby) {
        this.notedby = notedby;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
