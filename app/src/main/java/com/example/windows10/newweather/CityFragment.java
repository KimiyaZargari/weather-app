package com.example.windows10.newweather;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.PublicKey;
import java.util.Scanner;

/**
 * Created by Windows 10 on 30/09/2017.
 */

public class CityFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    private static final String APIKey = "cb3a32584926e7a062f3cc61f024e84d";
    private String cityName;
    public  static final String  baseURL = "https://api.openweathermap.org/data/2.5/forecast";
    private  RecyclerView list;
    private URL url = null;
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
        try {
            Uri builtUri = Uri.parse(baseURL).buildUpon().appendQueryParameter("q", cityName).appendQueryParameter("APPID", APIKey).appendQueryParameter("mode", "json").build();
            url= new URL(builtUri.toString());
            new WeatherQueryTast().execute(url);

        }catch(MalformedURLException e) {

            Log.d("num", e.getMessage());
        }
        return rootView;
    }
    public class WeatherQueryTast extends AsyncTask <URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            Log.d("num", url.toString());
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        return result;
        }

        @Override
        protected void onPostExecute(String s) {
           Log.d("num", s);
        }
    }

    public String getCityName() {
        return cityName;

    }
}
