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
    //private OrderViewModel orderViewModel;

    public void setOrder(Order order) {
        this.basketOrder = order;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


//        recyclerView = findViewById(R.id.shopping_cart);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
//
//        // Retrieve the current order from OrderViewModel
//        Order currentOrder = orderViewModel.getOrder().getValue();
//        Log.i("Cart Contents", currentOrder.toString());
//        if (currentOrder != null) {
//            itemList = currentOrder.OrderList();
//        } else {
//            itemList = new ArrayList<>();
//        }
//        cartAdapter = new CartAdapter(itemList, this);
//        recyclerView.setAdapter(cartAdapter);
    }
}