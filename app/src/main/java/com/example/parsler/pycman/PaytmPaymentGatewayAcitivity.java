package com.example.parsler.pycman;

import android.app.Activity;
import android.os.Bundle;

import com.paytm.pgsdk.PaytmMerchant;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PaytmPaymentGatewayAcitivity extends Activity {

    private int randomInt = 0;
    private PaytmPGService Service = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm_payment_gateway_acitivity);

        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(1000);
        //for testing environment
        Service = PaytmPGService.getStagingService();
        //for production environment
        /*Service = PaytmPGService.getProductionService();*/

        /*PaytmMerchant constructor takes two parameters
        1) Checksum generation url
        2) Checksum verification url
        Merchant should replace the below values with his values*/


        PaytmMerchant Merchant = new PaytmMerchant("https://pguat.paytm.com/merchant-chksum/ChecksumGenerator","https://pguat.paytm.com/merchant-chksum/ValidateChksum");

        //below parameter map is required to construct PaytmOrder object, Merchant should replace below map values with his own values

        Map<String, String> paramMap = new HashMap<String, String>();

        //these are mandatory parameters
        paramMap.put("REQUEST_TYPE", "DEFAULT");
        paramMap.put("ORDER_ID", String.valueOf(randomInt));
        //MID provided by paytm
        paramMap.put("MID", "id provided by paytm");
        paramMap.put("CUST_ID", "CUST123");
        paramMap.put("CHANNEL_ID", "WAP");
        paramMap.put("INDUSTRY_TYPE_ID", "Retail");
        paramMap.put("WEBSITE", "paytm");
        paramMap.put("TXN_AMOUNT", "1");
        paramMap.put("THEME", "merchant");

        PaytmOrder Order = new PaytmOrder(paramMap);


        Service.initialize(Order, Merchant,null);
        Service.startPaymentTransaction(this, false, false, new PaytmPaymentTransactionCallback() {

            @Override
            public void onTransactionSuccess(Bundle bundle) {

            }

            @Override
            public void onTransactionFailure(String s, Bundle bundle) {

            }

            @Override
            public void networkNotAvailable() {

            }

            @Override
            public void clientAuthenticationFailed(String s) {

            }

            @Override
            public void someUIErrorOccurred(String s) {

            }

            @Override
            public void onErrorLoadingWebPage(int i, String s, String s1) {

            }
        });
    }

}
