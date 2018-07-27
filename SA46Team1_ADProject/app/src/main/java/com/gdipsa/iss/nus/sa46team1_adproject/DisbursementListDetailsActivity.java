package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementListDetail;

import java.util.List;

public class DisbursementListDetailsActivity extends AppBaseActivity {

    private RecyclerView mRecyclerViewDisbursementListDetails;
    private DisbursementListDetailsAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list_details);

        mRecyclerViewDisbursementListDetails = findViewById(R.id.recycler_view_disbursement_list_detail);
        progressBar = findViewById(R.id.progressbar_disbursement_list_detail);

        Intent data = getIntent();
        final String disbursementId = data.getStringExtra("DisbursementId");
        String disbursementDate = data.getStringExtra("DisbursementDate");
        String disbursementDepartment = data.getStringExtra("DisbursementDepartment");
        String disbursementRepName = data.getStringExtra("DisbursementRepName");

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
                startActivity(intent);

            }
        });

        new MyTask().execute(disbursementId);

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

        }
    }

















}
