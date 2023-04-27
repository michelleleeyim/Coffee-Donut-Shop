package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import code.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class for Donut View that allows user to place coffee orders.
 * @author Stephanie Lin, Hyeseo Lee
 */
public class donut_activity extends AppCompatActivity implements DonutAdapter.OnDonutClickListener {
    private int NONE = 0;
    private int increment = 0;
    private int quantity;
    private Order order;
    private TextView donutQuantity;
    private TextView subTotalDisplay;
    private RadioGroup donutType;
    private Button addToCart;
    private String selectedType;
    private String selectedFlavor;
    private ArrayList<Order> orderList;
    private DonutAdapter donutAdapter;
    private RecyclerView recyclerView;
    private ImageButton addButton;
    private ImageButton minusButton;
    private ImageView donutTypeImage;

    /**
     * Sets up UI and the necessary variables, adapters and event listeners for the donut screen.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateHelper(savedInstanceState);
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
            /**
             * Calls the increaseQuant() method if the plus button was selected.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                increaseQuant();
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Calls the decreaseQuant() method if the minus button was selected.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                decreaseQuant();
            }
        });
        donutQuantity.setText(String.valueOf(quantity));
        donutType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Registers a listener for when the checked radio button in a RadioGroup changes.
             * Calls the method donutRadioGroup with the selected radio button as a parameter.
             * @param group the group in which the checked radio button has changed
             * @param checkedId the unique identifier of the newly checked radio button
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                donutRadioGroup(donutType.findViewById(checkedId));
            }
        });
    }

    /**
     * Initializes various variables and UI elements needed in the DonutActivity.
     * @param savedInstanceState If the activity is being re-initialized after
     *      previously being shut down then this Bundle contains the data it most
     *      recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    private void onCreateHelper(Bundle savedInstanceState) {
        setContentView(R.layout.activity_donut);
        order = (Order) getIntent().getSerializableExtra("order");
        orderList = (ArrayList<Order>) getIntent().getSerializableExtra("order list");
        addToCart = findViewById(R.id.addToCart);
        donutQuantity = findViewById(R.id.donutQuantity);
        subTotalDisplay = findViewById(R.id.subTotalDisplay);
        addButton = findViewById(R.id.addButton);
        minusButton = findViewById(R.id.minusButton);
        donutType = findViewById(R.id.donutType);
        recyclerView = findViewById(R.id.donut_flavor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        donutTypeImage = findViewById(R.id.donutTypeImage);
        quantity = NONE;
    }

    /**
     *Displays a short toast message indicating the donut flavor selection.
     * @param position Intger representation of position of the item in the list clicked by the user.
     */
    @Override
    public void onDonutClick(int position) {
        selectedFlavor = ((DonutAdapter) recyclerView.getAdapter()).getItemList().get(position);
        Toast.makeText(this, "You selected " + selectedFlavor + " flavor.",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Increases the quantity of item selected and updates the integer value depicted on screen.
     */
    private void increaseQuant() {
        quantity++;
        donutQuantity.setText(String.valueOf(quantity));
    }

    /**
     * Decreases the quantity of item selected and updates the integer value depicted on screen.
     */
    private void decreaseQuant() {
        if (quantity > 0) {
            quantity--;
            donutQuantity.setText(String.valueOf(quantity));
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
     * Sets the selected type of donut based on the radio button that is checked.
     * @param view The view that was clicked.
     */
    private void donutRadioGroup(View view) {
        boolean selected = ((RadioButton) view).isChecked();
        if (view.getId() == R.id.yeastButton && selected) {
            selectedType = "Yeast";
            donutTypeImage.setImageResource(R.drawable.type);
        } else if (view.getId() == R.id.cakeButton && selected) {
            selectedType = "Cake";
            donutTypeImage.setImageResource(R.drawable.cake);
        } else {
            selectedType = "Donut Hole";
            donutTypeImage.setImageResource(R.drawable.hole);
        }
        Toast.makeText(this, selectedType, Toast.LENGTH_SHORT).show();
    }

    /**
     * Adds a donut object to the order if all required information was inputted.
     * @param view The view that was clicked.
     */
    public void addDonut(View view) {
        if (selectedType == null) {
            Toast.makeText(getApplicationContext(), "Please select the donut type.",
                    Toast.LENGTH_SHORT).show();
        } else if (selectedFlavor == null) {
            Toast.makeText(getApplicationContext(), "Please select the donut flavor.",
                    Toast.LENGTH_SHORT).show();
        } else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Donut newDonut = new Donut("donut", selectedType, selectedFlavor, quantity);
            order.add(newDonut);
            Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_SHORT).show();
            subTotalDisplay.setText(String.format("%.2f", order.getTotalPrice()));
        }
    }

    /**
     * Removes donut object from the order if object matching information inputted is in order.
     * @param view the view that was selected.
     */
    public void removeDonut(View view) {
        if (selectedType == null) {
            Toast.makeText(getApplicationContext(), "Please select the donut type.",
                    Toast.LENGTH_SHORT).show();
        } else if (selectedFlavor == null) {
            Toast.makeText(getApplicationContext(), "Please select the donut flavor.",
                    Toast.LENGTH_SHORT).show();
        } else if (quantity == NONE) {
            Toast.makeText(getApplicationContext(), "Please select a different quantity.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Donut newDonut = new Donut("donut", selectedType, selectedFlavor, quantity);
            Donut currentItem = (Donut) order.returnItem(newDonut);
            if (currentItem == null) {
                Toast.makeText(getApplicationContext(), "No matching item.",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (currentItem.getQuantity() < quantity) {
                    Toast.makeText(getApplicationContext(), "Enter quantity less than "
                                    + Integer.toString(currentItem.getQuantity() + increment),
                            Toast.LENGTH_SHORT).show();
                } else {
                    order.remove(newDonut);
                    subTotalDisplay.setText(String.format("%.2f", order.getTotalPrice()));
                    Toast.makeText(getApplicationContext(), "Removed from cart.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}