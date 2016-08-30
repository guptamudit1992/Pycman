package com.example.parsler.pycman;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.parsler.pycman.Commons.StaticConstants;
import com.example.parsler.pycman.Components.Fonts.FontsOverride;
import com.example.parsler.pycman.Services.SMSListenerService;

public class LoginActivity extends Activity {

    private static IntentFilter intentFilter;
    private static SMSListenerService smsListenerService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Default Font
        FontsOverride.setDefaultFont(this,
                StaticConstants.TYPEFACE_FONT_MONOSPACE,
                StaticConstants.TYPEFACE_FONT_COPPERPLATE_REGULAR);

        setContentView(R.layout.activity_login);

        // Initiate inbound sms listener
        intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        smsListenerService = new SMSListenerService();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(smsListenerService, intentFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsListenerService);
    }


    /**
     * Function to lead to get started
     * @param view
     */
    public void gettingStarted(View view) {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
