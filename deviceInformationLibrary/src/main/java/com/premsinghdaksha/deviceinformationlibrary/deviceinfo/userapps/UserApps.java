/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.userapps;

import org.json.JSONObject;
import java.io.Serializable;

public class UserApps implements Serializable
{
    private String appName;
    private String packageName;
    private String versionName;
    private int versionCode;
    
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }
    
    public String getVersionName() {
        return this.versionName;
    }
    
    public void setVersionName(final String versionName) {
        this.versionName = versionName;
    }
    
    public int getVersionCode() {
        return this.versionCode;
    }
    
    public void setVersionCode(final int versionCode) {
        this.versionCode = versionCode;
    }
    
    public JSONObject toJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appName", (Object)this.getAppName());
            jsonObject.put("packageName", (Object)this.getPackageName());
            jsonObject.put("versionName", (Object)this.getVersionName());
            jsonObject.put("versionCode", this.getVersionCode());
            return jsonObject;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
