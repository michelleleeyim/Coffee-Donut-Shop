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
//import com.example.myapplication.activity_history;
import com.example.myapplication.donut_activity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
    private ArrayList<Order> orderList;
    //private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.hasExtra("order")) {
            order = (Order) intent.getSerializableExtra("order");
            Log.d("Cart Content", order.toString());

        } else {
            order = new Order();
        } if (intent.hasExtra("order list")) {
            orderList = (ArrayList<Order>) intent.getSerializableExtra("order list");
        } else {
            orderList = new ArrayList<>();
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 1 && resultCode == RESULT_OK){
//            byte[] orderBytes = data.getByteArrayExtra("order");
//            ByteArrayInputStream bis = new ByteArrayInputStream(orderBytes);
//            ObjectInput in = null;
//            try {
//                in = new ObjectInputStream(bis);
//                order = (Order) in.readObject();
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (in != null) {
//                        in.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    public void donutClick(View view){
        Toast.makeText(this, "donut", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, donut_activity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
    public void coffeeClick(View view){
        Toast.makeText(this, "coffee", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_coffee.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
    public void cartClick(View view){
        Toast.makeText(this, "cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, activity_cart.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
//    public void historyClick(View view){
//        Toast.makeText(this, "history", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, activity_history.class);
//        intent.putExtra("order", order);
//        intent.putExtra("order list", orderList);
//        startActivity(intent);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //order = (Order) getIntent().getSerializableExtra("order");
//        //Log.d("MainActivity", "onResume() called");
//        //Log.d("Cart Content", order.toString());
    //}

}