package com.example.parsler.pycman;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class PaymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }



    /**
     * Function to fetch Order Summary
     * @param view
     */
    public void getOrderConfirmation(View view) {

        Intent intent = new Intent(this, OrderConfirmationActivity.class);
        startActivity(intent);

    }
}
