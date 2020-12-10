/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model;

import org.json.JSONObject;
import android.content.Context;

import androidx.annotation.RequiresApi;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.DeviceInfo;

public class Device
{
    private String releaseBuildVersion;
    private String buildVersionCodeName;
    private String manufacturer;
    private String model;
    private String product;
    private String fingerprint;
    private String hardware;
    private String radioVersion;
    private String device;
    private String board;
    private String displayVersion;
    private String buildBrand;
    private String buildHost;
    private long buildTime;
    private String buildUser;
    private String serial;
    private String osVersion;
    private String language;
    private int sdkVersion;
    private String screenDensity;
    private int screenHeight;
    private int screenWidth;
    
    @RequiresApi(api = 14)
    public Device(final Context context) {
        final DeviceInfo deviceInfo = new DeviceInfo(context);
        this.releaseBuildVersion = deviceInfo.getReleaseBuildVersion();
        this.buildVersionCodeName = deviceInfo.getBuildVersionCodeName();
        this.manufacturer = deviceInfo.getManufacturer();
        this.model = deviceInfo.getModel();
        this.product = deviceInfo.getProduct();
        this.fingerprint = deviceInfo.getFingerprint();
        this.hardware = deviceInfo.getHardware();
        this.radioVersion = deviceInfo.getRadioVer();
        this.device = deviceInfo.getDevice();
        this.board = deviceInfo.getBoard();
        this.displayVersion = deviceInfo.getDisplayVersion();
        this.buildBrand = deviceInfo.getBuildBrand();
        this.buildHost = deviceInfo.getBuildHost();
        this.buildTime = deviceInfo.getBuildTime();
        this.buildUser = deviceInfo.getBuildUser();
        this.serial = deviceInfo.getSerial();
        this.osVersion = deviceInfo.getOSVersion();
        this.language = deviceInfo.getLanguage();
        this.sdkVersion = deviceInfo.getSdkVersion();
        this.screenDensity = deviceInfo.getScreenDensity();
        this.screenHeight = deviceInfo.getScreenHeight();
        this.screenWidth = deviceInfo.getScreenWidth();
    }
    
    public String getReleaseBuildVersion() {
        return this.releaseBuildVersion;
    }
    
    public void setReleaseBuildVersion(final String releaseBuildVersion) {
        this.releaseBuildVersion = releaseBuildVersion;
    }
    
    public String getBuildVersionCodeName() {
        return this.buildVersionCodeName;
    }
    
    public void setBuildVersionCodeName(final String buildVersionCodeName) {
        this.buildVersionCodeName = buildVersionCodeName;
    }
    
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }
    
    public String getModel() {
        return this.model;
    }
    
    public void setModel(final String model) {
        this.model = model;
    }
    
    public String getProduct() {
        return this.product;
    }
    
    public void setProduct(final String product) {
        this.product = product;
    }
    
    public String getFingerprint() {
        return this.fingerprint;
    }
    
    public void setFingerprint(final String fingerprint) {
        this.fingerprint = fingerprint;
    }
    
    public String getHardware() {
        return this.hardware;
    }
    
    public void setHardware(final String hardware) {
        this.hardware = hardware;
    }
    
    public String getRadioVersion() {
        return this.radioVersion;
    }
    
    public void setRadioVersion(final String radioVersion) {
        this.radioVersion = radioVersion;
    }
    
    public String getDevice() {
        return this.device;
    }
    
    public void setDevice(final String device) {
        this.device = device;
    }
    
    public String getBoard() {
        return this.board;
    }
    
    public void setBoard(final String board) {
        this.board = board;
    }
    
    public String getDisplayVersion() {
        return this.displayVersion;
    }
    
    public void setDisplayVersion(final String displayVersion) {
        this.displayVersion = displayVersion;
    }
    
    public String getBuildBrand() {
        return this.buildBrand;
    }
    
    public void setBuildBrand(final String buildBrand) {
        this.buildBrand = buildBrand;
    }
    
    public String getBuildHost() {
        return this.buildHost;
    }
    
    public void setBuildHost(final String buildHost) {
        this.buildHost = buildHost;
    }
    
    public long getBuildTime() {
        return this.buildTime;
    }
    
    public void setBuildTime(final long buildTime) {
        this.buildTime = buildTime;
    }
    
    public String getBuildUser() {
        return this.buildUser;
    }
    
    public void setBuildUser(final String buildUser) {
        this.buildUser = buildUser;
    }
    
    public String getSerial() {
        return this.serial;
    }
    
    public void setSerial(final String serial) {
        this.serial = serial;
    }
    
    public String getOsVersion() {
        return this.osVersion;
    }
    
    public void setOsVersion(final String osVersion) {
        this.osVersion = osVersion;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public void setLanguage(final String language) {
        this.language = language;
    }
    
    public int getSdkVersion() {
        return this.sdkVersion;
    }
    
    public void setSdkVersion(final int sdkVersion) {
        this.sdkVersion = sdkVersion;
    }
    
    public String getScreenDensity() {
        return this.screenDensity;
    }
    
    public void setScreenDensity(final String screenDensity) {
        this.screenDensity = screenDensity;
    }
    
    public int getScreenHeight() {
        return this.screenHeight;
    }
    
    public void setScreenHeight(final int screenHeight) {
        this.screenHeight = screenHeight;
    }
    
    public int getScreenWidth() {
        return this.screenWidth;
    }
    
    public void setScreenWidth(final int screenWidth) {
        this.screenWidth = screenWidth;
    }
    
    public JSONObject toJSON() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("releaseBuildVersion", (Object)this.getReleaseBuildVersion());
            jsonObject.put("buildVersionCodeName", (Object)this.getBuildVersionCodeName());
            jsonObject.put("manufacturer", (Object)this.getManufacturer());
            jsonObject.put("model", (Object)this.getModel());
            jsonObject.put("product", (Object)this.getProduct());
            jsonObject.put("fingerprint", (Object)this.getFingerprint());
            jsonObject.put("hardware", (Object)this.getHardware());
            jsonObject.put("radioVersion", (Object)this.getRadioVersion());
            jsonObject.put("device", (Object)this.getDevice());
            jsonObject.put("board", (Object)this.getBoard());
            jsonObject.put("displayVersion", (Object)this.getDisplayVersion());
            jsonObject.put("buildBrand", (Object)this.getBuildBrand());
            jsonObject.put("buildHost", (Object)this.getBuildHost());
            jsonObject.put("buildTime", this.getBuildTime());
            jsonObject.put("buildUser", (Object)this.getBuildUser());
            jsonObject.put("serial", (Object)this.getSerial());
            jsonObject.put("osVersion", (Object)this.getOsVersion());
            jsonObject.put("language", (Object)this.getLanguage());
            jsonObject.put("sdkVersion", this.getSdkVersion());
            jsonObject.put("screenDensity", (Object)this.getScreenDensity());
            jsonObject.put("screenHeight", this.getScreenHeight());
            jsonObject.put("screenWidth", this.getScreenWidth());
            return jsonObject;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
