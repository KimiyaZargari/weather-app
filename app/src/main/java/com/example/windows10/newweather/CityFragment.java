package com.example.windows10.newweather;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private WeatherDescription weathers[];

    public WeatherDescription[] getWeathers() {
        return weathers;
    }





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
        listAdapter = new WeatherListAdapter(15, this);
        list.setAdapter(listAdapter);
        weathers = new WeatherDescription[15];
        try {
            Uri builtUri = Uri.parse(baseURL).buildUpon().appendQueryParameter("q", cityName).appendQueryParameter("APPID", APIKey).appendQueryParameter("mode", "json").build();
            url= new URL(builtUri.toString());
            new WeatherQueryTask().execute(url);

        }catch(MalformedURLException e) {

            Log.d("num", e.getMessage());
        }
        return rootView;
    }
    public class WeatherQueryTask extends AsyncTask <URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            Log.d("num", url.toString());
            String result = null;
            JSONObject jsonObject = null;
            try {
                result = NetworkUtils.getResponseFromHttpURL(url);
                jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                for (int i = 0; i < 15; i++){
                     jsonObject =  (JSONObject) jsonArray.get(i);
                     JSONObject main = jsonObject.getJSONObject("main");
                     JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
                     weathers[i] = new WeatherDescription(main.getLong("temp"), main.getLong("temp_min"), main.getLong("temp_max"), main.getLong("pressure"), main.getInt("humidity"), weather.getString("main"), weather.getString("description"), weather.getString("icon"));

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                Log.d("num", e.getMessage());
            }


        return jsonObject.toString();
        }

        @Override
        protected void onPostExecute(String s) {
           Log.d("num", weathers[0].getMain());
            listAdapter.notifyDataSetChanged();

        }
    }

    public String getCityName() {
        return cityName;

    }
}
