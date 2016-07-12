/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author User
 */
public class Patient {

    private int patientid;
    private String firstname, lastname, middlename, regionid, cityprovinceid, specificaddress, birthplace,
            cellphonenum, occupationname, occupationaddress, nationality, civilstatus, religion, education, residencetelno, occupationlandline;
    private char sex;
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
     * @return the residencetelno
     */
    public String getResidencetelno() {
        return residencetelno;
    }

    /**
     * @param residencetelno the residencetelno to set
     */
    public void setResidencetelno(String residencetelno) {
        this.residencetelno = residencetelno;
    }

    /**
     * @return the occupationlandline
     */
    public String getOccupationlandline() {
        return occupationlandline;
    }

    /**
     * @param occupationlandline the occupationlandline to set
     */
    public void setOccupationlandline(String occupationlandline) {
        this.occupationlandline = occupationlandline;
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
     * @return the regionid
     */
    public String getRegionid() {
        return regionid;
    }

    /**
     * @param regionid the regionid to set
     */
    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    /**
     * @return the cityprovinceid
     */
    public String getCityprovinceid() {
        return cityprovinceid;
    }

    /**
     * @param cityprovinceid the cityprovinceid to set
     */
    public void setCityprovinceid(String cityprovinceid) {
        this.cityprovinceid = cityprovinceid;
    }

    /**
     * @return the specificaddress
     */
    public String getSpecificaddress() {
        return specificaddress;
    }

    /**
     * @param specificaddress the specificaddress to set
     */
    public void setSpecificaddress(String specificaddress) {
        this.specificaddress = specificaddress;
    }

    /**
     * @return the birthdplace
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * @param birthdplace the birthdplace to set
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * @return the cellphonenum
     */
    public String getCellphonenum() {
        return cellphonenum;
    }

    /**
     * @param cellphonenum the cellphonenum to set
     */
    public void setCellphonenum(String cellphonenum) {
        this.cellphonenum = cellphonenum;
    }

    /**
     * @return the occupationname
     */
    public String getOccupationname() {
        return occupationname;
    }

    /**
     * @param occupationname the occupationname to set
     */
    public void setOccupationname(String occupationname) {
        this.occupationname = occupationname;
    }

    /**
     * @return the occupationaddress
     */
    public String getOccupationaddress() {
        return occupationaddress;
    }

    /**
     * @param occupationaddress the occupationaddress to set
     */
    public void setOccupationaddress(String occupationaddress) {
        this.occupationaddress = occupationaddress;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the civilstatus
     */
    public String getCivilstatus() {
        return civilstatus;
    }

    /**
     * @param civilstatus the civilstatus to set
     */
    public void setCivilstatus(String civilstatus) {
        this.civilstatus = civilstatus;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
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

}
