package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;


public class RetrievalListActivity extends AppBaseActivity {

    TextView textViewItemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieval_list);

        setTitle("Retrieval List");

        textViewItemDescription = findViewById(R.id.test_retrieval_list);

        new MyTask().execute("localhost:49921");



    }

    private class MyTask extends AsyncTask<String, Void, List<StockRetrieval>>{
        @Override
        protected List<StockRetrieval> doInBackground(String... params) {
            return StockRetrieval.listStockRetrievals(params[0]);
        }
        @Override
        protected void onPostExecute(List<StockRetrieval> result) {

            textViewItemDescription.setText(result.get(0).getItemDescription());







        }
    }





}
