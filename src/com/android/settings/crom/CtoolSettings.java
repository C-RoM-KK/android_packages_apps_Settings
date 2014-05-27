package com.android.settings.crom;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.internal.util.slim.DeviceUtils;

import com.android.settings.crom.CRomSettings;
import com.android.settings.crom.InterfaceSettings
import com.android.settings.crom.LockscreenSettings;
import com.android.settings.crom.NavigationSettings;
import com.android.settings.crom.NotificationDrawer;
import com.android.settings.crom.PieControl;
import com.android.settings.crom.StatusBarSettings;
import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.ArrayList;
import java.util.List;

public class CtoolSettings extends SettingsPreferenceFragment implements ActionBar.TabListener {

    ViewPager mViewPager;
    String titleString[];
    ViewGroup mContainer;

    static Bundle mSavedState;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContainer = container;
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.setIcon(R.drawable.ic_settings_system);
   
        View view = inflater.inflate(R.layout.ctool_settings, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        StatusBarAdapter StatusBarAdapter = new StatusBarAdapter(getFragmentManager());
        mViewPager.setAdapter(StatusBarAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        ActionBar.Tab cromTab = actionBar.newTab();
        cromTab.setText("C-Rom");
        cromTab.setTabListener(this);

        ActionBar.Tab interfaceTab = actionBar.newTab();
        interfaceTab.setText("Interface");
        interfaceTab.setTabListener(this);

        ActionBar.Tab statusbarTab = actionBar.newTab();
        statusbarTab.setText("System");
        statusbarTab.setTabListener(this);

        ActionBar.Tab lockscreenTab = actionBar.newTab();
        lockscreenTab.setText("LockScreen");
        lockscreenTab.setTabListener(this);

        ActionBar.Tab notifdrawerTab = actionBar.newTab();
        notifdrawerTab.setText("Notification Drawer");
        notifdrawer.setTabListener(this);

        ActionBar.Tab piecontrolTab = actionBar.newTab();
        piecontrolTab.setText("PIE");
        piecontrolTab.setTabListener(this);

        ActionBar.Tab navigationTab = actionBar.newTab();
        navigationTab.setText("Navigation");
        navigationTab.setTabListener(this);

        actionBar.addTab(cromTab);
        actionBar.addTab(interfaceTab);
        actionBar.addTab(statusbarTab);
        actionBar.addTab(lockscreenTab);
        actionBar.addTab(notifdrawerTab);
        actionBar.addTab(piecontrolTab);
        actionBar.addTab(navigationTab);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        return view;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // After confirming PreferenceScreen is available, we call super.
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle saveState) {
        super.onSaveInstanceState(saveState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!DeviceUtils.isTablet(getActivity())) {
            mContainer.setPadding(0, 0, 0, 0);
        }
    }

    class StatusBarAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public StatusBarAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new CRomSettings();
            frags[1] = new InterfaceSettings();
            frags[2] = new StatusBarSettings();
            frags[3] = new LockscreenSettings();
            frags[4] = new NotificationDrawer();
            frags[5] = new PieControl();
            frags[6] = new NavigationSettings();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        String titleString[];
        titleString = new String[]{
                    getString(R.string.crom_settings_title),
                    getString(R.string.interface_settings_title),
                    getString(R.string.statusbar_settings_title),
                    getString(R.string.lockscreen_settings_title)
                    getString(R.string.notification_drawer_title),
                    getString(R.string.pie_control_title),
                    getString(R.string.navigation_settings_title)};
        return titleString;
    }
}
