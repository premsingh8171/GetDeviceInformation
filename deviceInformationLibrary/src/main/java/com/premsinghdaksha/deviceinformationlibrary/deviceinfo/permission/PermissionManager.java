/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission;

import android.app.Fragment;
import android.os.Build;
import android.util.Log;
import android.content.Context;
import android.app.Activity;
import android.content.DialogInterface;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class PermissionManager implements DialogInterface.OnClickListener
{
    private static boolean hasStarted;
    private final int MY_PERMISSIONS = 1012;
    private final String PERMISSIONS_TAG = "Permissions";
    private final String DEFAULT_DENY_DIALOG_TITLE = "Permission Required";
    private final String DEFAULT_DENY_DIALOG_POS_BTN = "GO TO SETTINGS";
    private final String DEFAULT_DENY_DIALOG_NEG_BTN = "CANCEL";
    private Activity activity;
    private Fragment fragment;
    private PermissionUtils permissionUtils;
    private PermissionCallback permissionCallback;
    private String permission;
    private boolean showDenyDialog;
    private boolean showRationale;
    private String denyDialogText;
    private String denyDialogTitle;
    private String denyPosBtnTxt;
    private String denyNegBtnTxt;
    private boolean showNegBtn;
    private boolean isCancellable;
    private AlertDialog alertDialog;
    
    public PermissionManager(final Fragment fragment) {
        this.showDenyDialog = true;
        this.showRationale = true;
        this.denyDialogTitle = "Permission Required";
        this.denyPosBtnTxt = "GO TO SETTINGS";
        this.denyNegBtnTxt = "CANCEL";
        this.showNegBtn = true;
        this.isCancellable = true;
        this.fragment = fragment;
        this.activity = (Activity)fragment.getActivity();
        this.permissionUtils = new PermissionUtils((Context)this.activity);
    }
    
    public PermissionManager(final Activity activity) {
        this.showDenyDialog = true;
        this.showRationale = true;
        this.denyDialogTitle = "Permission Required";
        this.denyPosBtnTxt = "GO TO SETTINGS";
        this.denyNegBtnTxt = "CANCEL";
        this.showNegBtn = true;
        this.isCancellable = true;
        this.activity = activity;
        this.permissionUtils = new PermissionUtils((Context)activity);
    }
    
    public PermissionManager showPermissionDialog(final String permission) {
        this.permission = permission;
        return this;
    }
    
    public PermissionManager withDenyDialogEnabled(final boolean showDenyDialog) {
        this.showDenyDialog = showDenyDialog;
        return this;
    }
    
    public PermissionManager withRationaleEnabled(final boolean showRationale) {
        this.showRationale = showRationale;
        return this;
    }
    
    public PermissionManager withDenyDialogMsg(final String denyDialogText) {
        this.denyDialogText = denyDialogText;
        return this;
    }
    
    public PermissionManager withDenyDialogTitle(final String denyDialogTitle) {
        this.denyDialogTitle = denyDialogTitle;
        return this;
    }
    
    public PermissionManager withDenyDialogPosBtnText(final String denyPosBtnTxt) {
        this.denyPosBtnTxt = denyPosBtnTxt;
        return this;
    }
    
    public PermissionManager withDenyDialogNegBtnText(final String denyNegBtnTxt) {
        this.denyNegBtnTxt = denyNegBtnTxt;
        return this;
    }
    
    public PermissionManager withDenyDialogNegBtn(final boolean showNegBtn) {
        this.showNegBtn = showNegBtn;
        return this;
    }
    
    public PermissionManager isDialogCancellable(final boolean isCancellable) {
        this.isCancellable = isCancellable;
        return this;
    }
    
    public PermissionManager withCallback(final PermissionCallback permissionCallback) {
        this.permissionCallback = permissionCallback;
        return this;
    }
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    public synchronized void build() {
        if (this.permission == null) {
            throw new RuntimeException("You need to set a permission before calling Build method!");
        }
        if (this.permissionCallback == null) {
            throw new RuntimeException("You need to set a permissionCallback before calling Build method!");
        }
        if (this.showDenyDialog && this.denyDialogText == null) {
            throw new RuntimeException("You need to set a deny Dialog description message before calling Build method!");
        }
        if (this.permissionUtils.isPermissionGranted(this.permission)) {
            Log.d("Permissions", String.format("%s permission already granted!", this.permission));
            return;
        }
        this.askPermissionDialog();
    }
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    private synchronized void askPermissionDialog() {
        if (PermissionManager.hasStarted) {
            return;
        }
        PermissionManager.hasStarted = true;
        if (this.fragment != null) {
            this.fragment.requestPermissions(new String[] { this.permission }, 1012);
            return;
        }
        ActivityCompat.requestPermissions(this.activity, new String[] { this.permission }, 1012);
    }
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    public synchronized void handleResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        if (permissions.length == 0) {
            return;
        }
        final String permission = permissions[0];
        switch (requestCode) {
            case 1012: {
                if (grantResults.length > 0 && grantResults[0] == 0) {
                    PermissionManager.hasStarted = false;
                    if (this.permissionCallback != null) {
                        this.permissionCallback.onPermissionGranted(permissions, grantResults);
                        break;
                    }
                    break;
                }
                else {
                    if (this.activity.shouldShowRequestPermissionRationale(permission) && this.showRationale) {
                        PermissionManager.hasStarted = false;
                        this.askPermissionDialog();
                        break;
                    }
                    if (this.showDenyDialog) {
                        this.displayDenyDialog();
                        break;
                    }
                    if (this.permissionCallback != null) {
                        this.permissionCallback.onPermissionDismissed(permission);
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private synchronized void displayDenyDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder((Context)this.activity).setTitle((CharSequence)this.denyDialogTitle).setMessage((CharSequence)this.denyDialogText).setCancelable(this.isCancellable).setPositiveButton((CharSequence)this.denyPosBtnTxt, (DialogInterface.OnClickListener)this);
        if (this.showNegBtn) {
            alertDialogBuilder.setNegativeButton((CharSequence)this.denyNegBtnTxt, (DialogInterface.OnClickListener)this);
        }
        this.alertDialog = alertDialogBuilder.show();
    }
    
    public void onClick(final DialogInterface dialog, final int which) {
        PermissionManager.hasStarted = false;
        if (this.alertDialog != null && this.alertDialog.isShowing()) {
            this.alertDialog.dismiss();
        }
        if (which == -1) {
            this.permissionCallback.onPositiveButtonClicked(dialog, which);
        }
        else if (which == -2) {
            this.permissionCallback.onNegativeButtonClicked(dialog, which);
        }
    }
    
    static {
        PermissionManager.hasStarted = false;
    }
    
    public interface PermissionCallback
    {
        void onPermissionGranted(final String[] p0, final int[] p1);
        
        void onPermissionDismissed(final String p0);
        
        void onPositiveButtonClicked(final DialogInterface p0, final int p1);
        
        void onNegativeButtonClicked(final DialogInterface p0, final int p1);
    }
}
