package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.List;

public class RequisitionHistoryActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewRequisitionHistory;
    private RequisitionHistoryAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history);
        setTitle("Requisition History");

        mRecyclerViewRequisitionHistory = findViewById(R.id.recycler_view_requisition_history);
        progressBar = findViewById(R.id.progressbar_requisition_history);

        //TODO: Temporary putting the login id as E4
        new MyTask().execute("E4");

    }

    private class MyTask extends AsyncTask<String, Void, List<StaffRequisitionHeader>> {
        @Override
        protected List<StaffRequisitionHeader> doInBackground(String... params) {
            return StaffRequisitionHeader.listStaffRequisitionHeader(params[0]);
        }
        @Override
        protected void onPostExecute(List<StaffRequisitionHeader> result) {

            if (result.size() == 0){
                TextView emptyTextView = findViewById(R.id.textView_requisition_history_empty);
                emptyTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
            adapter = new RequisitionHistoryAdapter(RequisitionHistoryActivity.this, result);
            mRecyclerViewRequisitionHistory.setAdapter(adapter);
            mRecyclerViewRequisitionHistory.setLayoutManager(new LinearLayoutManager(RequisitionHistoryActivity.this));

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
