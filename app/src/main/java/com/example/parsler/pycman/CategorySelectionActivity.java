package com.example.parsler.pycman;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;

public class CategorySelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection);
    }



    public void getPaymentActivity(View view) {

        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}
