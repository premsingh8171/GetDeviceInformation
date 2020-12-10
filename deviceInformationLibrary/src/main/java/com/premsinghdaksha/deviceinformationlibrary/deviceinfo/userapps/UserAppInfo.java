/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.userapps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public class UserAppInfo
{
    private Context context;
    
    public UserAppInfo(final Context context) {
        this.context = context;
    }
    
    public List<UserApps> getInstalledApps(final boolean getSysPackages) {
        final List<UserApps> res = new ArrayList<UserApps>();
        final List<PackageInfo> packs = (List<PackageInfo>)this.context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); ++i) {
            final PackageInfo p = packs.get(i);
            final ApplicationInfo a = p.applicationInfo;
            if (getSysPackages || (a.flags & 0x1) != 0x1) {
                final UserApps newInfo = new UserApps();
                newInfo.setAppName(p.applicationInfo.loadLabel(this.context.getPackageManager()).toString());
                newInfo.setPackageName(p.packageName);
                newInfo.setVersionName(p.versionName);
                newInfo.setVersionCode(p.versionCode);
                res.add(newInfo);
            }
        }
        return res;
    }
}
