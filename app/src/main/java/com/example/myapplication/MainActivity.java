package com.example.myapplication;

import code.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Main activity class of an Android application, which handles the navigation to other activities.
 * @authors Stephanie Lin, Hyeseo Lee
 */

public class MainActivity extends AppCompatActivity {
    private Order order;
    private ArrayList<Order> orderList;

    /**
     * Initializes the activity and retrieves the current order and order history list.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.hasExtra("order")) {
            order = (Order) intent.getSerializableExtra("order");
        } else {
            order = new Order();
        }
        if (intent.hasExtra("order list")) {
            orderList = (ArrayList<Order>) intent.getSerializableExtra("order list");
        } else {
            orderList = new ArrayList<Order>();
        }
    }

    /**
     * Handles the click event for the "donut" button and starts the "donut activity".
     * @param view The view that was clicked.
     */
    public void donutClick(View view) {
        Toast.makeText(this, "donut", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, donut_activity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    /**
     * Handles the click event for the "coffee" button and starts the "coffee activity".
     * @param view The view that was clicked.
     */
    public void coffeeClick(View view) {
        Toast.makeText(this, "coffee", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_coffee.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    /**
     * Handles the click event for the "cart" button and starts the "cart activity".
     * @param view The view that was clicked.
     */
    public void cartClick(View view) {
        Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_cart.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    /**
     * Handles the click event for the "history" button and starts the "history activity".
     * @param view The view that was clicked.
     */
    public void historyClick(View view) {
        Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_history.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

}