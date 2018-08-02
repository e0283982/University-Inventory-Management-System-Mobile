package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.ArrayList;
import java.util.List;


public class RetrievalListActivity extends AppBaseActivity {

    private RecyclerView mRecyclerViewStockRetrievalList;
    private RetrievalListAdapter adapter;
    private ProgressBar progressBar;
    private TextView retrievalListDisbursedTextView;
    private String stoRetId;
    private List<StockRetrieval> resultList;
    private Spinner filterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);
        setTitle("Retrieval List");

        mRecyclerViewStockRetrievalList = findViewById(R.id.recycler_view_retrieval_list);
        progressBar = findViewById(R.id.progressbar_retrieval_list);
        retrievalListDisbursedTextView = findViewById(R.id.textView_retrieval_list_disbursed);

        filterSpinner = findViewById(R.id.spinner_retrieval_list_collection_point_filter);
    }

    private class MyTask extends AsyncTask<String, Void, List<StockRetrieval>>{
        @Override
        protected List<StockRetrieval> doInBackground(String... params) {
            return StockRetrieval.listStockRetrievals(params[0]);
        }
        @Override
        protected void onPostExecute(List<StockRetrieval> result) {

            resultList = result;

            progressBar.setVisibility(View.GONE);
            adapter = new RetrievalListAdapter(RetrievalListActivity.this, result, stoRetId);
            mRecyclerViewStockRetrievalList.setAdapter(adapter);
            mRecyclerViewStockRetrievalList.setLayoutManager(new LinearLayoutManager(RetrievalListActivity.this));

            filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedItem = parent.getItemAtPosition(position).toString();

                    List<StockRetrieval> filterResult = new ArrayList<StockRetrieval>();

                    for(StockRetrieval stockRetrieval : resultList){

                        if (stockRetrieval.getCollectionPointDescription().equals(selectedItem)){
                            filterResult.add(stockRetrieval);
                        }

                    }

                    if (selectedItem.equals("All")){
                        filterResult = resultList;
                    }

                    adapter = new RetrievalListAdapter(RetrievalListActivity.this, filterResult, stoRetId);
                    mRecyclerViewStockRetrievalList.setAdapter(adapter);
                    mRecyclerViewStockRetrievalList.setLayoutManager(new LinearLayoutManager(RetrievalListActivity.this));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, StockRetrieval>() {
            @Override
            protected StockRetrieval doInBackground(Void... params) {
                return StockRetrieval.getLatestStockRetrievalid();
            }
            @Override
            protected void onPostExecute(StockRetrieval stockRetrieval) {

                if (stockRetrieval.getStockDisbursed() == 1){

                    retrievalListDisbursedTextView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                stoRetId = stockRetrieval.getStockRetrievalId();

                new MyTask().execute(stockRetrieval.getStockRetrievalId());
            }
        }.execute();

    }

}
