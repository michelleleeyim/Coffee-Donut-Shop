package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import code.Order;

/**
 * Class for Order History that allows user to cancel placed orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class activity_history extends AppCompatActivity {
    private Order order;
    private HistoryAdapter historyAdapter;
    private ArrayList<Order> orderList;
    private ListView orderHistoryView;
    private boolean placedOrder;

    /**
     * Sets up the "history" activity and adds a new order to the orderList if an order has been placed.
     * It also populates a list view with orders using a custom adapter.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        orderHistoryView = (ListView) findViewById(R.id.orderHistory);
        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        placedOrder = getIntent().getBooleanExtra("order placed", false);
        if (placedOrder == true) {
            orderList.add(order);
            order = new Order();
        }

        historyAdapter = new HistoryAdapter(orderList, this);
        orderHistoryView.setAdapter(historyAdapter);
    }

    /**
     * Returns the scene back to main menu and passes in order and order list data using intent.
     * @param view The view that was clicked.
     */
    public void orderHistoryBackClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

}
