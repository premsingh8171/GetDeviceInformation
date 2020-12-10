/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.usercontacts;

import org.json.JSONObject;
import java.io.Serializable;

public class UserContacts implements Serializable
{
    private String displayName;
    private String mobileNumber;
    private String phoneType;
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getPhoneType() {
        return this.phoneType;
    }
    
    public void setPhoneType(final String phoneType) {
        this.phoneType = phoneType;
    }
    
    public JSONObject toJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("displayName", (Object)this.getDisplayName());
            jsonObject.put("mobileNumber", (Object)this.getMobileNumber());
            jsonObject.put("phoneType", (Object)this.getPhoneType());
            return jsonObject;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
