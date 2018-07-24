package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DescriptionSearchAdapter extends RecyclerView.Adapter<DescriptionSearchAdapter.DescriptionSearchViewHolder> {

    private LayoutInflater mInflater;

    private List<Item> mItems;
    private ArrayList<Item> mItemsList = new ArrayList<Item>();

    class DescriptionSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView searchItemTextView;

        private String itemCode;
        private String category;
        private String description;
        private int quantity;
        private String UoM;
        private String supplier1;
        private String supplier2;
        private String supplier3;
        private int active;


        public DescriptionSearchViewHolder(View itemView) {
            super(itemView);

            searchItemTextView = itemView.findViewById(R.id.textView_search_item_description_store);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            Intent intent = new Intent(context, SearchDetailsActivity.class);

            intent.putExtra("ItemCode", itemCode);
            intent.putExtra("Category", category);
            intent.putExtra("Description", description);
            intent.putExtra("Quantity", quantity);
            intent.putExtra("UoM", UoM);
            intent.putExtra("Supplier1", supplier1);
            intent.putExtra("Supplier2", supplier2);
            intent.putExtra("Supplier3", supplier3);
            intent.putExtra("Active", active);

            ((Activity) context).startActivity(intent);

        }



    }

    DescriptionSearchAdapter(Context context, List<Item> mItems){
        mInflater = LayoutInflater.from(context);
        this.mItems = mItems;
        this.mItemsList.addAll(mItems);

    }

    @NonNull
    @Override
    public DescriptionSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_search_description, parent, false);
        return new DescriptionSearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionSearchViewHolder holder, int position) {

        Item current = mItems.get(position);
        holder.searchItemTextView.setText("Item: " + current.getDescription());

        holder.itemCode = current.getItemCode();
        holder.category = current.getCategory();
        holder.description = current.getDescription();
        holder.quantity = current.getQuantity();
        holder.UoM = current.getUoM();
        holder.supplier1 = current.getSupplier1();
        holder.supplier2 = current.getSupplier2();
        holder.supplier3 = current.getSupplier3();
        holder.active = current.getActive();


    }

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mItems.clear();
        if (charText.length() == 0) {
            mItems.addAll(mItemsList);
        } else {
            for (Item i : mItemsList) {
                if (i.getDescription().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mItems.add(i);
                }
            }
        }

        notifyDataSetChanged();
    }




}
