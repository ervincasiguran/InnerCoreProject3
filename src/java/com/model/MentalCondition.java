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
public class MentalCondition {
    private String conditionid;
    private String conditionname;

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
     * @return the conditionname
     */
    public String getConditionname() {
        return conditionname;
    }

    /**
     * @param conditionname the conditionname to set
     */
    public void setConditionname(String conditionname) {
        this.conditionname = conditionname;
    }
}
