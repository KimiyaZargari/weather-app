package com.example.windows10.newweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.MyViewHolder> {
    private int numOfItems;
    public WeatherListAdapter(int numOfItems){
        this.numOfItems = numOfItems;
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
    }


    @Override
    public int getItemCount() {
        return numOfItems;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView listItem;
        public MyViewHolder(View itemView){
            super(itemView);
            listItem = (TextView) itemView.findViewById(R.id.weather_item_text);
        }
        void bind(int listIndex){
            listItem.setText(String.valueOf(listIndex));

        }
    }
}
