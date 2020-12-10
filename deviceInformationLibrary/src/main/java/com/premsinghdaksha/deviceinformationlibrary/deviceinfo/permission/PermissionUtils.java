/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.content.Intent;
import android.os.Build;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class PermissionUtils
{
    private Context context;
    
    public PermissionUtils(final Context context) {
        this.context = context;
    }
    
    @SuppressLint("WrongConstant")
    public final synchronized boolean isPermissionGranted(final String permission) {
        if (Build.VERSION.SDK_INT < 23) {
            return this.context.checkCallingOrSelfPermission(permission) == 0;
        }
        final int hasPermission = ContextCompat.checkSelfPermission(this.context, permission);
        return hasPermission == 0;
    }
    
    @SuppressLint("WrongConstant")
    public void openAppSettings() {
        final Intent i = new Intent();
        i.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        i.addCategory("android.intent.category.DEFAULT");
        i.setData(Uri.parse("package:" + this.context.getPackageName()));
        i.addFlags(268435456);
        i.addFlags(1073741824);
        i.addFlags(8388608);
        this.context.startActivity(i);
    }
}
