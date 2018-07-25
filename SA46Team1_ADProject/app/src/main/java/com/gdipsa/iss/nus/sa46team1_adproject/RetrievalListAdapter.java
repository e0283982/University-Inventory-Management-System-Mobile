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

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;

public class RetrievalListAdapter extends RecyclerView.Adapter<RetrievalListAdapter.RetrievalListViewHolder>{

    private LayoutInflater mInflater;

    List<StockRetrieval> mRetrievalList;

    class RetrievalListViewHolder extends RecyclerView.ViewHolder{

        private TextView binNumberTextView;
        private TextView itemDescriptionTextView;
        private TextView itemsRetrievedTextView;
        private TextView collectionPointTextView;
        private Button adjustButton;

        private String itemAdjusted;
        private int quantityRetrieved;

        public RetrievalListViewHolder(final View itemView){
            super(itemView);

            binNumberTextView = itemView.findViewById(R.id.textView_binNumber);
            itemDescriptionTextView = itemView.findViewById(R.id.textView_itemDescription);
            itemsRetrievedTextView = itemView.findViewById(R.id.textView_itemsRetrieved);
            collectionPointTextView = itemView.findViewById(R.id.textView_collectionPoint);
            adjustButton = itemView.findViewById(R.id.button_retrieval_stock_adjustment);

            adjustButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, StockAdjustmentActivity.class);
                    intent.putExtra("ItemAdjusted", itemAdjusted);
                    intent.putExtra("QuantityRetrieved", quantityRetrieved);
                    ((Activity) context).startActivity(intent);

                }
            });

        }

    }

    RetrievalListAdapter(Context context,List<StockRetrieval> mRetrievalList){
        mInflater = LayoutInflater.from(context);
        this.mRetrievalList = mRetrievalList;
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
