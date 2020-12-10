/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.location;

import java.io.Serializable;

public class DeviceLocation implements Serializable
{
    private Double latitude;
    private Double longitude;
    private String addressLine1;
    private String city;
    private String state;
    private String countryCode;
    private String postalCode;
    
    public Double getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }
    
    public String getAddressLine1() {
        return this.addressLine1;
    }
    
    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public String getState() {
        return this.state;
    }
    
    public void setState(final String state) {
        this.state = state;
    }
    
    public String getCountryCode() {
        return this.countryCode;
    }
    
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
    
    public String getPostalCode() {
        return this.postalCode;
    }
    
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }
}
