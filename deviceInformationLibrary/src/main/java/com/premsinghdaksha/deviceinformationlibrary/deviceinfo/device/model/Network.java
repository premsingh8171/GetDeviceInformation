/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.DeviceInfo;

import org.json.JSONObject;

public class Network {
    private String IMEI;
    private String IMSI;
    private String phoneType;
    private String phoneNumber;
    private String operator;
    private String sIMSerial;
    private boolean isSimNetworkLocked;
    private boolean isNfcPresent;
    private boolean isNfcEnabled;
    private boolean isWifiEnabled;
    private boolean isNetworkAvailable;
    private String networkClass;
    private String networkType;

    public Network(final Context context) {
        final DeviceInfo deviceInfo = new DeviceInfo(context);
        this.IMEI = deviceInfo.getIMEI();
        this.IMSI = deviceInfo.getIMSI();
        this.phoneType = deviceInfo.getPhoneType();
        this.phoneNumber = deviceInfo.getPhoneNumber();
        this.operator = deviceInfo.getOperator();
        this.sIMSerial = deviceInfo.getSIMSerial();
        this.isSimNetworkLocked = deviceInfo.isSimNetworkLocked();
        this.isNfcPresent = deviceInfo.isNfcPresent();
        this.isNfcEnabled = deviceInfo.isNfcEnabled();
        this.isWifiEnabled = deviceInfo.isWifiEnabled();
        this.isNetworkAvailable = deviceInfo.isNetworkAvailable();
        this.networkClass = deviceInfo.getNetworkClass();
        this.networkType = deviceInfo.getNetworkType();
    }

    public String getIMEI() {
        return this.IMEI;
    }

    public void setIMEI(final String IMEI) {
        this.IMEI = IMEI;
    }

    public String getIMSI() {
        return this.IMSI;
    }

    public void setIMSI(final String IMSI) {
        this.IMSI = IMSI;
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(final String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(final String operator) {
        this.operator = operator;
    }

    public String getsIMSerial() {
        return this.sIMSerial;
    }

    public void setsIMSerial(final String sIMSerial) {
        this.sIMSerial = sIMSerial;
    }

    public boolean isSimNetworkLocked() {
        return this.isSimNetworkLocked;
    }

    public void setSimNetworkLocked(final boolean simNetworkLocked) {
        this.isSimNetworkLocked = simNetworkLocked;
    }

    public boolean isNfcPresent() {
        return this.isNfcPresent;
    }

    public void setNfcPresent(final boolean nfcPresent) {
        this.isNfcPresent = nfcPresent;
    }

    public boolean isNfcEnabled() {
        return this.isNfcEnabled;
    }

    public void setNfcEnabled(final boolean nfcEnabled) {
        this.isNfcEnabled = nfcEnabled;
    }

    public boolean isWifiEnabled() {
        return this.isWifiEnabled;
    }

    public void setWifiEnabled(final boolean wifiEnabled) {
        this.isWifiEnabled = wifiEnabled;
    }

    public boolean isNetworkAvailable() {
        return this.isNetworkAvailable;
    }

    public void setNetworkAvailable(final boolean networkAvailable) {
        this.isNetworkAvailable = networkAvailable;
    }

    public String getNetworkClass() {
        return this.networkClass;
    }

    public void setNetworkClass(final String networkClass) {
        this.networkClass = networkClass;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public void setNetworkType(final String networkType) {
        this.networkType = networkType;
    }

    public JSONObject toJSON() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("IMEI", (Object) this.getIMEI());
            jsonObject.put("IMSI", (Object) this.getIMSI());
            jsonObject.put("phoneType", (Object) this.getPhoneType());
            jsonObject.put("phoneNumber", (Object) this.getPhoneNumber());
            jsonObject.put("operator", (Object) this.getOperator());
            jsonObject.put("sIMSerial", (Object) this.getsIMSerial());
            jsonObject.put("isSimNetworkLocked", this.isSimNetworkLocked());
            jsonObject.put("isNfcPresent", this.isNfcPresent());
            jsonObject.put("isNfcEnabled", this.isNfcEnabled());
            jsonObject.put("isWifiEnabled", this.isWifiEnabled());
            jsonObject.put("isNetworkAvailable", this.isNetworkAvailable());
            jsonObject.put("networkClass", (Object) this.getNetworkClass());
            jsonObject.put("networkType", (Object) this.getNetworkType());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
