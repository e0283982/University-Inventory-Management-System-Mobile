package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

    public static final int CAPTURE_QRCODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_search);

        progressBar = findViewById(R.id.progressbar_qrcode_search);

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

                    //TODO:
                    String res = data.getExtras().getString("la.droid.qr.result");
//                    Toast.makeText(this, res, Toast.LENGTH_LONG).show();

                    new MyTask().execute(res);





                }
            } else if (resultCode == RESULT_CANCELED) {
                // Capture cancelled

            } else {
                // Capture failed
            }
        }


    }


    private class MyTask extends AsyncTask<String, Void, Item> {
        @Override
        protected Item doInBackground(String... params) {

//            List<Item> itemList = Item.listItems(params[0]);

            return Item.getItem(params[0]);
        }
        @Override
        protected void onPostExecute(Item result) {

            progressBar.setVisibility(View.GONE);


            Context context = QRCodeSearchActivity.this;
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
