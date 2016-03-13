package com.example.operatingsystemexperiment.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.operatingsystemexperiment.ui.base.AbstractShowFragment;

import java.util.List;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<AbstractShowFragment> fragments;

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    public ViewPagerAdapter(FragmentManager fm, List<AbstractShowFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }



    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
