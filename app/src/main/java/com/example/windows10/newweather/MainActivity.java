package com.example.windows10.newweather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchCityDialog.CitySelectedCallback {
    CityPagerAdapter cityPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    SearchCityDialog dialog;
    List<CityFragment> cities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        SharedPreferences cityNames = getPreferences(MODE_PRIVATE);
        try {
            JSONObject jsonObject = new JSONObject(cityNames.getString("jsonCities", ""));
            JSONArray jsonCityNames = jsonObject.getJSONArray("cityNames");
            CityFragment[] cityFragments = new CityFragment[jsonCityNames.length()];
            Bundle args = new Bundle();
            for (int i = 0; i < cityFragments.length; i++) {
                args.putString(CityFragment.ARG_OBJECT, jsonCityNames.get(i).toString());
                cityFragments[i] = new CityFragment();
                cityFragments[i].setArguments(args);
                cities.add(cityFragments[i]);
                args.clear();
            }


        } catch (JSONException e) {
            Log.d("num", "json exception");
        }


        cityPagerAdapter = new CityPagerAdapter(getSupportFragmentManager(), cities);
        viewPager = (ViewPager) findViewById(R.id.city_pager);
        viewPager.setAdapter(cityPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_add) {
            if (dialog == null) {
                dialog = new SearchCityDialog(this, getFragmentManager(), this);
            }
            dialog.show();
        } else if (id == R.id.action_delete) {
            Log.d("num", viewPager.getCurrentItem() + "");
            cityPagerAdapter.remove(viewPager.getCurrentItem(), viewPager);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences cityNames = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = cityNames.edit();
        editor.clear();
        JSONObject JSONObjectCities = new JSONObject();
        JSONArray cityList = new JSONArray();
        for (int i = 0; i < cities.size(); i++) {
            cityList.put(cities.get(i).getCityName());
        }
        try {
            JSONObjectCities.put("cityNames", cityList);
        } catch (JSONException e) {
            Log.d("num", "can't save");
        }
        editor.putString("jsonCities", JSONObjectCities.toString());
        editor.commit();


    }

    @Override
    public void citySelected(String city) {
        cityPagerAdapter.add(city);
        dialog.dismiss();
    }
}
