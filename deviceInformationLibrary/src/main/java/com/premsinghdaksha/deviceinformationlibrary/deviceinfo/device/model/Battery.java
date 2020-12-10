/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model;

import org.json.JSONObject;
import android.content.Context;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.DeviceInfo;

public class Battery
{
    private int batteryPercent;
    private boolean isPhoneCharging;
    private String batteryHealth;
    private String batteryTechnology;
    private float batteryTemperature;
    private int batteryVoltage;
    private String chargingSource;
    private boolean isBatteryPresent;
    
    public Battery(final Context context) {
        final DeviceInfo deviceInfo = new DeviceInfo(context);
        this.batteryPercent = deviceInfo.getBatteryPercent();
        this.isPhoneCharging = deviceInfo.isPhoneCharging();
        this.batteryHealth = deviceInfo.getBatteryHealth();
        this.batteryTechnology = deviceInfo.getBatteryTechnology();
        this.batteryTemperature = deviceInfo.getBatteryTemperature();
        this.batteryVoltage = deviceInfo.getBatteryVoltage();
        this.chargingSource = deviceInfo.getChargingSource();
        this.isBatteryPresent = deviceInfo.isBatteryPresent();
    }
    
    public int getBatteryPercent() {
        return this.batteryPercent;
    }
    
    public void setBatteryPercent(final int batteryPercent) {
        this.batteryPercent = batteryPercent;
    }
    
    public boolean isPhoneCharging() {
        return this.isPhoneCharging;
    }
    
    public void setPhoneCharging(final boolean phoneCharging) {
        this.isPhoneCharging = phoneCharging;
    }
    
    public String getBatteryHealth() {
        return this.batteryHealth;
    }
    
    public void setBatteryHealth(final String batteryHealth) {
        this.batteryHealth = batteryHealth;
    }
    
    public String getBatteryTechnology() {
        return this.batteryTechnology;
    }
    
    public void setBatteryTechnology(final String batteryTechnology) {
        this.batteryTechnology = batteryTechnology;
    }
    
    public float getBatteryTemperature() {
        return this.batteryTemperature;
    }
    
    public void setBatteryTemperature(final float batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }
    
    public int getBatteryVoltage() {
        return this.batteryVoltage;
    }
    
    public void setBatteryVoltage(final int batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }
    
    public String getChargingSource() {
        return this.chargingSource;
    }
    
    public void setChargingSource(final String chargingSource) {
        this.chargingSource = chargingSource;
    }
    
    public boolean isBatteryPresent() {
        return this.isBatteryPresent;
    }
    
    public void setBatteryPresent(final boolean batteryPresent) {
        this.isBatteryPresent = batteryPresent;
    }
    
    public JSONObject toJSON() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("batteryPercent", this.getBatteryPercent());
            jsonObject.put("isPhoneCharging", this.isPhoneCharging());
            jsonObject.put("batteryHealth", (Object)this.getBatteryHealth());
            jsonObject.put("batteryTechnology", (Object)this.getBatteryTechnology());
            jsonObject.put("batteryTemperature", (double)this.getBatteryTemperature());
            jsonObject.put("batteryVoltage", this.getBatteryVoltage());
            jsonObject.put("chargingSource", (Object)this.getChargingSource());
            jsonObject.put("isBatteryPresent", this.isBatteryPresent());
            return jsonObject;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
