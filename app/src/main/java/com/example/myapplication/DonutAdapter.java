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
    private OnDonutClickListener onDonutClickListener;


    public DonutAdapter(ArrayList<String> itemList, Context context, OnDonutClickListener onDonutClickListener) {
        this.itemList = itemList;
        this.context = context;
        this.onDonutClickListener = onDonutClickListener;


        imageMap = new HashMap<>();
        imageMap.put("Plain Donut", R.drawable.plain);
        imageMap.put("Chocolate Donut", R.drawable.choco);
        imageMap.put("Strawberry Donut", R.drawable.strawberry);
        imageMap.put("Boston Cream Donut", R.drawable.boston);
        imageMap.put("Powdered Sugar Donut", R.drawable.yeast);
        imageMap.put("Cookie Monster Donut", R.drawable.cookie);
        imageMap.put("Glazed Donut", R.drawable.glazed);
        imageMap.put("Cherry Donut", R.drawable.cherry);
        imageMap.put("Jelly Donut", R.drawable.jelly);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_row, parent, false);
        return new MyViewHolder(view, onDonutClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = itemList.get(position);
        holder.donutFlavor.setText(name);
        holder.image.setImageResource(imageMap.get(name));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView donutFlavor;
        ImageView image;
        OnDonutClickListener onDonutClickListener; // add click listener

        public MyViewHolder(@NonNull View itemView, OnDonutClickListener onDonutClickListener) {
            super(itemView);
            donutFlavor = itemView.findViewById(R.id.donut_flavor);
            image = itemView.findViewById(R.id.donut_image);
            this.onDonutClickListener = onDonutClickListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onDonutClickListener.onDonutClick(getAdapterPosition());
        }
    }

    public interface OnDonutClickListener {
        void onDonutClick(int position);
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

}
