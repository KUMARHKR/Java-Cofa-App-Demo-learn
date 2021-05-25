package com.example.justjava;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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
        if (NumOfCaf ==1){
            Toast.makeText(this ,"You can't have less then 1 cup cofachino ", Toast.LENGTH_SHORT).show();
            return ;
        }

        NumOfCaf = NumOfCaf - 1;
        displayQuantity(NumOfCaf);
    }

    public void increment(View view) {
        if (NumOfCaf ==100){
            Toast.makeText(this ,"You can't have more then 100 cup cofachino ", Toast.LENGTH_SHORT).show();
            return;
        }
        NumOfCaf = NumOfCaf + 1;
        displayQuantity(NumOfCaf);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameFild = findViewById(R.id.nameTextView);
        String nameText = nameFild.getText().toString();


        CheckBox creamBox = findViewById(R.id.creamBox);
        boolean hasWhipcream = creamBox.isChecked();
        CheckBox extraChocolet = findViewById(R.id.extraChocoletView);
        boolean hasChocolate = extraChocolet.isChecked();

        int priceView = calculatePrice(hasChocolate ,hasWhipcream );

        String priceMassage = createOrderSummary(nameText,priceView,hasChocolate,hasWhipcream);
//send email tool
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_LOCUS_ID, "kdas08546@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Cafa Order in just java app" + priceView);
        intent.putExtra(Intent.EXTRA_TEXT, priceMassage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        displayMessage(priceMassage);

//        e-mail intent ----
//                <activity >
//            <intent-filter>
//                <action android:name="android.intent.action.SEND" />
//                <data android:type="*/*" />
//                <category android:name="android.intent.category.DEFAULT" />
//            </intent-filter>
//            <intent-filter>
//                <action android:name="android.intent.action.SENDTO" />
//                <data android:scheme="mailto" />
//                <category android:name="android.intent.category.DEFAULT" />
//            </intent-filter>
//        </activity>

    }





    private int calculatePrice(boolean hasChocolate, boolean hasWhipcream) {
        int basePrice = 5;
        if (hasChocolate){
            basePrice =basePrice +2;
        }
        if (hasWhipcream){
            basePrice = basePrice + 1;
        }


        return  NumOfCaf * basePrice;
    }



    private String createOrderSummary(String nameText,int price, boolean checkBox , boolean extraChocolet) {
        String priceMassage = "Name: " + nameText ;
        priceMassage = priceMassage + "\n Quantity: " + NumOfCaf;
        priceMassage = priceMassage + "\n Add-on: " + checkBox;
        priceMassage = priceMassage + "\n Total: ₹" + price;
        priceMassage = priceMassage + "\nAdd chocolate?" + extraChocolet  ;
        priceMassage = priceMassage + "\n Thank You !";

        return priceMassage;
    }




    /**
     * This method displays the given quantity value on the screen.
     */

    @SuppressLint("SetTextI18n")
    private void displayQuantity(int numOfCaf) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numOfCaf);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}