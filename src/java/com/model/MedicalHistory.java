/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.sql.Timestamp;


/**
 *
 * @author User
 */
public class MedicalHistory {
    private int patientid;
    private String presentingproblems, historyofpresentillness, pastmedicalhistory, pastpersonalhistory, familyhistory;
    private Timestamp datelastedited;
    private String editedby;

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
     * @return the presentingproblems
     */
    public String getPresentingproblems() {
        return presentingproblems;
    }

    /**
     * @param presentingproblems the presentingproblems to set
     */
    public void setPresentingproblems(String presentingproblems) {
        this.presentingproblems = presentingproblems;
    }

    /**
     * @return the historyofpresentillness
     */
    public String getHistoryofpresentillness() {
        return historyofpresentillness;
    }

    /**
     * @param historyofpresentillness the historyofpresentillness to set
     */
    public void setHistoryofpresentillness(String historyofpresentillness) {
        this.historyofpresentillness = historyofpresentillness;
    }

    /**
     * @return the pastmedicalhistory
     */
    public String getPastmedicalhistory() {
        return pastmedicalhistory;
    }

    /**
     * @param pastmedicalhistory the pastmedicalhistory to set
     */
    public void setPastmedicalhistory(String pastmedicalhistory) {
        this.pastmedicalhistory = pastmedicalhistory;
    }

    /**
     * @return the pastpersonalhistory
     */
    public String getPastpersonalhistory() {
        return pastpersonalhistory;
    }

    /**
     * @param pastpersonalhistory the pastpersonalhistory to set
     */
    public void setPastpersonalhistory(String pastpersonalhistory) {
        this.pastpersonalhistory = pastpersonalhistory;
    }

    /**
     * @return the familyhistory
     */
    public String getFamilyhistory() {
        return familyhistory;
    }

    /**
     * @param familyhistory the familyhistory to set
     */
    public void setFamilyhistory(String familyhistory) {
        this.familyhistory = familyhistory;
    }

    /**
     * @return the datelastedited
     */
    public Timestamp getDatelastedited() {
        return datelastedited;
    }

    /**
     * @param datelastedited the datelastedited to set
     */
    public void setDatelastedited(Timestamp datelastedited) {
        this.datelastedited = datelastedited;
    }

    /**
     * @return the editedby
     */
    public String getEditedby() {
        return editedby;
    }

    /**
     * @param editedby the editedby to set
     */
    public void setEditedby(String editedby) {
        this.editedby = editedby;
    }

    
}
