package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class DepartmentRepRequisitionHistoryActivity extends AppBaseDepartmentActivity {

    private RecyclerView mRecyclerViewRequisitionHistory;
    private RequisitionHistoryAdapter adapter;
    private ProgressBar progressBar;
    private Spinner filterSpinner;

    private List<StaffRequisitionHeader> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisition_history);

        setTitle("Department Requisition History");

        mRecyclerViewRequisitionHistory = findViewById(R.id.recycler_view_requisition_history);
        progressBar = findViewById(R.id.progressbar_requisition_history);

        //This is the case of dep req history
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String requestorDepartmentCode = pref.getString("DepartmentCode", "Department Code");
        new MyDepRepTask().execute(requestorDepartmentCode);

        filterSpinner = findViewById(R.id.spinner_requisition_history_status_filter);

    }

    private class MyDepRepTask extends AsyncTask<String, Void, List<StaffRequisitionHeader>> {
        @Override
        protected List<StaffRequisitionHeader> doInBackground(String... params) {
            return StaffRequisitionHeader.listStaffRequisitionHeaderDepartmentRep(params[0]);
        }
        @Override
        protected void onPostExecute(List<StaffRequisitionHeader> result) {

            resultList = result;

            if (result.size() == 0){
                TextView emptyTextView = findViewById(R.id.textView_requisition_history_empty);
                emptyTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
            adapter = new RequisitionHistoryAdapter(DepartmentRepRequisitionHistoryActivity.this, result);
            mRecyclerViewRequisitionHistory.setAdapter(adapter);
            mRecyclerViewRequisitionHistory.setLayoutManager(new LinearLayoutManager(DepartmentRepRequisitionHistoryActivity.this));

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

                    adapter = new RequisitionHistoryAdapter(DepartmentRepRequisitionHistoryActivity.this, filterResult);
                    mRecyclerViewRequisitionHistory.setAdapter(adapter);
                    mRecyclerViewRequisitionHistory.setLayoutManager(new LinearLayoutManager(DepartmentRepRequisitionHistoryActivity.this));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

}
