/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission.PermissionUtils;

import java.util.List;
import java.util.Locale;

public class LocationInfo implements LocationListener {
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 0L;
    private static final long MIN_TIME_BW_UPDATES = 0L;
    private Context context;
    private PermissionUtils permissionUtils;

    public LocationInfo(final Context context) {
        this.context = context;
        this.permissionUtils = new PermissionUtils(context);
    }

    @SuppressLint("MissingPermission")
    private Double[] getLocationLatLong() {
        final Double[] latlong = {0.0, 0.0};
        try {
            @SuppressLint("WrongConstant") final LocationManager Locationm = (LocationManager) this.context.getSystemService("location");
            Locationm.requestLocationUpdates("gps", 0L, 0.0f, (LocationListener) this, Looper.getMainLooper());
            final Location locationGPS = Locationm.getLastKnownLocation("gps");
            if (locationGPS != null) {
                latlong[0] = locationGPS.getLatitude();
                latlong[1] = locationGPS.getLongitude();
                Locationm.removeUpdates((LocationListener) this);
                return latlong;
            }
            Locationm.requestLocationUpdates("network", 0L, 0.0f, (LocationListener) this, Looper.getMainLooper());
            final Location locationNet = Locationm.getLastKnownLocation("network");
            if (locationNet != null) {
                latlong[0] = locationNet.getLatitude();
                latlong[1] = locationNet.getLongitude();
                Locationm.removeUpdates((LocationListener) this);
                return latlong;
            }
            final Location locationPassive = Locationm.getLastKnownLocation("passive");
            if (locationPassive != null) {
                latlong[0] = locationPassive.getLatitude();
                latlong[1] = locationPassive.getLongitude();
                Locationm.removeUpdates((LocationListener) this);
                return latlong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latlong;
    }

    private DeviceLocation getAddressFromLocation(final double latitude, final double longitude) {
        final Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());
        final DeviceLocation addressInfo = new DeviceLocation();
        try {
            final List<Address> addressList = (List<Address>) geocoder.getFromLocation(latitude, longitude, 1);
            if (addressList != null && addressList.size() > 0) {
                final Address address = addressList.get(0);
                addressInfo.setAddressLine1(address.getAddressLine(0));
                addressInfo.setCity(address.getLocality());
                addressInfo.setPostalCode(address.getPostalCode());
                addressInfo.setState(address.getAdminArea());
                addressInfo.setCountryCode(address.getCountryCode());
                addressInfo.setLatitude(latitude);
                addressInfo.setLongitude(longitude);
                return addressInfo;
            }
        } catch (Exception e) {
            Log.e("", "Unable connect to Geocoder", (Throwable) e);
        } finally {
            return addressInfo;
        }
    }

    public DeviceLocation getLocation() {
        if (!this.permissionUtils.isPermissionGranted("android.permission.ACCESS_FINE_LOCATION")) {
            throw new RuntimeException("Access Fine Location permission not granted!");
        }
        final Double[] latlong = this.getLocationLatLong();
        return this.getAddressFromLocation(latlong[0], latlong[1]);
    }

    public void onLocationChanged(final Location location) {
    }

    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
    }

    public void onProviderEnabled(final String provider) {
    }

    public void onProviderDisabled(final String provider) {
    }
}
