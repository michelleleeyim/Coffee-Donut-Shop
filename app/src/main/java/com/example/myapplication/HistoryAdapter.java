package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;

import java.util.ArrayList;

import code.Order;

/**
 * Class representing an adapter for a list of orders to be displayed in a ListView.
 * @authors Stephanie Lin, Hyeseo Lee
 */
public class HistoryAdapter extends ArrayAdapter<Order> {
    private ArrayList<Order> orderList;
    private Order order;
    private Context context;
    private int increment = 1;

    /**
     * Constructor for the adaptor.
     * @param orderList ArrayList of Order objects storing orders that have been placed.
     * @param context Current state of the application.
     */
    public HistoryAdapter(ArrayList<Order> orderList, Context context) {
        super(context, R.layout.history_row_layout, orderList);
        this.orderList = orderList;
        this.context = context;
    }

    /**
     * Method for creating a View to display a single item in the ListView.
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return View object that represents a row in the ListView that the adapter is attached to.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
