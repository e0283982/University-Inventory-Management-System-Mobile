package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class QRConfirmDeliveryActivity extends AppCompatActivity {

    public static final int CAPTURE_QRCODE = 1;

    String employeeQRCodeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrconfirm_delivery);

        //Assuming Employee Id is E4, key is SSIS

        String employeeId = "E4";
        employeeQRCodeValue = "SSIS" + employeeId;

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
//                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                    } else{
                        replyIntent.putExtra("QRStatus", "Failure");
//                        Toast.makeText(getApplicationContext(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
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
