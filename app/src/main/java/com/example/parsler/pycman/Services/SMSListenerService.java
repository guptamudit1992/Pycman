package com.example.parsler.pycman.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.parsler.pycman.Commons.StaticConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSListenerService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Receiver attached to inbound sms
        smsReceiver(intent);
    }


    /**
     * Function to read inbound sms
     * @param intent
     */
    public static void smsReceiver(Intent intent) {

        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] smsMessages;
            String sender;

            if (bundle != null) {
                try {

                    Object[] pdus = (Object[]) bundle.get("pdus");
                    smsMessages = new SmsMessage[pdus.length];
                    sender = SmsMessage.createFromPdu((byte[]) pdus[0]).getOriginatingAddress();

                    // TODO: Check for verified sender
                    if(sender != null) {

                        // Retrieve OTP from inbound sms
                        getOTPFromMessage(smsMessages, pdus);
                    }
                } catch(Exception e) {

                    // TODO: Error handling in case of sms receiver exception
                }
            }
        }
    }


    /**
     * Function to retrieve OTP from inbound sms
     * @param smsMessages
     * @param pdus
     */
    public static void getOTPFromMessage(SmsMessage[] smsMessages, Object[] pdus) {

        String otp;

        for(int i = 0; i < smsMessages.length; i++) {

            // Read content of inbound sms
            smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = smsMessages[i].getMessageBody();
            Pattern pattern = Pattern.compile(StaticConstants.OTP_REGEX);
            Matcher matcher = pattern.matcher(message);

            if(message.contains(StaticConstants.PYCMAN)) {
                if(matcher.find()) {
                    otp = matcher.group();
                }
            }
        }
    }
}
