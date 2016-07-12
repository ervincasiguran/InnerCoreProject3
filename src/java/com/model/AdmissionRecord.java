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
public class AdmissionRecord {
    private int patientid;
    private Timestamp datetimeadmitted, dischargedate;
    private String conditionid, patienttypeid, reasonfordischarge, dischargedby ;
    private int admittingnurse;
    private int admittingphysician;
    private String admittingdiagnosis;
    private String finaldiagnosis;
    private String recommendation;
    
    
    private String firstname;
    private String lastname;
    private String middlename;
    private String admittingphysicianname;
    private String admittingnursename;
    private String birthday;

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
     * @return the conditionid
     */
    public String getConditionid() {
        return conditionid;
    }

    /**
     * @param conditionid the conditionid to set
     */
    public void setConditionid(String conditionid) {
        this.conditionid = conditionid;
    }

    /**
     * @return the patienttypeid
     */
    public String getPatienttypeid() {
        return patienttypeid;
    }

    /**
     * @param patienttypeid the patienttypeid to set
     */
    public void setPatienttypeid(String patienttypeid) {
        this.patienttypeid = patienttypeid;
    }

    /**
     * @return the reasonfordischarge
     */
    public String getReasonfordischarge() {
        return reasonfordischarge;
    }

    /**
     * @param reasonfordischarge the reasonfordischarge to set
     */
    public void setReasonfordischarge(String reasonfordischarge) {
        this.reasonfordischarge = reasonfordischarge;
    }

    /**
     * @return the dischargedby
     */
    public String getDischargedby() {
        return dischargedby;
    }

    /**
     * @param dischargedby the dischargedby to set
     */
    public void setDischargedby(String dischargedby) {
        this.dischargedby = dischargedby;
    }

    /**
     * @return the dischargedate
     */
    public Timestamp getDischargedate() {
        return dischargedate;
    }

    /**
     * @param dischargedate the dischargedate to set
     */
    public void setDischargedate(Timestamp dischargedate) {
        this.dischargedate = dischargedate;
    }

    /**
     * @return the admittingnurse
     */
    public int getAdmittingnurse() {
        return admittingnurse;
    }

    /**
     * @param admittingnurse the admittingnurse to set
     */
    public void setAdmittingnurse(int admittingnurse) {
        this.admittingnurse = admittingnurse;
    }

    /**
     * @return the admittingphysician
     */
    public int getAdmittingphysician() {
        return admittingphysician;
    }

    /**
     * @param admittingphysician the admittingphysician to set
     */
    public void setAdmittingphysician(int admittingphysician) {
        this.admittingphysician = admittingphysician;
    }

    /**
     * @return the admittingdiagnosis
     */
    public String getAdmittingdiagnosis() {
        return admittingdiagnosis;
    }

    /**
     * @param admittingdiagnosis the admittingdiagnosis to set
     */
    public void setAdmittingdiagnosis(String admittingdiagnosis) {
        this.admittingdiagnosis = admittingdiagnosis;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the admittingphysicianname
     */
    public String getAdmittingphysicianname() {
        return admittingphysicianname;
    }

    /**
     * @param admittingphysicianname the admittingphysicianname to set
     */
    public void setAdmittingphysicianname(String admittingphysicianname) {
        this.admittingphysicianname = admittingphysicianname;
    }

    /**
     * @return the admittingnursename
     */
    public String getAdmittingnursename() {
        return admittingnursename;
    }

    /**
     * @param admittingnursename the admittingnursename to set
     */
    public void setAdmittingnursename(String admittingnursename) {
        this.admittingnursename = admittingnursename;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the finaldiagnosis
     */
    public String getFinaldiagnosis() {
        return finaldiagnosis;
    }

    /**
     * @param finaldiagnosis the finaldiagnosis to set
     */
    public void setFinaldiagnosis(String finaldiagnosis) {
        this.finaldiagnosis = finaldiagnosis;
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

    



    
}
