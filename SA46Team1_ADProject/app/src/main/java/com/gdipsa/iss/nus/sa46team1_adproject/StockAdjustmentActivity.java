package com.gdipsa.iss.nus.sa46team1_adproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StockAdjustmentActivity extends AppCompatActivity {

    private TextView itemAdjustedTextView;
    private Spinner quantityAdjustedSpinner;
    private Spinner reasonSpinner;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_adjustment);

        itemAdjustedTextView = findViewById(R.id.textView_stock_adjustment_item_description);
        quantityAdjustedSpinner = findViewById(R.id.spinner_stock_adjustment_quantity_adjusted);
        reasonSpinner = findViewById(R.id.spinner_stock_adjustment_reason);
        submitButton = findViewById(R.id.button_stock_adjustment_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String remarkSelected = reasonSpinner.getSelectedItem().toString();

                Toast.makeText(getApplicationContext(), remarkSelected, Toast.LENGTH_LONG).show();

            }
        });





    }
}
