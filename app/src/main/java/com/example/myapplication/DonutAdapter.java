package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import code.Donut;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.MyViewHolder> {

    private ArrayList<String> itemList;
    private HashMap<String, Integer> imageMap;
    private Context context;

    public DonutAdapter(ArrayList<String> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = itemList.get(position);
        holder.donutFlavor.setText(name);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView donutFlavor;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            donutFlavor = itemView.findViewById(R.id.donut_flavor);
            image = itemView.findViewById(R.id.donut_image);
        }
    }
}
