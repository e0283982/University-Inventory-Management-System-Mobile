package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class SearchDetailsActivity extends AppBaseActivity {

    private TextView itemCodeTextView;
    private TextView itemCategoryTextView;
    private TextView itemDescriptionTextView;
    private TextView activeTextView;
    private TextView supplier1TextView;
    private TextView supplier2TextView;
    private TextView supplier3TextView;
    private TextView quantityTextView;
    private Button adjustButton;

    private String itemCode;
    private String category;
    private String description;
    private int quantity;
    private String UoM;
    private String supplier1;
    private String supplier2;
    private String supplier3;
    private int active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);


        itemCodeTextView = findViewById(R.id.textView_search_details_item_code);
        itemCategoryTextView = findViewById(R.id.textView_search_details_item_category);
        itemDescriptionTextView = findViewById(R.id.textView_search_details_item_description);
        activeTextView = findViewById(R.id.textView_search_details_item_active);
        supplier1TextView = findViewById(R.id.textView_search_details_item_supplier1);
        supplier2TextView = findViewById(R.id.textView_search_details_item_supplier2);
        supplier3TextView = findViewById(R.id.textView_search_details_item_supplier3);
        quantityTextView = findViewById(R.id.textView_search_details_item_quantity);
        adjustButton = findViewById(R.id.button_search_details_adjust);

        Intent data = getIntent();

        itemCode = data.getStringExtra("ItemCode");
        category = data.getStringExtra("Category");
        description = data.getStringExtra("Description");
        quantity = data.getIntExtra("Quantity", 0);
        UoM = data.getStringExtra("UoM");
        supplier1 = data.getStringExtra("Supplier1");
        supplier2 = data.getStringExtra("Supplier2");
        supplier3 = data.getStringExtra("Supplier3");
        active = data.getIntExtra("Active", 0);

        setTitle("Item Details");

        itemCodeTextView.setText(itemCode);
        itemCategoryTextView.setText(category);
        itemDescriptionTextView.setText(description);

        if (active == 1){
            activeTextView.setText("Yes");
        } else{
            activeTextView.setText("No");
        }

        supplier1TextView.setText(supplier1);
        supplier2TextView.setText(supplier2);
        supplier3TextView.setText(supplier3);

        DecimalFormat fmt = new DecimalFormat("#,##0");

        String quantityText = fmt.format(quantity);

        quantityTextView.setText("Balance: " + quantityText + " " + UoM);

        adjustButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, StockAdjustmentActivity.class);
                intent.putExtra("ItemAdjusted", description);
                intent.putExtra("QuantityRetrieved", quantity);
                intent.putExtra("StockRetrievalId", "NoStockRetrieval");
                ((Activity) context).startActivity(intent);

            }
        });
    }

}
