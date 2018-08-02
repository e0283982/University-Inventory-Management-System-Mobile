package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.ArrayList;
import java.util.List;

public class RequisitionHistoryActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewRequisitionHistory;
    private RequisitionHistoryAdapter adapter;
    private ProgressBar progressBar;
    private Spinner filterSpinner;

    private List<StaffRequisitionHeader> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history);
        setTitle("Requisition History");

        mRecyclerViewRequisitionHistory = findViewById(R.id.recycler_view_requisition_history);
        progressBar = findViewById(R.id.progressbar_requisition_history);

        Intent data = getIntent();

        //This is the case of normal user
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String requestorId = pref.getString("EmployeeID", "Employee ID");
        new MyTask().execute(requestorId);

        filterSpinner = findViewById(R.id.spinner_requisition_history_status_filter);
    }

    private class MyTask extends AsyncTask<String, Void, List<StaffRequisitionHeader>> {
        @Override
        protected List<StaffRequisitionHeader> doInBackground(String... params) {
            return StaffRequisitionHeader.listStaffRequisitionHeader(params[0]);
        }
        @Override
        protected void onPostExecute(List<StaffRequisitionHeader> result) {

            resultList = result;

            if (result.size() == 0){
                TextView emptyTextView = findViewById(R.id.textView_requisition_history_empty);
                emptyTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
            adapter = new RequisitionHistoryAdapter(RequisitionHistoryActivity.this, result);
            mRecyclerViewRequisitionHistory.setAdapter(adapter);
            mRecyclerViewRequisitionHistory.setLayoutManager(new LinearLayoutManager(RequisitionHistoryActivity.this));

            filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedItem = parent.getItemAtPosition(position).toString();

                    List<StaffRequisitionHeader> filterResult = new ArrayList<StaffRequisitionHeader>();

                    for(StaffRequisitionHeader staffRequisitionHeader : resultList){

                        if (staffRequisitionHeader.getStatus().equals(selectedItem)){
                            filterResult.add(staffRequisitionHeader);
                        }

                    }

                    if (selectedItem.equals("All")){
                        filterResult = resultList;
                    }

                    adapter = new RequisitionHistoryAdapter(RequisitionHistoryActivity.this, filterResult);
                    mRecyclerViewRequisitionHistory.setAdapter(adapter);
                    mRecyclerViewRequisitionHistory.setLayoutManager(new LinearLayoutManager(RequisitionHistoryActivity.this));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

}
