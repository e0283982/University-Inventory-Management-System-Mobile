package com.gdipsa.iss.nus.sa46team1_adproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;

import java.util.ArrayList;
import java.util.List;

public class DescriptionSearchActivity extends AppBaseActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerViewSearchItemDescription;
    private DescriptionSearchAdapter adapter;
    SearchView editsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_search);
        setTitle("Search");

        mRecyclerViewSearchItemDescription = findViewById(R.id.recycler_view_search_description_store);

        //Temporary put in data first
        List<Item> itemList = new ArrayList<Item>();

        Item item1 = new Item("Test1");
        Item item2 = new Item("Test2");
        Item item3 = new Item("Test3");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        adapter = new DescriptionSearchAdapter(DescriptionSearchActivity.this, itemList);
        mRecyclerViewSearchItemDescription.setAdapter(adapter);
        mRecyclerViewSearchItemDescription.setLayoutManager(new LinearLayoutManager(DescriptionSearchActivity.this));

        editsearch = findViewById(R.id.search_item_by_description_store);
        editsearch.setOnQueryTextListener(this);


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



}
