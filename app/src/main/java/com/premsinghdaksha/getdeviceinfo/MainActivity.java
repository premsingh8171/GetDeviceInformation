/*
Code by prem singh daksha 09/12/2020
*/
package com.premsinghdaksha.getdeviceinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.premsinghdaksha.getdeviceinfo.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(0);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addTitle("Location");
        adapter.addTitle("App");
        adapter.addTitle("Ads");
        adapter.addTitle("Battery");
        adapter.addTitle("Device");
        adapter.addTitle("Memory");
        adapter.addTitle("Network");
        adapter.addTitle("Installed Apps");
        adapter.addTitle("Contacts");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}