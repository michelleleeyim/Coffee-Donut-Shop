package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.Order;

public class HistoryAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orderList;
    private Order order;
    private Context context;
    private int increment = 1;

    public HistoryAdapter(ArrayList<Order> orderList, Context context) {
        super(context, R.layout.history_row_layout, orderList);
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.history_row_layout, parent, false);

        TextView orderNumber = rowView.findViewById(R.id.orderNumber);
        TextView orderItems = rowView.findViewById(R.id.orderItems);
        TextView orderPrice = rowView.findViewById(R.id.orderHistoryPrice);
        AppCompatImageButton orderCancelButton = rowView.findViewById(R.id.orderCancelButton);

        Order order = orderList.get(position);
        if (order != null) {
            orderNumber.setText("Order #: " + Integer.toString(position + increment));
            orderItems.setText(order.toString());
            orderPrice.setText("Price: " + String.format("$%.2f", order.priceWithTax()));
        }

        orderCancelButton.setOnClickListener(view -> {
            int index = orderList.indexOf(order);
            orderList.set(index, new Order());
            orderList.get(index).setOrderNumber(index + 1);
            notifyDataSetChanged();
        });

        return rowView;
    }





//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_row_layout, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Order order = orderList.get(position);
//        if (order != null) {
//            holder.orderNumber.setText("Order #: " + Integer.toString(position + increment));
//            holder.orderItems.setText(order.toString());
//            holder.orderPrice.setText("Price: " + String.format("$%.2f", order.priceWithTax()));
//        }
//        //
//        holder.orderCancelButton.setOnClickListener(view -> {
//            int index = orderList.indexOf(order);
//            orderList.set(index, new Order());
//            orderList.get(index).setOrderNumber(index + 1);
//            notifyDataSetChanged();
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return orderList.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView orderNumber, orderItems, orderPrice;
//        AppCompatImageButton orderCancelButton;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            orderNumber = itemView.findViewById(R.id.orderNumber);
//            orderItems = itemView.findViewById(R.id.orderItems);
//            orderPrice = itemView.findViewById(R.id.orderHistoryPrice);
//            orderCancelButton = itemView.findViewById(R.id.orderCancelButton);
//        }
//    }
}
