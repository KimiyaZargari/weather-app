package com.example.windows10.newweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Windows 10 on 30/09/2017.
 */

public class CityFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private String cityName;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        cityName = args.getString(ARG_OBJECT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.city_fragment, container, false);

        ((TextView) rootView.findViewById(R.id.textView2)).setText(cityName);
        return rootView;
    }

    public String getCityName() {
        return cityName;

    }
}
