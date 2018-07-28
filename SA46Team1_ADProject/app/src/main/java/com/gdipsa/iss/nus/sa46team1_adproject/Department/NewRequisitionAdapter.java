package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.NewRequisition;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewRequisitionAdapter extends RecyclerView.Adapter<NewRequisitionAdapter.NewRequisitionViewHolder> {

    private LayoutInflater mInflater;
    List<NewRequisition> mNewRequisitionList;

    class NewRequisitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView newRequisitionItemDescription;
        private TextView newRequisitionItemUoM;
        private TextView newRequisitionOrderQty;

        public NewRequisitionViewHolder(View itemView) {
            super(itemView);

            newRequisitionItemDescription = itemView.findViewById(R.id.textView_new_requisition_item_description);
            newRequisitionItemUoM = itemView.findViewById(R.id.textView_new_requisition_item_uom);
            newRequisitionOrderQty = itemView.findViewById(R.id.textView_new_requisition_order_qty);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //TODO: to allow edit
        }


    }


    NewRequisitionAdapter(Context context, List<NewRequisition> mNewRequisitionList){
        mInflater = LayoutInflater.from(context);
        this.mNewRequisitionList = mNewRequisitionList;
    }


    @NonNull
    @Override
    public NewRequisitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_new_requisition, parent, false);
        return new NewRequisitionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewRequisitionViewHolder holder, int position) {

        NewRequisition current = mNewRequisitionList.get(position);

        holder.newRequisitionItemDescription.setText(current.getItemDescription());
        holder.newRequisitionItemUoM.setText(current.getItemUoM());
        holder.newRequisitionOrderQty.setText("" + current.getOrderQty());


    }

    @Override
    public int getItemCount() {
        if (mNewRequisitionList != null)
            return mNewRequisitionList.size();
        else return 0;
    }






}
