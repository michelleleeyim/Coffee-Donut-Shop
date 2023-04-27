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

/**custom RecyclerView adapter for a list of donut flavors.
 * @authors Stephanie Lin, Hyeseo Lee
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.MyViewHolder> {

    private ArrayList<String> itemList;
    private HashMap<String, Integer> imageMap;
    private Context context;
    private OnDonutClickListener onDonutClickListener;

    /**
     * Defines the constructor of the DonutAdapter class and initializes variables.
     * @param itemList Arraylist of String objects representing donut flavors.
     * @param context Current state of the application.
     * @param onDonutClickListener interface defining a method which is called when a donut is clicked.
     */
    public DonutAdapter(ArrayList<String> itemList, Context context,
                        OnDonutClickListener onDonutClickListener) {
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

    /**
     * Method for creating a new ViewHolder object for the DonutAdapter by inflating the layout.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return New instance of MyViewHolder with an OnDonutClickListener.
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donut_row, parent,
                false);
        return new MyViewHolder(view, onDonutClickListener);
    }

    /**
     * Sets the data for a specific view holder by retrieving the corresponding item from the data set.
     * Displays its name and image in the respective views.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = itemList.get(position);
        holder.donutFlavor.setText(name);
        holder.image.setImageResource(imageMap.get(name));
    }

    /**
     * Returns size of the itemList holding donut flavors.
     * @return Integer representation of number of flavors stored in itemList.
     */
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     *  defines a view holder for the RecyclerView that sets up the layout and handles click events.
     * @authors Stephanie Lin, Hyeseo Lee
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView donutFlavor;
        ImageView image;
        OnDonutClickListener onDonutClickListener;

        /**
         * Initializes the view holder and sets a click listener on its view.
         * @param itemView View for a single item in the RecyclerView.
         * @param onDonutClickListener An interface for handling click events on a donut item.
         */
        public MyViewHolder(@NonNull View itemView, OnDonutClickListener onDonutClickListener) {
            super(itemView);
            donutFlavor = itemView.findViewById(R.id.donut_flavor);
            image = itemView.findViewById(R.id.donut_image);
            this.onDonutClickListener = onDonutClickListener;
            itemView.setOnClickListener(this);
        }

        /**
         * Triggers the onDonutClick method of the OnDonutClickListener interface
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            onDonutClickListener.onDonutClick(getAdapterPosition());
        }
    }

    /**
     * Interface for a click listener that triggers a method when a donut item is clicked.
     */
    public interface OnDonutClickListener {
        /**
         * behavior when a donut item is clicked, taking the position of the clicked item as a parameter.
         * @param position Integer representation of the position of the flavor.
         */
        void onDonutClick(int position);
    }

    /**
     * Returns the ArrayList of items in the DonutAdapter.
     * @return the ArrayList of string objects, itemList, storing donut flavors.
     */
    public ArrayList<String> getItemList() {
        return itemList;
    }

}
