package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.CollectionPoint;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;

public class DisbursementListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewDisbursementList;
    private DisbursementListAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disbursement_list);
        setTitle("Disbursement List");

        mRecyclerViewDisbursementList = findViewById(R.id.recycler_view_disbursement_list_collection_point);
        progressBar = findViewById(R.id.progressbar_disbursement_list_collection_point);

        new MyTask().execute();

    }

    private class MyTask extends AsyncTask<Void, Void, List<CollectionPoint>> {
        @Override
        protected List<CollectionPoint> doInBackground(Void... params) {
            return CollectionPoint.listCollectionPoints();
        }
        @Override
        protected void onPostExecute(List<CollectionPoint> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new DisbursementListAdapter(DisbursementListActivity.this, result);
            mRecyclerViewDisbursementList.setAdapter(adapter);
            mRecyclerViewDisbursementList.setLayoutManager(new LinearLayoutManager(DisbursementListActivity.this));

        }
    }



}
