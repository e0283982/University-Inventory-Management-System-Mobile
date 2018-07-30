package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockAdjustment;

import java.util.ArrayList;
import java.util.List;

public class StockAdjustmentActivity extends AppCompatActivity {

    private TextView itemAdjustedTextView;
    private Spinner quantityAdjustedSpinner;
    private Spinner reasonSpinner;
    private Button submitButton;

    private ArrayAdapter<Integer> quantitySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_adjustment);

        setTitle("Stock Adjustment");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemAdjustedTextView = findViewById(R.id.textView_stock_adjustment_item_description);
        quantityAdjustedSpinner = findViewById(R.id.spinner_stock_adjustment_quantity_adjusted);
        reasonSpinner = findViewById(R.id.spinner_stock_adjustment_reason);
        submitButton = findViewById(R.id.button_stock_adjustment_submit);

        Intent data = getIntent();

        final String itemAdjustedDescription = data.getStringExtra("ItemAdjusted");
        final String stockRetrievalId = data.getStringExtra("StockRetrievalId");
        int quantityRetrieved = data.getIntExtra("QuantityRetrieved", 0);

        itemAdjustedTextView.setText(itemAdjustedDescription);


        List<Integer> quantitySpinner = new ArrayList<Integer>();

        for (int i = 1; i <= quantityRetrieved; i++){
            quantitySpinner.add(i);
        }

        quantitySpinnerAdapter = new ArrayAdapter<Integer>(StockAdjustmentActivity.this, android.R.layout.simple_spinner_item, quantitySpinner);

        quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        quantityAdjustedSpinner.setAdapter(quantitySpinnerAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StockAdjustmentActivity.this);
                builder.setMessage("Are you sure you want to submit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String quantityAdjustedString = quantityAdjustedSpinner.getSelectedItem().toString();
                        int quantityAdjusted = Integer.parseInt(quantityAdjustedString);
                        String remarkSelected = reasonSpinner.getSelectedItem().toString();

                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        String requestorId = pref.getString("EmployeeID", "Employee ID");
                        StockAdjustment newStockAdjustment = new StockAdjustment(requestorId, itemAdjustedDescription, quantityAdjusted, remarkSelected, stockRetrievalId);

                        new MyTask().execute(newStockAdjustment);

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null){
                            dialog.dismiss();
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


//        Toast.makeText(getApplicationContext(), data.getStringExtra("StockRetrievalId"), Toast.LENGTH_SHORT).show();


    }


    private class MyTask extends AsyncTask<StockAdjustment, Void, Void> {
        @Override
        protected Void doInBackground(StockAdjustment... params) {

            StockAdjustment.createStockAdjustment(params[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Toast.makeText(getApplicationContext(), "Stock adjustment successfully created", Toast.LENGTH_SHORT).show();

            Intent data = getIntent();
            String stockRetrievalId = data.getStringExtra("StockRetrievalId");
            if (stockRetrievalId.equals("NoStockRetrieval")){
                Intent intent = new Intent(StockAdjustmentActivity.this, DescriptionSearchActivity.class);
                startActivity(intent);
            } else{
                finish();
            }

        }
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        switch (id){
//
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(StockAdjustmentActivity.this);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


}
