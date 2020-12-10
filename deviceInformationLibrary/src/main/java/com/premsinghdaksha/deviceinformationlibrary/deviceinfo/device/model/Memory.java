/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model;

import org.json.JSONObject;
import android.content.Context;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.DeviceInfo;

public class Memory {

    private boolean hasExternalSDCard;
    private long totalRAM;
    private long availableInternalMemorySize;
    private long totalInternalMemorySize;
    private long availableExternalMemorySize;
    private long totalExternalMemorySize;

    public Memory(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo(context);
        this.hasExternalSDCard = deviceInfo.hasExternalSDCard();
        this.totalRAM = deviceInfo.getTotalRAM();
        this.availableInternalMemorySize = deviceInfo.getAvailableInternalMemorySize();
        this.totalInternalMemorySize = deviceInfo.getTotalInternalMemorySize();
        this.availableExternalMemorySize = deviceInfo.getAvailableExternalMemorySize();
        this.totalExternalMemorySize = deviceInfo.getTotalExternalMemorySize();
    }

    public boolean isHasExternalSDCard() {
        return hasExternalSDCard;
    }

    public void setHasExternalSDCard(boolean hasExternalSDCard) {
        this.hasExternalSDCard = hasExternalSDCard;
    }

    public long getTotalRAM() {
        return totalRAM;
    }

    public void setTotalRAM(long totalRAM) {
        this.totalRAM = totalRAM;
    }

    public long getAvailableInternalMemorySize() {
        return availableInternalMemorySize;
    }

    public void setAvailableInternalMemorySize(long availableInternalMemorySize) {
        this.availableInternalMemorySize = availableInternalMemorySize;
    }

    public long getTotalInternalMemorySize() {
        return totalInternalMemorySize;
    }

    public void setTotalInternalMemorySize(long totalInternalMemorySize) {
        this.totalInternalMemorySize = totalInternalMemorySize;
    }

    public long getAvailableExternalMemorySize() {
        return availableExternalMemorySize;
    }

    public void setAvailableExternalMemorySize(long availableExternalMemorySize) {
        this.availableExternalMemorySize = availableExternalMemorySize;
    }

    public long getTotalExternalMemorySize() {
        return totalExternalMemorySize;
    }

    public void setTotalExternalMemorySize(long totalExternalMemorySize) {
        this.totalExternalMemorySize = totalExternalMemorySize;
    }

    public JSONObject toJSON() {
        try {
            JSONObject jsonObject= new JSONObject();
            jsonObject.put("hasExternalSDCard", isHasExternalSDCard());
            jsonObject.put("totalRAM", getTotalRAM());
            jsonObject.put("availableInternalMemorySize", getAvailableInternalMemorySize());
            jsonObject.put("totalInternalMemorySize", getTotalInternalMemorySize());
            jsonObject.put("availableExternalMemorySize", getAvailableExternalMemorySize());
            jsonObject.put("totalExternalMemorySize", getTotalExternalMemorySize());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
