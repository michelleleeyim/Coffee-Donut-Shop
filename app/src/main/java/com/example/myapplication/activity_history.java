package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
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

    }
    public void orderHistoryBackClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

}
