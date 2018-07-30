package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;

import java.util.List;

public class QRCodeSearchActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    Boolean backToActivity = false;

    public static final int CAPTURE_QRCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_search);

        progressBar = findViewById(R.id.progressbar_qrcode_search);

        captureQRCode();

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (backToActivity){
            backToActivity = false;
            recreate();
        }

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

                    new MyTask().execute(res);

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


    private class MyTask extends AsyncTask<String, Void, Item> {
        @Override
        protected Item doInBackground(String... params) {
            return Item.getItem(params[0]);
        }
        @Override
        protected void onPostExecute(Item result) {

            Context context = QRCodeSearchActivity.this;

            if (result == null){
                Toast.makeText(context, "Item Not Found", Toast.LENGTH_SHORT).show();
                finish();
            }

            progressBar.setVisibility(View.GONE);
            backToActivity = true;



            Intent intent = new Intent(context, SearchDetailsActivity.class);

            intent.putExtra("ItemCode", result.getItemCode());
            intent.putExtra("Category", result.getCategory());
            intent.putExtra("Description", result.getDescription());
            intent.putExtra("Quantity", result.getQuantity());
            intent.putExtra("UoM", result.getUoM());
            intent.putExtra("Supplier1", result.getSupplier1());
            intent.putExtra("Supplier2", result.getSupplier2());
            intent.putExtra("Supplier3", result.getSupplier3());
            intent.putExtra("Active", result.getActive());

            ((Activity) context).startActivity(intent);

        }
    }


}
