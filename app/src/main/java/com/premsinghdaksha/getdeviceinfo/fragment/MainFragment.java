/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.getdeviceinfo.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.ads.Ad;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.ads.AdInfo;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model.App;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model.Battery;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model.Device;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model.Memory;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.device.model.Network;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.location.DeviceLocation;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.location.LocationInfo;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission.PermissionManager;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission.PermissionUtils;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.userapps.UserAppInfo;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.userapps.UserApps;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.usercontacts.UserContactInfo;
import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.usercontacts.UserContacts;
import com.premsinghdaksha.getdeviceinfo.R;
import com.premsinghdaksha.getdeviceinfo.adapter.CustomListAdapter;

import java.util.List;

public class MainFragment extends Fragment implements AdInfo.AdIdCallback, PermissionManager.PermissionCallback {

    private int position;
    private Activity mActivity;

    private RecyclerView recyclerView;
    private CustomListAdapter adapter;

    private PermissionManager permissionManager;
    private PermissionUtils permissionUtils;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static Fragment newInstance(int pos) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("pos");
        }
        permissionManager = new PermissionManager(getActivity());
        permissionUtils = new PermissionUtils(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.smoothScrollToPosition(0);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        askPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void askPermission() {
        String permission = null;
        switch (position) {
            case 0:
                permission = Manifest.permission.ACCESS_FINE_LOCATION;
                break;
            case 6:
                permission = Manifest.permission.READ_PHONE_STATE;
                break;
            case 8:
                permission = Manifest.permission.READ_CONTACTS;
                break;
        }
        if (permission != null) getPermission(permission);
        else initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermission(String permission) {
        PermissionUtils permissionUtils = new PermissionUtils(mActivity);
        if (!permissionUtils.isPermissionGranted(permission)) {
            permissionManager.showPermissionDialog(permission)
                    .withCallback(this)
                    .withDenyDialogEnabled(true)
                    .withDenyDialogMsg(mActivity.getString(R.string.permission_location))
                    .build();
        } else initialize();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initialize() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                switch (position) {
                    case 0: //call location
                        LocationInfo locationInfo = new LocationInfo(mActivity);
                        DeviceLocation location = locationInfo.getLocation();
                        adapter = new CustomListAdapter(mActivity, location);
                        break;
                    case 1: //call apps
                        App app = new App(mActivity);
                        adapter = new CustomListAdapter(mActivity, app);
                        break;
                    case 2: //call ads
                        AdInfo adInfo = new AdInfo(mActivity);
                        adInfo.getAndroidAdId(MainFragment.this);
                        break;
                    case 3: //call battery
                        Battery battery = new Battery(mActivity);
                        adapter = new CustomListAdapter(mActivity, battery);
                        break;
                    case 4: //call device
                        Device device = new Device(mActivity);
                        adapter = new CustomListAdapter(mActivity, device);
                        break;
                    case 5: //call memory
                        Memory memory = new Memory(mActivity);
                        adapter = new CustomListAdapter(mActivity, memory);
                        break;
                    case 6: //call network
                        Network network = new Network(mActivity);
                        //Log.d("Network Info", network.toString());
                        adapter = new CustomListAdapter(mActivity, network);
                        break;
                    case 7: //call installed apps
                        UserAppInfo userAppInfo = new UserAppInfo(mActivity);
                        List<UserApps> userApps = userAppInfo.getInstalledApps(true);
                        adapter = new CustomListAdapter(mActivity, userApps);
                        break;
                    case 8: //call contacts
                        UserContactInfo userContactInfo = new UserContactInfo(mActivity);
                        List<UserContacts> userContacts = userContactInfo.getContacts();
                        adapter = new CustomListAdapter(mActivity, userContacts);
                        break;
                }

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (adapter != null) {
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.handleResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(String[] permissions, int[] grantResults) {
        initialize();
    }

    @Override
    public void onPermissionDismissed(String permission) {

    }

    @Override
    public void onPositiveButtonClicked(DialogInterface dialog, int which) {
        /**
         * You can choose to open the
         * app settings screen
         * * */
        permissionUtils.openAppSettings();
        Toast.makeText(mActivity, "User has opened the settings screen", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNegativeButtonClicked(DialogInterface dialog, int which) {
        /**
         * The user has denied the permission!
         * You need to handle this in your code
         * * */
        Toast.makeText(mActivity, "User has denied the permissions", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Context context, final Ad ad) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new CustomListAdapter(mActivity, ad);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
