package com.example.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

import static java.text.NumberFormat.*;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int NumOfCaf = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void decrement(View view) {

        NumOfCaf=NumOfCaf-1;
        display(NumOfCaf);
    }

    public void increment(View view) {

        NumOfCaf=NumOfCaf+1;
        display(NumOfCaf);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        display(NumOfCaf);
        displayPrice(NumOfCaf * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(getCurrencyInstance().format(number));
    }



}