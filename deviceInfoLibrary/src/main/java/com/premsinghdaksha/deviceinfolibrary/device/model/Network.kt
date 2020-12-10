package com.an.deviceinfo.device.model

import android.content.Context
import com.an.deviceinfo.device.DeviceInfo
import org.json.JSONObject

 class Network {

    var iMEI: String? = null
    var iMSI: String? = null
    var phoneType: String? = null
    var phoneNumber: String? = null
    var operator: String? = null
    private var sIMSerial: String? = null
    var isSimNetworkLocked = false
    var isNfcPresent = false
    var isNfcEnabled = false
    var isWifiEnabled = false
    var isNetworkAvailable = false
    var networkClass: String? = null
    var networkType: String? = null

    fun Network(context: Context?) {
        val deviceInfo = DeviceInfo(context!!)
        iMEI = deviceInfo.getIMEI()
        iMSI = deviceInfo.getIMSI()
        phoneType = deviceInfo.phoneType
        phoneNumber = deviceInfo.phoneNumber
        operator = deviceInfo.operator
        sIMSerial = deviceInfo.getSIMSerial()
        isSimNetworkLocked = deviceInfo.isSimNetworkLocked
        isNfcPresent = deviceInfo.isNfcPresent
        isNfcEnabled = deviceInfo.isNfcEnabled
        isWifiEnabled = deviceInfo.isWifiEnabled
        isNetworkAvailable = deviceInfo.isNetworkAvailable
        networkClass = deviceInfo.networkClass
        networkType = deviceInfo.networkType
    }

    fun getsIMSerial(): String? {
        return sIMSerial
    }

    fun setsIMSerial(sIMSerial: String?) {
        this.sIMSerial = sIMSerial
    }

    fun toJSON(): JSONObject? {
        return try {
            val jsonObject = JSONObject()
            jsonObject.put("IMEI", iMEI)
            jsonObject.put("IMSI", iMSI)
            jsonObject.put("phoneType", phoneType)
            jsonObject.put("phoneNumber", phoneNumber)
            jsonObject.put("operator", operator)
            jsonObject.put("sIMSerial", getsIMSerial())
            jsonObject.put("isSimNetworkLocked", isSimNetworkLocked)
            jsonObject.put("isNfcPresent", isNfcPresent)
            jsonObject.put("isNfcEnabled", isNfcEnabled)
            jsonObject.put("isWifiEnabled", isWifiEnabled)
            jsonObject.put("isNetworkAvailable", isNetworkAvailable)
            jsonObject.put("networkClass", networkClass)
            jsonObject.put("networkType", networkType)
            jsonObject
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

