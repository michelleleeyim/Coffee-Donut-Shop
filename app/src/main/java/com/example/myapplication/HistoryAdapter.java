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
}
