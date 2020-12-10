/*
Code by prem singh daksha 09/12/2020
*/


package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.ads;

import android.content.Context;


public class AdInfo
{
    private Context context;
    
    public AdInfo(final Context context) {
        this.context = context;
    }
    
    public final void getAndroidAdId(final AdIdCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Ad ad = AdInfo.this.getAd();
                    callback.onResponse(AdInfo.this.context, ad);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    public Ad getAd() throws Exception {
        final AdvertisingIdClient.AdIdInfo adInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.context);
        final String advertisingId = adInfo.getId();
        final boolean adDoNotTrack = adInfo.isLimitAdTrackingEnabled();
        final Ad ad = new Ad();
        ad.setAdDoNotTrack(adDoNotTrack);
        ad.setAdvertisingId(advertisingId);
        return ad;
    }
    
    public interface AdIdCallback
    {
        void onResponse(final Context p0, final Ad p1);
    }
}
