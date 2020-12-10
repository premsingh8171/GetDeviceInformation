/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.deviceinformationlibrary.deviceinfo.usercontacts;

import android.database.Cursor;
import android.net.Uri;
import java.util.Set;
import android.provider.ContactsContract;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.app.Activity;

import com.premsinghdaksha.deviceinformationlibrary.deviceinfo.permission.PermissionUtils;

public class UserContactInfo
{
    private Activity activity;
    private PermissionUtils permissionUtils;
    
    public UserContactInfo(final Activity activity) {
        this.activity = activity;
        this.permissionUtils = new PermissionUtils((Context)activity);
    }
    
    public List<UserContacts> getContacts() {
        if (!this.permissionUtils.isPermissionGranted("android.permission.BLUETOOTH")) {
            throw new RuntimeException("Access user contacts permission not granted!");
        }
        final List<UserContacts> contacts = new ArrayList<UserContacts>();
        final Set<String> uniqueValues = new HashSet<String>();
        final Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        final String[] projection = { "display_name", "data1", "data2" };
        final Cursor people = this.activity.getContentResolver().query(uri, projection, (String)null, (String[])null, (String)null);
        final int indexName = people.getColumnIndex("display_name");
        final int indexNumber = people.getColumnIndex("data1");
        final int indexPhoneType = people.getColumnIndex("data2");
        people.moveToFirst();
        do {
            if (!uniqueValues.contains(people.getString(indexNumber))) {
                final UserContacts contactsModel = new UserContacts();
                contactsModel.setDisplayName(people.getString(indexName));
                contactsModel.setMobileNumber(people.getString(indexNumber));
                contactsModel.setPhoneType(people.getString(indexPhoneType));
                uniqueValues.add(people.getString(indexNumber));
                contacts.add(contactsModel);
            }
        } while (people.moveToNext());
        return contacts;
    }
}
