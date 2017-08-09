package com.example.rongcloud.rcdemo.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by rongcloud on 2017/7/24.
 */

public class HomeVpAdapter extends FragmentPagerAdapter {

    private final String[] titles = {"消息", "联系人", "我"};
    private List<Fragment> fragments;

    public HomeVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
