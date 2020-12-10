/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.getdeviceinfo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.premsinghdaksha.getdeviceinfo.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<String> titles = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void addTitle(String title) {
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);
    }


    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
