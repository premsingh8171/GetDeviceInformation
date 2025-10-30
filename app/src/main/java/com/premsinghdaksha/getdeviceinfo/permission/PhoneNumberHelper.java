package com.premsinghdaksha.getdeviceinfo.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PhoneNumberHelper {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private Activity activity;

    public PhoneNumberHelper(Activity activity) {
        this.activity = activity;
    }

    // Check and request permissions
    public void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean readPhoneStatePermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
            boolean readPhoneNumbersPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED;

            if (!readPhoneStatePermission || !readPhoneNumbersPermission) {
                ActivityCompat.requestPermissions(activity,
                        new String[] { Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS },
                        PERMISSION_REQUEST_CODE);
            } else {
                // Permissions already granted
                getPhoneNumberSafe();
            }
        } else {
            // Permissions automatically granted on older versions
            getPhoneNumberSafe();
        }
    }

    // Handle permission request response in your activity
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                getPhoneNumberSafe();
            } else {
                // Permission denied: handle gracefully
                // e.g., show a message or disable phone number features
            }
        }
    }

    // Safely get the phone number
    private void getPhoneNumberSafe() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                String phoneNumber = telephonyManager.getLine1Number();
                // Use the phone number as needed
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            // Handle the exception gracefully
        }
    }
}

