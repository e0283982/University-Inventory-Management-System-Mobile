package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementListDetail;

import java.util.List;

public class DisbursementListDetailsActivity extends AppBaseActivity {

    private RecyclerView mRecyclerViewDisbursementListDetails;
    private DisbursementListDetailsAdapter adapter;
    private ProgressBar progressBar;

    private List<DisbursementListDetail> disbursementDetailsList;

    public static final int CAPTURE_QRCODE_CONFIRM_DELIVERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list_details);

        mRecyclerViewDisbursementListDetails = findViewById(R.id.recycler_view_disbursement_list_detail);
        progressBar = findViewById(R.id.progressbar_disbursement_list_detail);

        Intent data = getIntent();
        final String disbursementId = data.getStringExtra("DisbursementId");
        String disbursementDate = data.getStringExtra("DisbursementDate");
        final String disbursementDepartment = data.getStringExtra("DisbursementDepartment");
        final String disbursementRepName = data.getStringExtra("DisbursementRepName");

        setTitle(disbursementId);

        TextView disbursementListDetailDate = findViewById(R.id.textView_disbursement_list_detail_date);
        TextView disbursementListDetailDepName = findViewById(R.id.textView_disbursement_list_detail_department_name);
        TextView disbursementListDetailRepName = findViewById(R.id.textView_disbursement_list_detail_rep_name);

        Button confirmDeliveryButton = findViewById(R.id.button_disbursement_list_detail_confirm_delivery);

        disbursementListDetailDate.setText(disbursementDate);
        disbursementListDetailDepName.setText(disbursementDepartment);
        disbursementListDetailRepName.setText(disbursementRepName);

        confirmDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DisbursementListDetailsActivity.this, QRConfirmDeliveryActivity.class);
                intent.putExtra("DisbursementId", disbursementId);
                intent.putExtra("DisbursementRepName", disbursementRepName);
                startActivityForResult(intent, CAPTURE_QRCODE_CONFIRM_DELIVERY);

            }
        });

        new MyTask().execute(disbursementId);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_QRCODE_CONFIRM_DELIVERY) {
            if (resultCode == RESULT_OK) {

                if (data.getStringExtra("QRStatus").equals("Success")){

                    Toast.makeText(getApplicationContext(), "Validation Success", Toast.LENGTH_SHORT).show();

                    for(DisbursementListDetail dld : disbursementDetailsList){
                        if (dld.getQtyAdjusted() > 0){
                            dld.setDisbursementIdAndroid(1);
                        }

                        break;
                    }

                    for(int i = 0; i < disbursementDetailsList.size(); i++){
                        DisbursementListDetail disbursementListDetail = disbursementDetailsList.get(i);
                        new UpdateDisbursementTask().execute(disbursementListDetail);
                    }

//                    for(DisbursementListDetail dld : disbursementDetailsList){
//                        new UpdateDisbursementTask().execute(dld);
//                    }

                } else{
                    Toast.makeText(getApplicationContext(), "Validation Failure", Toast.LENGTH_SHORT).show();
                }

            }


        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Capture QR Code failed",
                    Toast.LENGTH_LONG).show();
        }


    }



    private class MyTask extends AsyncTask<String, Void, List<DisbursementListDetail>> {
        @Override
        protected List<DisbursementListDetail> doInBackground(String... params) {
            return DisbursementListDetail.listDisbursementDetails(params[0]);
        }
        @Override
        protected void onPostExecute(List<DisbursementListDetail> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new DisbursementListDetailsAdapter(DisbursementListDetailsActivity.this, result);
            mRecyclerViewDisbursementListDetails.setAdapter(adapter);
            mRecyclerViewDisbursementListDetails.setLayoutManager(new LinearLayoutManager(DisbursementListDetailsActivity.this));

            disbursementDetailsList = result;

        }
    }

    private class UpdateDisbursementTask extends AsyncTask<DisbursementListDetail, Void, Void>{

        @Override
        protected Void doInBackground(DisbursementListDetail... params) {
            DisbursementListDetail.updateDisbursementDetail(params[0], DisbursementListDetailsActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            Toast.makeText(getApplicationContext(), "Delivery successful", Toast.LENGTH_SHORT).show();

            finish();


        }


    }
















}
