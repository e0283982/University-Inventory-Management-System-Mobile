package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class SearchDetailsActivity extends AppCompatActivity {

    private TextView itemCodeTextView;
    private TextView itemCategoryTextView;
    private TextView itemDescriptionTextView;
    private TextView activeTextView;
    private TextView supplier1TextView;
    private TextView supplier2TextView;
    private TextView supplier3TextView;
    private TextView quantityTextView;

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

        itemCodeTextView.setText("Item Code: " + itemCode);
        itemCategoryTextView.setText("Item Category: " + category);
        itemDescriptionTextView.setText("Description: " + description);

        if (active == 1){
            activeTextView.setText("Active: Yes");
        } else{
            activeTextView.setText("Active: No");
        }

        supplier1TextView.setText("Supplier1: " + supplier1);
        supplier2TextView.setText("Supplier2: " + supplier2);
        supplier3TextView.setText("Supplier3: " + supplier3);

        DecimalFormat fmt = new DecimalFormat("#,##0");

        String quantityText = fmt.format(quantity);

        quantityTextView.setText("Balance: " + quantityText + " " + UoM);




    }




}
