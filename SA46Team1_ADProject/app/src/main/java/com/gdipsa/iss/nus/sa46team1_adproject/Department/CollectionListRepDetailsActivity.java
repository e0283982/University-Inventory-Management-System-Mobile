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
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.List;

public class CollectionListRepDetailsActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewDisbursementListRepDetails;
    private DisbursementListDetailsAdapter adapter;
    private ProgressBar progressBar;

    private List<DisbursementListDetail> disbursementDetailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list_rep_details);

        Intent data = getIntent();
        final String disbursementId = data.getStringExtra("DisbursementId");
        String disbursementDate = data.getStringExtra("DisbursementDate");
        final String disbursementDepartment = data.getStringExtra("DisbursementDepartment");
        final String disbursementRepName = data.getStringExtra("DisbursementRepName");

        setTitle(disbursementId);

        mRecyclerViewDisbursementListRepDetails = findViewById(R.id.recycler_view_disbursement_list_rep_detail);
        progressBar = findViewById(R.id.progressbar_disbursement_list_detail_rep);

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
            adapter = new DisbursementListDetailsAdapter(CollectionListRepDetailsActivity.this, result);
            mRecyclerViewDisbursementListRepDetails.setAdapter(adapter);
            mRecyclerViewDisbursementListRepDetails.setLayoutManager(new LinearLayoutManager(CollectionListRepDetailsActivity.this));

        }
    }


}
