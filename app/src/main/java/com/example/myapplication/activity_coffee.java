package com.example.myapplication;
import code.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class activity_coffee extends AppCompatActivity {
    private int NONE = 0;
    private int quantity;
    private Order order;
    private TextView coffeeQuantity;
    private TextView subTotalDisplay;

    private String size;
    private CheckBox sweetCream;
    private CheckBox irishCream;
    private CheckBox caramel;
    private CheckBox mocha;
    private CheckBox frenchVanilla;
    private String[] addIns = new String[5]; // array to store selected add-ins


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
        subTotalDisplay = findViewById(R.id.subTotalDisplay);
        coffeeQuantity = findViewById(R.id.coffeeQuantity);
        ImageButton coffeeAddButton = findViewById(R.id.coffeeAddButton);
        ImageButton coffeeMinusButton = findViewById(R.id.coffeeMinusButton);
        sweetCream = findViewById(R.id.sweetCream);
        irishCream = findViewById(R.id.irishCream);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        frenchVanilla = findViewById(R.id.frenchVanilla);

        quantity = NONE;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                size = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

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

    private void increaseQuant() {
        quantity++;
        coffeeQuantity.setText(String.valueOf(quantity));
    }

    private void decreaseQuant() {
        if (quantity > 0) {
            quantity--;
            coffeeQuantity.setText(String.valueOf(quantity));
        }
    }

    public void backClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        //intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    public void addCoffee(View view) {
        if (size == null) {
            Toast.makeText(getApplicationContext(), "Please select a size", Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a quantity", Toast.LENGTH_SHORT).show();
            return;
        }
        Coffee newCoffee = createCoffee();
        order.add(newCoffee);
        Toast.makeText(getApplicationContext(), "Added " + quantity + " " + size + " coffee(s) to your order", Toast.LENGTH_SHORT).show();
        subTotalDisplay.setText(String.format("$%.2f", order.getTotalPrice()));
        Log.d("Cart Content", order.toString());
    }

    public void removeCoffee(View view) {

        // Check if a coffee size and quantity have been selected
        if (size == null) {
            Toast.makeText(getApplicationContext(), "Please select the cup size.", Toast.LENGTH_SHORT).show();
            return;
        } else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            // Create a new Coffee object with the selected add-ins, size, and quantity
            Coffee newCoffee = createCoffee();
            // Get the matching Coffee object from the Order object
            Coffee currentItem = (Coffee) order.returnItem(newCoffee);
            if (currentItem == null) {
                Toast.makeText(getApplicationContext(), "No matching item.", Toast.LENGTH_SHORT).show();
            }
            if(currentItem!=null) {
                if (currentItem.getQuantity() < quantity) {
                    Toast.makeText(getApplicationContext(), "Enter quantity less than " + Integer.toString(currentItem.getQuantity() + 1), Toast.LENGTH_SHORT).show();
                } else {
                    order.remove(currentItem);
                    subTotalDisplay.setText(String.format("$%.2f", order.getTotalPrice()));
                    Toast.makeText(getApplicationContext(), "Removed from cart.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private Coffee createCoffee(){
        String[] addIns = new String[5];
        if(sweetCream.isChecked()){
            addIns[0] = "Sweet Cream";
        }if(frenchVanilla.isChecked()){
            addIns[1] = "French Vanilla";
        }if(irishCream.isChecked()){
            addIns[2]= "Irish Cream";
        }if(caramel.isChecked()){
            addIns[3] = "Caramel";
        }if(mocha.isChecked()){
            addIns[4] = "Mocha";
        }
        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);
        return coffee;
    }
    //hi

}
