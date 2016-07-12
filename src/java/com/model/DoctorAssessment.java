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
public class DoctorAssessment {
    private int patientid;
    private Timestamp datetimeadmitted;
    private GregorianCalendar fromdate;
    private String recommendation, assessedby;

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
     * @return the recommendation
     */
    public String getRecommendation() {
        return recommendation;
    }

    /**
     * @param recommendation the recommendation to set
     */
    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    /**
     * @return the assessedby
     */
    public String getAssessedby() {
        return assessedby;
    }

    /**
     * @param assessedby the assessedby to set
     */
    public void setAssessedby(String assessedby) {
        this.assessedby = assessedby;
    }
}
