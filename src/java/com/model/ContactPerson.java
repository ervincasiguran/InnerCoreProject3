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
public class ContactPerson {
    private int patientid;
    private String contactpersonname, relationship, contactnumber, contactaddress;
    private boolean partytopaybill;

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
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * @return the contactnumber
     */
    public String getContactnumber() {
        return contactnumber;
    }

    /**
     * @param contactnumber the contactnumber to set
     */
    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    /**
     * @return the contactaddress
     */
    public String getContactaddress() {
        return contactaddress;
    }

    /**
     * @param contactaddress the contactaddress to set
     */
    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    /**
     * @return the partytopaybill
     */
    public boolean isPartytopaybill() {
        return partytopaybill;
    }

    /**
     * @param partytopaybill the partytopaybill to set
     */
    public void setPartytopaybill(boolean partytopaybill) {
        this.partytopaybill = partytopaybill;
    }

    /**
     * @return the contactpersonname
     */
    public String getContactpersonname() {
        return contactpersonname;
    }

    /**
     * @param contactpersonname the contactpersonname to set
     */
    public void setContactpersonname(String contactpersonname) {
        this.contactpersonname = contactpersonname;
    }
}
