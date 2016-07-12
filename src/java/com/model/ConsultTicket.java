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
public class ConsultTicket {
    private int patientid;
    private String birthday;
    private String name;
    private String dateofconsultation;
    private String address;
    private char sex;
    private String complaints;
    private int addedby, consultedby;
    private Timestamp datetimadded;
    private String consultstatus;
    private String consultedbyname;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dateofconsultation
     */
    public String getDateofconsultation() {
        return dateofconsultation;
    }

    /**
     * @param dateofconsultation the dateofconsultation to set
     */
    public void setDateofconsultation(String dateofconsultation) {
        this.dateofconsultation = dateofconsultation;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    /**
     * @return the complaints
     */
    public String getComplaints() {
        return complaints;
    }

    /**
     * @param complaints the complaints to set
     */
    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    /**
     * @return the addedby
     */
    public int getAddedby() {
        return addedby;
    }

    /**
     * @param addedby the addedby to set
     */
    public void setAddedby(int addedby) {
        this.addedby = addedby;
    }

    /**
     * @return the consultedby
     */
    public int getConsultedby() {
        return consultedby;
    }

    /**
     * @param consultedby the consultedby to set
     */
    public void setConsultedby(int consultedby) {
        this.consultedby = consultedby;
    }

    /**
     * @return the datetimadded
     */
    public Timestamp getDatetimadded() {
        return datetimadded;
    }

    /**
     * @param datetimadded the datetimadded to set
     */
    public void setDatetimadded(Timestamp datetimadded) {
        this.datetimadded = datetimadded;
    }

    /**
     * @return the consultstatus
     */
    public String getConsultstatus() {
        return consultstatus;
    }

    /**
     * @param consultstatus the consultstatus to set
     */
    public void setConsultstatus(String consultstatus) {
        this.consultstatus = consultstatus;
    }

    /**
     * @return the consultedbyname
     */
    public String getConsultedbyname() {
        return consultedbyname;
    }

    /**
     * @param consultedbyname the consultedbyname to set
     */
    public void setConsultedbyname(String consultedbyname) {
        this.consultedbyname = consultedbyname;
    }
    
    
}
