package com.example.windows10.newweather;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;

/**
 * Created by Windows 10 on 30/09/2017.
 */

public class CityPagerAdapter extends FragmentStatePagerAdapter {

    private List<CityFragment> cityFragments;
    private int baseId = 0;

    public CityPagerAdapter(FragmentManager fm, List<CityFragment> cities) {
        super(fm);
        this.cityFragments = cities;

    }

    @Override
    public Fragment getItem(int position) {
        return cityFragments.get(position);
    }


    @Override
    public int getCount() {
        return cityFragments.size();
    }


    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return cityFragments.get(position).getCityName();
    }

    public void add(String cityName) {
        Bundle args = new Bundle();
        args.putString(CityFragment.ARG_OBJECT, cityName);
        CityFragment cityFragment = new CityFragment();
        cityFragment.setArguments(args);
        cityFragments.add(cityFragment);
        notifyDataSetChanged();

    }

    public void remove(int currentPage, ViewPager viewPager) {
        cityFragments.remove(currentPage);
        notifyDataSetChanged();
    }

}
