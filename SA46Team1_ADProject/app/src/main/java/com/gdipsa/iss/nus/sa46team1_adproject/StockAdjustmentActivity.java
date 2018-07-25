package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        itemAdjustedTextView.setText(data.getStringExtra("ItemAdjusted"));

        int quantityRetrieved = data.getIntExtra("QuantityRetrieved", 0);
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

                String quantityAdjustedString = quantityAdjustedSpinner.getSelectedItem().toString();
                int quantityAdjusted = Integer.parseInt(quantityAdjustedString);

                String remarkSelected = reasonSpinner.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), "" + quantityAdjusted, Toast.LENGTH_LONG).show();

            }
        });





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
