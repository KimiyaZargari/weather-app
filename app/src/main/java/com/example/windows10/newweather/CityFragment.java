package com.example.windows10.newweather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.PublicKey;

/**
 * Created by Windows 10 on 30/09/2017.
 */

public class CityFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private String cityName;
    private  RecyclerView list;
    private WeatherListAdapter listAdapter;





    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        cityName = args.getString(ARG_OBJECT);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.city_fragment, container, false);
        list = rootView.findViewById(R.id.weather_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        list.setLayoutManager(layoutManager);
        listAdapter = new WeatherListAdapter(20);
        list.setAdapter(listAdapter);

        return rootView;
    }

    public String getCityName() {
        return cityName;

    }
}
