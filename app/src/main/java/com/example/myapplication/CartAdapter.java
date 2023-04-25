package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.*;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private ArrayList<MenuItem> itemList;
    private Context context;

    public CartAdapter(ArrayList<MenuItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MenuItem item = itemList.get(position);
        if(item!=null){
        // set item name, quantity, add-ins and size (if applicable)
        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText("Quantity: " + item.getQuantity());
        if (item instanceof Coffee) {
            Coffee coffee = (Coffee) item;
            holder.itemAddins.setText("Add-ins: " + coffee.getAddIns());
            holder.itemSize.setText("Size: " + coffee.getCupSize());
        } else {
            holder.itemAddins.setText("");
            holder.itemSize.setText("");
        }
        }

        // set remove button onClickListener
        holder.removeItemButton.setOnClickListener(view -> {
            itemList.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        //return itemList.size();
        return itemList == null ? 0 : itemList.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemAddins, itemSize;
        Button removeItemButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemAddins = itemView.findViewById(R.id.item_addins);
            itemSize = itemView.findViewById(R.id.item_size);
            removeItemButton = itemView.findViewById(R.id.remove_button);
        }
    }
}

