package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.*;

/**
 * Defines a RecyclerView adapter for displaying a list of menu items in a shopping cart.
 * Allows user to remove items, and updates total price.
 * @authors Stephanie Lin, Hyeseo Lee
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    ArrayList<MenuItem> itemList;
    private Context context;
    private Order order;

    private int increment = 1;
    private int start = 0;

    /**
     *constructor for the CartAdapter class.
     * @param itemList ArrayList storing items in order.
     * @param context  current state of the application.
     * @param order The order object storing information of items in shopping cart.
     */
    public CartAdapter(ArrayList<MenuItem> itemList, Context context, Order order) {
        this.itemList = itemList;
        this.context = context;
        this.order = order;
    }

    /**
     * Defines a method that creates a new instance of MyViewHolder.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return MyViewHolder that was created by inflating layout from specified XML file.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    /**
     * Binds the data from the MenuItem object to the corresponding views in the RecyclerView.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MenuItem item = itemList.get(position);
        if (item != null) {
            holder.itemName.setText(item.getName());
            holder.itemQuantity.setText("Quantity: " + item.getQuantity());
            holder.image.setImageResource(R.drawable.bag);

            if (item instanceof Coffee) {
                Coffee coffee = (Coffee) item;
                holder.image.setImageResource(R.drawable.coffee);
                String addInsList = coffee.getAddInString();
                holder.itemAddins.setText("Add-ins: " + addInsList.substring(start,
                        addInsList.length() - increment));
                holder.itemSize.setText("Size: " + coffee.getCupSize());
            } else {
                holder.itemAddins.setText("");
                holder.itemSize.setText("");
            }
        }

        holder.removeItemButton.setOnClickListener(view -> {
            itemList.remove(position);
            notifyDataSetChanged();
            order.remove(item);
            ((activity_cart) context).updatePrice();
        });
    }

    /**
     * Retrievces the number of items in order cart.
     * @return Integer value of number of items in order cart.
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * MyViewHolder class used to hold the views of each item in the RecyclerView.
     * @authors Stephanie Lin, Hyeseo Lee
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemAddins, itemSize;
        AppCompatImageButton removeItemButton;
        ImageView image;

        /**
         * Initializes the views of a single item's layout in the RecyclerView.
         * @param itemView root view of the item layout inflated in onCreateViewHolder method.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.donut_flavor);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            itemAddins = itemView.findViewById(R.id.item_addins);
            itemSize = itemView.findViewById(R.id.item_size);
            removeItemButton = itemView.findViewById(R.id.remove_button);
            image = itemView.findViewById(R.id.donut_image);
        }
    }
}
