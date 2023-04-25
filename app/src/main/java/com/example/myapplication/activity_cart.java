package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.Order;
import code.OrderSingle;
import code.*;

public class activity_cart extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;
    private ArrayList<MenuItem> itemList;
    private ArrayList<Order> placedOrderList;
    private Order basketOrder;
    private Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        recyclerView = findViewById(R.id.shopping_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        order = (Order) getIntent().getSerializableExtra("order");
        // Retrieve the current order from OrderViewModel
        Log.i("Cart Contents", order.toString());
        if (order != null) {
            itemList = order.OrderList();
        } else {
            itemList = new ArrayList<>();
        }
        cartAdapter = new CartAdapter(itemList, this);
        recyclerView.setAdapter(cartAdapter);
    }
    //hi
}