package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementListDetail;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionDetail;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class RequisitionHistoryDetailsActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewRequisitionHistoryDetails;
    private RequisitionHistoryDetailsAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history_details);

        mRecyclerViewRequisitionHistoryDetails = findViewById(R.id.recycler_view_requisition_history_detail);
        progressBar = findViewById(R.id.progressbar_requisition_history_detail);

        Intent data = getIntent();
        String reqFormId = data.getStringExtra("RequisitionFormId");

        setTitle(reqFormId);

        new MyTask().execute(reqFormId);

    }


    private class MyTask extends AsyncTask<String, Void, List<StaffRequisitionDetail>> {
        @Override
        protected List<StaffRequisitionDetail> doInBackground(String... params) {
            return StaffRequisitionDetail.listStaffRequisitionDetail(params[0]);
        }
        @Override
        protected void onPostExecute(List<StaffRequisitionDetail> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new RequisitionHistoryDetailsAdapter(RequisitionHistoryDetailsActivity.this, result);
            mRecyclerViewRequisitionHistoryDetails.setAdapter(adapter);
            mRecyclerViewRequisitionHistoryDetails.setLayoutManager(new LinearLayoutManager(RequisitionHistoryDetailsActivity.this));

        }
    }


}
