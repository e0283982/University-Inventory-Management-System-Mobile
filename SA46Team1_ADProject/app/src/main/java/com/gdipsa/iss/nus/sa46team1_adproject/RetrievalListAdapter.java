package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;

public class RetrievalListAdapter extends RecyclerView.Adapter<RetrievalListAdapter.RetrievalListViewHolder>{

    private LayoutInflater mInflater;
    List<StockRetrieval> mRetrievalList;
    private String stockRetrievalId;

    class RetrievalListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView binNumberTextView;
        private TextView binLocationTextView;
        private TextView itemDescriptionTextView;
        private TextView itemsRetrievedTextView;
        private TextView collectionPointTextView;

        private String itemAdjusted;
        private int quantityRetrieved;

        public RetrievalListViewHolder(final View itemView){
            super(itemView);

            binNumberTextView = itemView.findViewById(R.id.textView_retrieval_binNumber);
            binLocationTextView = itemView.findViewById(R.id.textView_retrieval_binLocation);
            itemDescriptionTextView = itemView.findViewById(R.id.textView_retrieval_itemDescription);
            itemsRetrievedTextView = itemView.findViewById(R.id.textView_retrieval_itemsRetrieved);
            collectionPointTextView = itemView.findViewById(R.id.textView_retrieval_collectionPoint);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();

            if (quantityRetrieved <= 0){
                Toast.makeText(v.getContext(), "Invalid stock adjustment as quantity retrieved is 0", Toast.LENGTH_SHORT).show();
                return;
            }


            Intent intent = new Intent(context, StockAdjustmentActivity.class);
            intent.putExtra("ItemAdjusted", itemAdjusted);
            intent.putExtra("QuantityRetrieved", quantityRetrieved);
            intent.putExtra("StockRetrievalId", stockRetrievalId);
            ((Activity) context).startActivity(intent);
        }
    }

    RetrievalListAdapter(Context context, List<StockRetrieval> mRetrievalList, String stockRetrievalId){
        mInflater = LayoutInflater.from(context);
        this.mRetrievalList = mRetrievalList;
        this.stockRetrievalId = stockRetrievalId;
    }

    @NonNull
    @Override
    public RetrievalListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_retrieval, parent, false);
        return new RetrievalListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrievalListViewHolder holder, int position) {

        StockRetrieval current = mRetrievalList.get(position);
        holder.binNumberTextView.setText("Bin #" + current.getBinNumber());
        holder.binLocationTextView.setText("Location: " + current.getBinLocation());
        holder.itemDescriptionTextView.setText(current.getItemDescription());
        holder.itemsRetrievedTextView.setText("Quantity Retrieved: " + current.getItemsRetrieved());
        holder.collectionPointTextView.setText("Collection Point: " + current.getCollectionPointDescription());

        holder.itemAdjusted = current.getItemDescription();
        holder.quantityRetrieved = current.getItemsRetrieved();

    }

    @Override
    public int getItemCount() {
        if (mRetrievalList != null)
            return mRetrievalList.size();
        else return 0;
    }



}
