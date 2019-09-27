package com.example.windows10.newweather;

public class singleWeather {
    private float temp;

    public float getTemp() {
        return temp;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
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

    private float temp_min;
    private float temp_max;
    private float pressure;
    private int humidity;
    private String main;
    private String description;
    private String icon;


    public singleWeather(float temp, float temp_min, float temp_max, float pressure, int humidity, String main, String description, String icon) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

}
