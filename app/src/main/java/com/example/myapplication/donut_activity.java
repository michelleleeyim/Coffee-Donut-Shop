package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import code.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class donut_activity extends AppCompatActivity implements DonutAdapter.OnDonutClickListener {
    private int NONE = 0;
    private int increment = 0;
    private int quantity;
    private Order order;
    private ImageView donutImage;
    private TextView donutQuantity;
    private TextView subTotalDisplay;
    private RadioGroup donutType;
    private RadioButton yeastButton;
    private RadioButton cakeButton;
    private RadioButton donutHoleButton;
    private Button addToCart;
    private String selectedType;
    private String selectedFlavor;

    private int selectedQuantity;
    private ArrayList<Order> orderList;
    private DonutAdapter donutAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        addToCart = findViewById(R.id.addToCart);
        donutQuantity = findViewById(R.id.donutQuantity);
        subTotalDisplay = findViewById(R.id.subTotalDisplay);
        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton minusButton = findViewById(R.id.minusButton);
        donutType = findViewById(R.id.donutType);
        recyclerView = findViewById(R.id.donut_flavor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        quantity = NONE;
        ArrayList<String> donutFlavors = new ArrayList<>();
        donutFlavors.add("Plain Donut");
        donutFlavors.add("Chocolate Donut");
        donutFlavors.add("Strawberry Donut");
        donutFlavors.add("Glazed Donut");
        donutFlavors.add("Cookie Monster Donut");
        donutFlavors.add("Cherry Donut");
        donutFlavors.add("Boston Cream Donut");
        donutFlavors.add("Powdered Sugar Donut");
        donutFlavors.add("Jelly Donut");
        donutAdapter = new DonutAdapter(donutFlavors, this, this);
        recyclerView.setAdapter(donutAdapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { increaseQuant(); }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuant();
            }
        });
        donutQuantity.setText(String.valueOf(quantity));
        donutType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                donutRadioGroup(donutType.findViewById(checkedId));
            }
        });
    }
  @Override
    public void onDonutClick(int position) {
        selectedFlavor = ((DonutAdapter) recyclerView.getAdapter()).getItemList().get(position);
      Toast.makeText(this, "You selected " + selectedFlavor + " flavor.", Toast.LENGTH_SHORT).show();
  }
    private void increaseQuant(){
        quantity++;
        donutQuantity.setText(String.valueOf(quantity));
    }
    private void decreaseQuant(){
        if(quantity>0){
            quantity--;
            donutQuantity.setText(String.valueOf(quantity));
        }
    }
    public void backClick(View view){
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }
    private void donutRadioGroup(View view) {
        boolean selected = ((RadioButton) view).isChecked();
        if (view.getId() == R.id.yeastButton && selected) {
            selectedType = "Yeast";
        } else if (view.getId() == R.id.cakeButton && selected) {
            selectedType = "Cake";
        } else {
            selectedType = "Donut Hole";
        }
        Toast.makeText(this, selectedType, Toast.LENGTH_SHORT).show();
    }
    public void addDonut(View view){
        if(selectedType == null){
            Toast.makeText(getApplicationContext(), "Please select the donut type.", Toast.LENGTH_SHORT).show();
        }else if(selectedFlavor == null){
            Toast.makeText(getApplicationContext(),"Please select the donut flavor.",Toast.LENGTH_SHORT).show();
        }else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.", Toast.LENGTH_SHORT).show();
        }
        else{
            Donut newDonut = new Donut("donut", selectedType,selectedFlavor,quantity);
            order.add(newDonut);
            Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_SHORT).show();
            Log.d("Cart Content", order.toString());
            subTotalDisplay.setText(String.format("$%.2f", order.getTotalPrice()));
        }
    }
    public void removeDonut(View view) {
        if(selectedType == null){
            Toast.makeText(getApplicationContext(), "Please select the donut type.", Toast.LENGTH_SHORT).show();
        }else if(selectedFlavor == null){
            Toast.makeText(getApplicationContext(),"Please select the donut flavor.",Toast.LENGTH_SHORT).show();
        }else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.", Toast.LENGTH_SHORT).show();
        } else {
            Donut newDonut = new Donut("donut", selectedType,selectedFlavor,quantity);
            Donut currentItem = (Donut) order.returnItem(newDonut);
            if (currentItem == null) {
                Toast.makeText(getApplicationContext(), "No matching item.", Toast.LENGTH_SHORT).show();
            } else {
                if (currentItem.getQuantity() < quantity) {
                    Toast.makeText(getApplicationContext(), "Enter quantity less than " + Integer.toString(currentItem.getQuantity() + increment), Toast.LENGTH_SHORT).show();
                } else {
                    order.remove(newDonut);
                    subTotalDisplay.setText(String.format("$%.2f", order.getTotalPrice()));
                    Toast.makeText(getApplicationContext(), "Removed from cart.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}