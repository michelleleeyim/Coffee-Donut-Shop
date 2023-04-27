package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import code.Order;
import code.*;

/**
 * Class for the order cart that holds items that have been added to cart.
 * Allows user functionalities to cancel orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class activity_cart extends AppCompatActivity {
    private CartAdapter cartAdapter;
    private RecyclerView recyclerView;
    private ArrayList<MenuItem> itemList;
    private ArrayList<Order> orderList;
    private Order order;
    private double tax = 0.06625;
    private TextView subTotal_order;
    private TextView tax_order;
    private TextView total_order;
    private boolean placeOrder = true;

    /**
     * Sets up a RecyclerView with a CartAdapter to display a list of items.
     * Also sets up TextViews to display the subtotal, tax, items in cart, and total price.
     * @param savedInstanceState Bundle object that contains the saved state of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.shopping_cart);
        subTotal_order = findViewById(R.id.subtotal_order);
        tax_order = findViewById(R.id.tax_order);
        total_order = findViewById(R.id.total_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");

        if (order != null) {
            itemList = order.OrderList();
        } else {
            itemList = new ArrayList<>();
        }

        cartAdapter = new CartAdapter(itemList, this, order);
        recyclerView.setAdapter(cartAdapter);
        updatePrice();
    }

    /**
     * Updates the total price of items in shopping cart, including tax amounts.
     */
    public void updatePrice() {
        double subTotal = order.getTotalPrice();
        double salesTax = subTotal * tax;
        double total = order.priceWithTax();

        subTotal_order.setText("Total: " + String.format("$%.2f", subTotal));
        tax_order.setText("Tax:" + String.format("$%.2f", salesTax));
        total_order.setText("Total:" + String.format("$%.2f", total));
    }

    /**
     * Returns back to main menu when back button is clicked.
     * @param view UI element that triggered the method call.
     */
    public void backClick(View view) {
        Toast.makeText(this, "Main Menu", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("order", order);
        intent.putExtra("order list", orderList);
        startActivity(intent);
    }

    /**
     * creates an alert dialog to confirm order and launches a new activity upon confirmation.
     * @param view UI element that triggered the method call.
     */
    public void placeOrder(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order");
        builder.setMessage("Are you sure you want to place this order?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            /**
             * onClickListener for a dialog interface, which is triggered when an order is placed.
             * @param dialogInterface the dialog that received the click
             * @param i the button that was clicked
             */
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Order placed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), activity_history.class);
                intent.putExtra("order", order);
                intent.putExtra("order list", orderList);
                intent.putExtra("order placed", placeOrder);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}