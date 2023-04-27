package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import code.Order;

public class activity_history extends AppCompatActivity {
    private Order order;
    private HistoryAdapter historyAdapter;
    private ArrayList<Order> orderList;
    private ListView orderHistoryView;
    private boolean placedOrder;

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



        // Retrieve the current order from OrderViewModel
        String history = "";
        for (int i = 0; i < orderList.size(); i++) {
            history += orderList.get(i).toString();
        }
        Log.i("Order Contents", history);

    }
    public void orderHistoryBackClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
    public void createFile() {
        File file = new File("Order History.txt");
    }
    public void exportData(View view) {
        createFile();
        try{
            FileWriter writer = new FileWriter("Order History.txt");
            for (int i = 0; i < orderList.size(); i++) {
                writer.write("Order number " + Integer.toString(i + 1) + ": \n");
                Order orderBasket =  orderList.get(i);
                if (orderBasket.getTotalPrice() == 0) {
                    writer.write("order canceled.\n\n");
                } else {
                    writer.write(orderBasket.toString());
                    writer.write("Total price: " + String.format("$%.2f", orderBasket.priceWithTax()) + "\n\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
