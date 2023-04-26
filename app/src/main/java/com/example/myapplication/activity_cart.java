package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.Order;
import code.*;

public class activity_cart extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;
    private ArrayList<MenuItem> itemList;
    private ArrayList<Order> orderList;
    private Order basketOrder;
    private Order order;
    private double tax = 0.06625;
    private TextView subTotal_order;
    private TextView tax_order;
    private TextView total_order;
    private boolean placeOrder = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemList = new ArrayList<>(); // Initialize itemList with an empty list
        recyclerView = findViewById(R.id.shopping_cart);
        subTotal_order = findViewById(R.id.subtotal_order);
        tax_order = findViewById(R.id.tax_order);
        total_order = findViewById(R.id.total_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        // Retrieve the current order from OrderViewModel
        Log.i("Cart Contents", order.toString());
        if (order != null) {
            itemList = order.OrderList();
        } else {
            itemList = new ArrayList<>();
        }
        cartAdapter = new CartAdapter(itemList, this,order);
        recyclerView.setAdapter(cartAdapter);
        updatePrice();
    }
    public void updatePrice() {
        double subTotal = order.getTotalPrice();
        double salesTax = subTotal * tax;
        double total = order.priceWithTax();

        subTotal_order.setText("Total: "+ String.format("$%.2f", subTotal));
        tax_order.setText("Tax:"+String.format("$%.2f", salesTax));
        total_order.setText("Total:"+String.format("$%.2f",total));
    }
    public void backClick(View view){
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    public void placeOrder(View view) {
        Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_history.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        intent.putExtra("order placed", placeOrder);
        //Order order = new Order();
        startActivity(intent);
    }
}