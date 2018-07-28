package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementListDetail;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.NewRequisition;
import com.gdipsa.iss.nus.sa46team1_adproject.DescriptionSearchActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DescriptionSearchAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.ArrayList;
import java.util.List;

public class NewRequisitionActivity extends AppBaseDepartmentActivity {

    List<Item> mItemList;
    private int maxNewItemQuantity = 50;

    FloatingActionButton fab;

    private RecyclerView mRecyclerViewNewRequisition;
    private NewRequisitionAdapter adapter;

    List<NewRequisition> newRequisitionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_requisition);
        setTitle("New Requisition");

        new MyTask().execute();

        mRecyclerViewNewRequisition = findViewById(R.id.recycler_view_new_requisition);
        newRequisitionList = new ArrayList<NewRequisition>();

        adapter = new NewRequisitionAdapter(NewRequisitionActivity.this, newRequisitionList);
        mRecyclerViewNewRequisition.setAdapter(adapter);
        mRecyclerViewNewRequisition.setLayoutManager(new LinearLayoutManager(NewRequisitionActivity.this));


        fab = (FloatingActionButton) findViewById(R.id.fab_new_requisition);
        fab.setEnabled(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_new_requisition);

                //Item Spinner
                final Spinner addItemSpinner = dialog.findViewById(R.id.spinner_dialog_new_requisition_item);
                List<String> itemListSpinner = new ArrayList<String>();
                for(Item item : mItemList){
                    itemListSpinner.add(item.getDescription());
                }

                ArrayAdapter<String> itemListSpinnerAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, itemListSpinner);
                itemListSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                addItemSpinner.setAdapter(itemListSpinnerAdapter);

                //Item Quantity Spinner
                final Spinner itemQuantitySpinner = dialog.findViewById(R.id.spinner_dialog_new_requisition_item_quantity);
                List<Integer> quantitySpinner = new ArrayList<Integer>();
                for (int i = 1; i <= maxNewItemQuantity; i++){
                    quantitySpinner.add(i);
                }

                ArrayAdapter<Integer> quantitySpinnerAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, quantitySpinner);
                quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                itemQuantitySpinner.setAdapter(quantitySpinnerAdapter);


                Button submitButton = dialog.findViewById(R.id.button_dialog_new_requisition_submit);
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String itemOrdered = addItemSpinner.getSelectedItem().toString();
                        String itemUoM = null;
                        int orderedQuantity = Integer.parseInt(itemQuantitySpinner.getSelectedItem().toString());

                        for(Item item : mItemList){
                            if (item.getDescription().equals(itemOrdered)){
                                itemUoM = item.getUoM();
                            }
                        }

                        //Assuming Employee Id is E4
                        NewRequisition newRequisition = new NewRequisition("E4", itemOrdered, itemUoM, orderedQuantity);

                        for(NewRequisition existingRequisition : newRequisitionList){
                            if (existingRequisition.getItemDescription().equals(itemOrdered)){
                                Toast.makeText(getApplicationContext(), "Item has already been added", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                return;
                            }

                        }


                        newRequisitionList.add(newRequisition);

                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });



                dialog.show();


            }
        });

        Button submitRequisitionButton = findViewById(R.id.button_new_requisition_submit_requisition);
        submitRequisitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (NewRequisition newRequisition : newRequisitionList){
                    new CreateNewRequisitionTask().execute(newRequisition);
                }
            }
        });

    }


    private class MyTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... params) {
            return Item.listItems();
        }
        @Override
        protected void onPostExecute(List<Item> result) {

            mItemList = result;

            fab.setEnabled(true);

        }
    }

    private class CreateNewRequisitionTask extends AsyncTask<NewRequisition, Void, Void>{

        @Override
        protected Void doInBackground(NewRequisition... params) {
            NewRequisition.createStockAdjustment(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Toast.makeText(getApplicationContext(), "New requisition is successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NewRequisitionActivity.this, RequisitionHistoryActivity.class);
            startActivity(intent);

        }

    }






}
