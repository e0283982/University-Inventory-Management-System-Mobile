package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.CollectionPoint;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;

import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class DisbursementListDepartmentActivity extends AppBaseActivity {

    private RecyclerView mRecyclerViewDisbursementListDepartment;
    private DisbursementListDepartmentAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list_department);

        mRecyclerViewDisbursementListDepartment = findViewById(R.id.recycler_view_disbursement_list_department);
        progressBar = findViewById(R.id.progressbar_disbursement_list_department);

        Intent data = getIntent();
        String collectionPoint = data.getStringExtra("CollectionPointDescription");

        setTitle(collectionPoint);

        new MyTask().execute(collectionPoint);

    }

    private class MyTask extends AsyncTask<String, Void, List<DisbursementList>> {
        @Override
        protected List<DisbursementList> doInBackground(String... params) {
            return DisbursementList.listDepartmentDisbursementList(params[0]);
        }
        @Override
        protected void onPostExecute(List<DisbursementList> result) {

            if (result.size() == 0){
                TextView emptyTextView = findViewById(R.id.textView_disbursement_list_empty);
                emptyTextView.setVisibility(View.VISIBLE);
            }

            progressBar.setVisibility(View.GONE);
            adapter = new DisbursementListDepartmentAdapter(DisbursementListDepartmentActivity.this, result);
            mRecyclerViewDisbursementListDepartment.setAdapter(adapter);
            mRecyclerViewDisbursementListDepartment.setLayoutManager(new LinearLayoutManager(DisbursementListDepartmentActivity.this));

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }
}
