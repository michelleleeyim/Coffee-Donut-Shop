package com.example.myapplication;

import code.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class for Coffee View that allows user to place coffee orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class activity_coffee extends AppCompatActivity {
    private int NONE = 0;
    private int ADD_IN_SIZE = 5;
    private int INCREMENT = 1;
    private int quantity;
    private Order order;
    private ArrayList<Order> orderList;
    private TextView coffeeQuantity;
    private TextView subTotalDisplay;
    private String size;
    private CheckBox sweetCream;
    private CheckBox irishCream;
    private CheckBox caramel;
    private CheckBox mocha;
    private CheckBox frenchVanilla;

    /**
     *  This method initializes various views, widgets, spinner, button clicks, and sets text.
     *  It also gets data from the intent that started the activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        activity_coffee coffee = new activity_coffee();
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
            /**
             * Defines a listener that sets the variable "size" to the selected item of a spinner.
             * @param parentView The AdapterView where the selection happened
             * @param selectedItemView The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                       int position, long id) {
                size = parentView.getItemAtPosition(position).toString();
            }

            /**
             * Highlights lack of functionality if nothing is selected.
             * @param parentView The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        coffeeAddButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the increaseQuant() method if the plus button was selected.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                increaseQuant();
            }
        });
        coffeeMinusButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the decreaseQuant() method if the minus button was selected.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                decreaseQuant();
            }
        });
        coffeeQuantity.setText(String.valueOf(quantity));
    }

    /**
     * Increases the quantity of item selected and updates the integer value depicted on screen.
     */
    private void increaseQuant() {
        quantity++;
        coffeeQuantity.setText(String.valueOf(quantity));
    }

    /**
     * Decreases the quantity of item selected and updates the integer value depicted on screen.
     */
    private void decreaseQuant() {
        if (quantity > NONE) {
            quantity--;
            coffeeQuantity.setText(String.valueOf(quantity));
        }
    }

    /**
     * Returns the scene back to main menu and passes in order and order list data using intent.
     * @param view The view that was clicked.
     */
    public void backClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    /**
     * Creates a coffee object depending on the information inputted by the user.
     * @return
     */
    private Coffee createCoffee() {
        String[] addIns = new String[ADD_IN_SIZE];
        if (sweetCream.isChecked()) {
            addIns[0] = "Sweet Cream";
        }
        if (frenchVanilla.isChecked()) {
            addIns[1] = "French Vanilla";
        }
        if (irishCream.isChecked()) {
            addIns[2] = "Irish Cream";
        }
        if (caramel.isChecked()) {
            addIns[3] = "Caramel";
        }
        if (mocha.isChecked()) {
            addIns[4] = "Mocha";
        }
        Coffee coffee = new Coffee("Coffee", size, addIns, quantity);
        return coffee;
    }

    /**
     * Adds a coffee object to the order if all required information was inputted.
     * @param view The view that was clicked.
     */
    public void addCoffee(View view) {
        if (size == null) {
            Toast.makeText(getApplicationContext(), "Please select a size",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a quantity",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Coffee newCoffee = createCoffee();
        order.add(newCoffee);
        Toast.makeText(getApplicationContext(), "Added " + quantity + " " + size
                + " coffee(s) to your order", Toast.LENGTH_SHORT).show();
        subTotalDisplay.setText(String.format("%.2f", order.getTotalPrice()));
    }

    /**
     * Removes coffee object from the order if object matching information inputted is in order.
     * @param view the view that was selected.
     */
    public void removeCoffee(View view) {
        if (size == null) {
            Toast.makeText(getApplicationContext(), "Please select the cup size.",
                    Toast.LENGTH_SHORT).show();
        } else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Coffee newCoffee = createCoffee();
            Coffee currentItem = (Coffee) order.returnItem(newCoffee);
            if (currentItem == null) {
                Toast.makeText(getApplicationContext(), "No matching item.",
                        Toast.LENGTH_SHORT).show();
            }
            if (currentItem != null) {
                if (currentItem.getQuantity() < quantity) {
                    Toast.makeText(getApplicationContext(), "Enter quantity less than "
                                    + Integer.toString(currentItem.getQuantity() + INCREMENT),
                            Toast.LENGTH_SHORT).show();
                } else {
                    order.remove(newCoffee);
                    subTotalDisplay.setText(String.format("%.2f", order.getTotalPrice()));
                    Toast.makeText(getApplicationContext(), "Removed from cart.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
