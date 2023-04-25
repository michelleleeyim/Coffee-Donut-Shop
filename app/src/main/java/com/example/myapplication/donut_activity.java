package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import code.*;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class donut_activity extends AppCompatActivity {
    private int quantity = 0;
    private Order order;
    private ImageView donutImage;
    private TextView donutQuantity;
    private RadioGroup donutType;
    private RadioButton yeastButton;
    private RadioButton cakeButton;
    private RadioButton donutHoleButton;
    private Button addToCart;
    private String selectedType;
    private String selectedFlavor;

    private int selectedQuantity;
    private ArrayList<Order> orderList;

    /**
     * setter method that assigns the passed in Order to the order variable.
     * @param Order representing the current order basket.
     */
    public void setOrder(Order Order) {
        this.order = Order;
    }

    /**
     * Setter method that assigns passed in ArrayList of Orders to orderList.
     * @param orderlist representing the ArrayList to update orderList.
     */
    public void setOrderList(ArrayList<Order> orderlist) {
        orderList = orderlist;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);


        order = OrderSingle.getInstance().getOrder();
        donut_activity donut = new donut_activity();
        donut.setOrder(order);
        addToCart = findViewById(R.id.addToCart);
        donutImage = findViewById(R.id.donutImage);
        Spinner spinner = findViewById(R.id.spinnerDonut);
        donutQuantity = findViewById(R.id.donutQuantity);
        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton minusButton = findViewById(R.id.minusButton);
        donutType = findViewById(R.id.donutType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.donut_flavors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuant();
            }
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFlavor = parent.getItemAtPosition(position).toString();
                if (selectedFlavor.equals("Plain")) {
                    donutImage.setImageResource(R.drawable.plain);
                } else if (selectedFlavor.equals("Chocolate")) {
                    donutImage.setImageResource(R.drawable.choco);
                } else if (selectedFlavor.equals("Strawberry")) {
                    donutImage.setImageResource(R.drawable.strawberry);
                } else if (selectedFlavor.equals("Powdered Sugar")) {
                    donutImage.setImageResource(R.drawable.yeast);
                }
                else if (selectedFlavor.equals("Boston Cream")) {
                    donutImage.setImageResource(R.drawable.boston);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nada//
            }
        });

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
        startActivity(intent);
    }

    private void donutRadioGroup(View view){
        boolean selected = ((RadioButton) view).isChecked();
        if(view.getId() == R.id.yeastButton && selected){
            selectedType = "Yeast";
        }else if(view.getId()==R.id.cakeButton && selected){
            selectedType = "Cake";
        }else{
            selectedType = "Donut Hole";
        }
        Toast.makeText(this, selectedType, Toast.LENGTH_SHORT).show();
    }

    public void addDonut(View view){
        if(selectedType == null){
            Toast.makeText(getApplicationContext(), "Please select the donut type.", Toast.LENGTH_SHORT).show();
        }else if(selectedFlavor == null){
            Toast.makeText(getApplicationContext(),"Please select the donut flavor.",Toast.LENGTH_SHORT).show();
        }else{
            Donut newDonut = new Donut("donut", selectedType,selectedFlavor,selectedQuantity);
            this.order.add(newDonut);
            Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_SHORT).show();
        }
    }
}