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

import com.gdipsa.iss.nus.sa46team1_adproject.Data.CollectionPoint;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StockRetrieval;

import java.util.List;

public class DisbursementListAdapter extends RecyclerView.Adapter<DisbursementListAdapter.DisbursementListViewHolder>{

    private LayoutInflater mInflater;
    List<CollectionPoint> mCollectionPointList;

    class DisbursementListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView collectionPointIdTextView;
        private TextView collectionPointDescriptionTextView;
        private TextView collectionPointTimeTextView;

        private String collectionPointDescription;


        public DisbursementListViewHolder(View itemView) {
            super(itemView);

            collectionPointIdTextView = itemView.findViewById(R.id.textView_collection_point_id);
            collectionPointDescriptionTextView = itemView.findViewById(R.id.textView_collection_point_description);
            collectionPointTimeTextView = itemView.findViewById(R.id.textView_collection_point_collection_time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            Intent intent = new Intent(context, DisbursementListDepartmentActivity.class);
            intent.putExtra("CollectionPointDescription", collectionPointDescription);

            ((Activity) context).startActivity(intent);


        }
    }

    DisbursementListAdapter(Context context, List<CollectionPoint> mCollectionPointList){
        mInflater = LayoutInflater.from(context);
        this.mCollectionPointList = mCollectionPointList;
    }

    @NonNull
    @Override
    public DisbursementListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_collection_point, parent, false);
        return new DisbursementListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DisbursementListViewHolder holder, int position) {

        CollectionPoint current = mCollectionPointList.get(position);

        holder.collectionPointDescription = current.getCollectionPointDescription();

        holder.collectionPointIdTextView.setText(current.getCollectionPointId());
        holder.collectionPointDescriptionTextView.setText(current.getCollectionPointDescription());
        holder.collectionPointTimeTextView.setText("Time: " + current.getCollectionPointTime());

    }

    @Override
    public int getItemCount() {
        if (mCollectionPointList != null)
            return mCollectionPointList.size();
        else return 0;
    }




}
