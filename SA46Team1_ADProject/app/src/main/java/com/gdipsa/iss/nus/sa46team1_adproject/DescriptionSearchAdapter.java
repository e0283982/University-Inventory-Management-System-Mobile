package com.gdipsa.iss.nus.sa46team1_adproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;

import java.util.List;

public class DescriptionSearchAdapter extends RecyclerView.Adapter<DescriptionSearchAdapter.DescriptionSearchViewHolder> {

    private LayoutInflater mInflater;

    List<Item> mItems;

    class DescriptionSearchViewHolder extends RecyclerView.ViewHolder{

        private TextView searchItemTextView;


        public DescriptionSearchViewHolder(View itemView) {
            super(itemView);

            searchItemTextView = itemView.findViewById(R.id.textView_search_item_description_store);

        }
    }






}
