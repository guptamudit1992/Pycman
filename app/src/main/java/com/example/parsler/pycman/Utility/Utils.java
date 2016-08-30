package com.example.parsler.pycman.Utility;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utils {

    public static String getIMEINumber(Context context) {

        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
