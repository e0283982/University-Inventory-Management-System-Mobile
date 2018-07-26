package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.CollectionPoint;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;

import java.util.List;

public class DisbursementListDepartmentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewDisbursementListDepartment;
    private DisbursementListDepartmentAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list_department);
        setTitle("Department");

        mRecyclerViewDisbursementListDepartment = findViewById(R.id.recycler_view_disbursement_list_department);
        progressBar = findViewById(R.id.progressbar_disbursement_list_department);

        new MyTask().execute();



    }

    private class MyTask extends AsyncTask<Void, Void, List<DisbursementList>> {
        @Override
        protected List<DisbursementList> doInBackground(Void... params) {
            return DisbursementList.listDepartmentDisbursementList();
        }
        @Override
        protected void onPostExecute(List<DisbursementList> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new DisbursementListDepartmentAdapter(DisbursementListDepartmentActivity.this, result);
            mRecyclerViewDisbursementListDepartment.setAdapter(adapter);
            mRecyclerViewDisbursementListDepartment.setLayoutManager(new LinearLayoutManager(DisbursementListDepartmentActivity.this));

        }
    }



}
