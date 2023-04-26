package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import code.*;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    ArrayList<MenuItem> itemList = new ArrayList<>();
    private Context context;
    private Order order;


    public CartAdapter(ArrayList<MenuItem> itemList, Context context, Order order) {
        this.itemList = itemList;
        this.context = context;
        this.order = order;
    }
//hi
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
            // set item name and quantity
            holder.itemName.setText(item.getName());
            holder.itemQuantity.setText("Quantity: " + item.getQuantity());
            holder.image.setImageResource(R.drawable.donut);

            if (item instanceof Coffee) {
                Coffee coffee = (Coffee) item;
                holder.image.setImageResource(R.drawable.coffee);
                // set add-ins
                //String[] addInsArray = coffee.getAddIns();
                String addInsList = coffee.getAddInString();
                holder.itemAddins.setText("Add-ins: " + addInsList.substring(0, addInsList.length() - 1));

                // set size
                holder.itemSize.setText("Size: " + coffee.getCupSize());
            }
            else {
                holder.itemAddins.setText("");
                holder.itemSize.setText("");
            }
        }

        // set remove button onClickListener
        holder.removeItemButton.setOnClickListener(view -> {
            itemList.remove(position);
            notifyDataSetChanged();
            order.remove(item);
            ((activity_cart)context).updatePrice();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemAddins, itemSize;
        AppCompatImageButton removeItemButton;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemAddins = itemView.findViewById(R.id.item_addins);
            itemSize = itemView.findViewById(R.id.item_size);
            removeItemButton = itemView.findViewById(R.id.remove_button);
            image = itemView.findViewById(R.id.image);
        }
    }
}
