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
public class CityRegion {
    private String cityOrRegionID;
    private String cityOrRegionName;

    /**
     * @return the cityOrRegionID
     */
    public String getCityOrRegionID() {
        return cityOrRegionID;
    }

    /**
     * @param cityOrRegionID the cityOrRegionID to set
     */
    public void setCityOrRegionID(String cityOrRegionID) {
        this.cityOrRegionID = cityOrRegionID;
    }

    /**
     * @return the cityOrRegionname
     */
    public String getCityOrRegionName() {
        return cityOrRegionName;
    }

    /**
     * @param cityOrRegionname the cityOrRegionname to set
     */
    public void setCityOrRegionName(String cityOrRegionname) {
        this.cityOrRegionName = cityOrRegionname;
    }
}
