package com.example.windows10.newweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.MyViewHolder> {
    private int numOfItems;
    private CityFragment city;


    public WeatherListAdapter(int numOfItems, CityFragment city){
        this.numOfItems = numOfItems;
        this.city = city;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int listItemID = R.layout.weather_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItemID, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);
        if (city.getWeathers()[position] != null)
        holder.description.setText(city.getWeathers()[position].getDescription());
    }


    @Override
    public int getItemCount() {
        return numOfItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView description;
        public MyViewHolder(View itemView){
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.weatherDescription);
        }
        void bind(int listIndex){
            //if (city.getWeathers()[listIndex] != null)
            //description.setText(city.getWeathers()[listIndex].getDescription());

        }
    }
}
