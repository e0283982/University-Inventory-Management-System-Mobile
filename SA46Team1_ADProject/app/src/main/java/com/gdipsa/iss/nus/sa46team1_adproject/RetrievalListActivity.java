package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;


public class RetrievalListActivity extends AppBaseActivity {

    private RecyclerView mRecyclerViewStockRetrievalList;
    private RetrievalListAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);
        setTitle("Retrieval List");

        mRecyclerViewStockRetrievalList = findViewById(R.id.recycler_view_retrieval_list);
        progressBar = findViewById(R.id.progressbar_retrieval_list);

        new MyTask().execute("Test");

    }

    private class MyTask extends AsyncTask<String, Void, List<StockRetrieval>>{
        @Override
        protected List<StockRetrieval> doInBackground(String... params) {
            return StockRetrieval.listStockRetrievals(params[0]);
        }
        @Override
        protected void onPostExecute(List<StockRetrieval> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new RetrievalListAdapter(RetrievalListActivity.this, result);
            mRecyclerViewStockRetrievalList.setAdapter(adapter);
            mRecyclerViewStockRetrievalList.setLayoutManager(new LinearLayoutManager(RetrievalListActivity.this));



        }
    }





}
