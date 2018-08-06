package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Hendri Setia Wardana
 */

public class QRConfirmDeliveryActivity extends AppCompatActivity {

    public static final int CAPTURE_QRCODE = 1;

    String employeeQRCodeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrconfirm_delivery);

        //Assuming key is SSIS + representative name
        Intent data = getIntent();
        String repName = data.getStringExtra("DisbursementRepName");

        employeeQRCodeValue = "SSIS" + repName;

        captureQRCode();
    }

    public void captureQRCode(){
        Intent intent = new Intent("la.droid.qr.scan");
        intent.putExtra("la.droid.qr.complete", true);
        try {
            startActivityForResult(intent, CAPTURE_QRCODE);
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=la.droid.qr.priva")));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_QRCODE) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("la.droid.qr.result")) {

                    String res = data.getExtras().getString("la.droid.qr.result");

                    Intent replyIntent = new Intent();

                    if (res.equals(employeeQRCodeValue)){
                        replyIntent.putExtra("QRStatus", "Success");
                    } else{
                        replyIntent.putExtra("QRStatus", "Failure");
                    }

                    setResult(RESULT_OK, replyIntent);
                    finish();

                }
            } else if (resultCode == RESULT_CANCELED) {
                // Capture cancelled
                finish();

            } else {
                // Capture failed
                finish();
            }
        }


    }




}
