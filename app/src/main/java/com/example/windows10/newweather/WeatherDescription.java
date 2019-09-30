package com.example.windows10.newweather;

import android.graphics.Bitmap;

import java.sql.Time;
import java.util.Date;

public class WeatherDescription {
    private int temp;

    private long dt;

    public int getTemp() {
        return temp;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    private int temp_min;
    private int temp_max;
    private float pressure;
    private int humidity;
    private String main;
    private String description;
    private String icon;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

    public String getTime() {
        return time;
    }

    private String  time;

    public String getDay() {
        return day;
    }

    private String day;


    public WeatherDescription(long dt, float temp, float temp_min, float temp_max, float pressure, int humidity, String main, String description, String icon) {
        this.temp = (int)temp;
        this.temp_min = (int)temp_min;
        this.temp_max = (int)temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.dt = dt;
        day = new Date(dt * 1000L).toString().substring(0, 3);
        time = new Time(dt * 1000L).toString().substring(0,5);
    }

}
