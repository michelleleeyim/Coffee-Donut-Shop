package com.example.myapplication;
import code.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class activity_coffee extends AppCompatActivity {
    private int NONE = 0;
    private int quantity;
    private Order order;
    private TextView coffeeQuantity;
    public void setOrder(Order Order) {
        this.order = Order;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        order = (Order) getIntent().getSerializableExtra("order");
        activity_coffee coffee = new activity_coffee();
        coffee.setOrder(order);
        Spinner spinner = findViewById(R.id.spinnerCoffee);
        coffeeQuantity = findViewById(R.id.coffeeQuantity);
        ImageButton coffeeAddButton = findViewById(R.id.coffeeAddButton);
        ImageButton coffeeMinusButton = findViewById(R.id.coffeeMinusButton);

        quantity = NONE;

        coffeeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseQuant();
            }
        });
        coffeeMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseQuant();
            }
        });

        coffeeQuantity.setText(String.valueOf(quantity));
    }

    private void increaseQuant(){
        quantity++;
        coffeeQuantity.setText(String.valueOf(quantity));
    }
    private void decreaseQuant() {
        if (quantity > 0) {
            quantity--;
            coffeeQuantity.setText(String.valueOf(quantity));
        }
    }

    public void backClick(View view){
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addCoffee(View view) {

    }

    public void removeCoffee(View view) {

    }


}
