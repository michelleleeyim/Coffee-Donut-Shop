package com.example.myapplication;
import code.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.activity_cart;
import com.example.myapplication.activity_coffee;
import com.example.myapplication.activity_history;
import com.example.myapplication.donut_activity;

import java.util.ArrayList;

/**
 * hey
 */

public class MainActivity extends AppCompatActivity {
    private ImageButton donutButton;
    private ImageButton coffeeButton;
    private ImageButton cartButton;
    private ImageButton historyButton;
    private Order order;
    private ArrayList<Order> orderList  = new ArrayList<>();
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order = new Order();
    }

    public void donutClick(View view){
        Toast.makeText(this, "donut", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, donut_activity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
    public void coffeeClick(View view){
        Toast.makeText(this, "coffee", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_coffee.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
    public void cartClick(View view){
        Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_cart.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
    public void historyClick(View view){
        Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_history.class);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //order = (Order) getIntent().getSerializableExtra("order");
        Log.d("MainActivity", "onResume() called");
        Log.d("Cart Content", order.toString());
    }

}