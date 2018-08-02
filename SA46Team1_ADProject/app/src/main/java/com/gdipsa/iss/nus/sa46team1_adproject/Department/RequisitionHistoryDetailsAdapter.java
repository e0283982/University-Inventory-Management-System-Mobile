package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionDetail;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.util.List;

public class RequisitionHistoryDetailsAdapter extends RecyclerView.Adapter<RequisitionHistoryDetailsAdapter.RequisitionHistoryDetailsViewHolder>{

    private LayoutInflater mInflater;
    List<StaffRequisitionDetail> mStaffRequisitionDetails;

    class RequisitionHistoryDetailsViewHolder extends RecyclerView.ViewHolder{

        private TextView reqHistoryItemDescTextView;
        private TextView reqHistoryItemUoMTextView;
        private TextView reqHistoryQtyOrdTextView;
        private TextView reqHistoryQtyDelTextView;
        private TextView reqHistoryQtyBkOrdTextView;


        public RequisitionHistoryDetailsViewHolder(View itemView) {
            super(itemView);

            reqHistoryItemDescTextView = itemView.findViewById(R.id.textView_requisition_history_detail_item_description);
            reqHistoryItemUoMTextView = itemView.findViewById(R.id.textView_requisition_history_detail_item_uom);
            reqHistoryQtyOrdTextView = itemView.findViewById(R.id.textView_requisition_history_detail_quantity_ordered);
            reqHistoryQtyDelTextView = itemView.findViewById(R.id.textView_requisition_history_detail_quantity_delivered);
            reqHistoryQtyBkOrdTextView = itemView.findViewById(R.id.textView_requisition_history_detail_quantity_backordered);

        }

    }

    RequisitionHistoryDetailsAdapter(Context context, List<StaffRequisitionDetail> mStaffRequisitionDetails){
        mInflater = LayoutInflater.from(context);
        this.mStaffRequisitionDetails = mStaffRequisitionDetails;
    }


    @NonNull
    @Override
    public RequisitionHistoryDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_requisition_history_detail, parent, false);
        return new RequisitionHistoryDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequisitionHistoryDetailsViewHolder holder, int position) {

        StaffRequisitionDetail current = mStaffRequisitionDetails.get(position);

        holder.reqHistoryItemDescTextView.setText(current.getItemDescription());
        holder.reqHistoryItemUoMTextView.setText(current.getItemUoM());
        holder.reqHistoryQtyOrdTextView.setText("" + current.getQtyOrdered());
        holder.reqHistoryQtyDelTextView.setText("" + current.getQtyDelivered());
        holder.reqHistoryQtyBkOrdTextView.setText("" + current.getQtyBackOrdered());

    }

    @Override
    public int getItemCount() {
        if (mStaffRequisitionDetails != null)
            return mStaffRequisitionDetails.size();
        else return 0;
    }

}
