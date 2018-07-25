package com.gdipsa.iss.nus.sa46team1_adproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.ArrayList;
import java.util.List;

public class DescriptionSearchActivity extends AppBaseActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerViewSearchItemDescription;
    private DescriptionSearchAdapter adapter;
    SearchView editsearch;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_search);
        setTitle("Search");

        mRecyclerViewSearchItemDescription = findViewById(R.id.recycler_view_search_description_store);
        progressBar = findViewById(R.id.progressbar_description_search);

        editsearch = findViewById(R.id.search_item_by_description_store);
        editsearch.setOnQueryTextListener(this);

        new MyTask().execute();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        new MyTask().execute();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    private class MyTask extends AsyncTask<Void, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(Void... params) {
            return Item.listItems();
        }
        @Override
        protected void onPostExecute(List<Item> result) {

            progressBar.setVisibility(View.GONE);
            adapter = new DescriptionSearchAdapter(DescriptionSearchActivity.this, result);
            mRecyclerViewSearchItemDescription.setAdapter(adapter);
            mRecyclerViewSearchItemDescription.setLayoutManager(new LinearLayoutManager(DescriptionSearchActivity.this));


        }
    }

}
