package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.Order;

public class activity_history extends AppCompatActivity {
    private Order order;
    private HistoryAdapter historyAdapter;
    private ArrayList<Order> orderList;
    private RecyclerView historyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        historyRecyclerView = findViewById(R.id.orderHistory);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        // Retrieve the current order from OrderViewModel
        Log.i("Cart Contents", order.toString());

        historyAdapter = new HistoryAdapter(orderList, this);
        historyRecyclerView.setAdapter(historyAdapter);
    }
    private void orderHistoryBackClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
}
