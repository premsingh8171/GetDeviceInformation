/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public final class AdvertisingIdClient {
    public static AdIdInfo getAdvertisingIdInfo(final Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            final PackageManager pm = context.getPackageManager();
            pm.getPackageInfo("com.android.vending", 0);
        } catch (Exception e) {
            throw e;
        }
        final AdvertisingConnection connection = new AdvertisingConnection();
        final Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, (ServiceConnection) connection, 1)) {
            try {
                final AdvertisingInterface adInterface = new AdvertisingInterface(connection.getBinder());
                final AdIdInfo adIdInfo = new AdIdInfo(adInterface.getId(), adInterface.isLimitAdTrackingEnabled(true));
                Log.d("*****ADINFO***", adInterface.getId());
                return adIdInfo;
            } catch (Exception exception) {
                throw exception;
            } finally {
                context.unbindService((ServiceConnection) connection);
            }
        }
        throw new IOException("Google Play connection failed");
    }

    public static final class AdIdInfo {
        private final String advertisingId;
        private final boolean limitAdTrackingEnabled;

        AdIdInfo(final String advertisingId, final boolean limitAdTrackingEnabled) {
            this.advertisingId = advertisingId;
            this.limitAdTrackingEnabled = limitAdTrackingEnabled;
        }

        public String getId() {
            return this.advertisingId;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }
    }

    private static final class AdvertisingConnection implements ServiceConnection {
        boolean retrieved;
        private final LinkedBlockingQueue<IBinder> queue;

        private AdvertisingConnection() {
            this.retrieved = false;
            this.queue = new LinkedBlockingQueue<IBinder>(1);
        }

        public void onServiceConnected(final ComponentName name, final IBinder service) {
            try {
                this.queue.put(service);
            } catch (InterruptedException ex) {
            }
        }

        public void onServiceDisconnected(final ComponentName name) {
        }

        public IBinder getBinder() throws InterruptedException {
            if (this.retrieved) {
                throw new IllegalStateException();
            }
            this.retrieved = true;
            return this.queue.take();
        }
    }

    private static final class AdvertisingInterface implements IInterface {
        private IBinder binder;

        public AdvertisingInterface(final IBinder pBinder) {
            this.binder = pBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getId() throws RemoteException {
            final Parcel data = Parcel.obtain();
            final Parcel reply = Parcel.obtain();
            String id;
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, data, reply, 0);
                reply.readException();
                id = reply.readString();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return id;
        }

        public boolean isLimitAdTrackingEnabled(final boolean paramBoolean) throws RemoteException {
            final Parcel data = Parcel.obtain();
            final Parcel reply = Parcel.obtain();
            boolean limitAdTracking;
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                data.writeInt((int) (paramBoolean ? 1 : 0));
                this.binder.transact(2, data, reply, 0);
                reply.readException();
                limitAdTracking = (0 != reply.readInt());
            } finally {
                reply.recycle();
                data.recycle();
            }
            return limitAdTracking;
        }
    }
}
