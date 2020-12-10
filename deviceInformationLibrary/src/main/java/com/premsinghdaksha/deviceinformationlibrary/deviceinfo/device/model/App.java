/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model;

import org.json.JSONObject;
import android.content.Context;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.DeviceInfo;

public class App
{
    private String appVersionName;
    private Integer appVersionCode;
    private String packageName;
    private String activityName;
    private String appName;
    
    public App(final Context context) {
        final DeviceInfo deviceInfo = new DeviceInfo(context);
        this.appVersionName = deviceInfo.getVersionName();
        this.appVersionCode = deviceInfo.getVersionCode();
        this.packageName = deviceInfo.getPackageName();
        this.activityName = deviceInfo.getActivityName();
        this.appName = deviceInfo.getAppName();
    }
    
    public String getAppVersionName() {
        return this.appVersionName;
    }
    
    public void setAppVersionName(final String appVersionName) {
        this.appVersionName = appVersionName;
    }
    
    public Integer getAppVersionCode() {
        return this.appVersionCode;
    }
    
    public void setAppVersionCode(final Integer appVersionCode) {
        this.appVersionCode = appVersionCode;
    }
    
    public String getPackageName() {
        return this.packageName;
    }
    
    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }
    
    public String getActivityName() {
        return this.activityName;
    }
    
    public void setActivityName(final String activityName) {
        this.activityName = activityName;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public JSONObject toJSON() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("appVersionName", (Object)this.getAppVersionName());
            jsonObject.put("appVersionCode", (Object)this.getAppVersionCode());
            jsonObject.put("packageName", (Object)this.getPackageName());
            jsonObject.put("activityName", (Object)this.getActivityName());
            jsonObject.put("appName", (Object)this.getAppName());
            return jsonObject;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
