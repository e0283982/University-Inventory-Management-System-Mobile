package com.gdipsa.iss.nus.sa46team1_adproject.Department;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RequisitionHistoryAdapter extends RecyclerView.Adapter<RequisitionHistoryAdapter.RequisitionHistoryViewHolder>{

    private LayoutInflater mInflater;
    List<StaffRequisitionHeader> mStaffRequisitionHeaderList;

    class RequisitionHistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView requisitionHistoryId;
        private TextView requisitionHistoryDateRequested;
        private TextView requisitionHistoryApprovalStatus;
        private TextView requisitionHistoryStatus;

        private String reqFormId;

        public RequisitionHistoryViewHolder(View itemView) {
            super(itemView);

            requisitionHistoryId = itemView.findViewById(R.id.textView_requisition_history_requisition_id);
            requisitionHistoryDateRequested = itemView.findViewById(R.id.textView_requisition_history_requisition_date_requested);
            requisitionHistoryApprovalStatus = itemView.findViewById(R.id.textView_requisition_history_approval_status);
            requisitionHistoryStatus = itemView.findViewById(R.id.textView_requisition_history_status);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, RequisitionHistoryDetailsActivity.class);
            intent.putExtra("RequisitionFormId", reqFormId);
            ((Activity) context).startActivity(intent);
        }
    }

    RequisitionHistoryAdapter(Context context, List<StaffRequisitionHeader> mStaffRequisitionHeaderList){
        mInflater = LayoutInflater.from(context);
        this.mStaffRequisitionHeaderList = mStaffRequisitionHeaderList;
    }

    @NonNull
    @Override
    public RequisitionHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_requisition_history, parent, false);
        return new RequisitionHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequisitionHistoryViewHolder holder, int position) {

        StaffRequisitionHeader current = mStaffRequisitionHeaderList.get(position);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try{
            date = formatter2.parse(current.getDateRequestedStr().substring(0, 10));
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.requisitionHistoryId.setText(current.getRequisitionFormId());
        holder.requisitionHistoryDateRequested.setText(formatter.format(date));
        holder.requisitionHistoryApprovalStatus.setText(current.getApprovalStatus());
        holder.requisitionHistoryStatus.setText(current.getStatus());

        holder.reqFormId = current.getRequisitionFormId();

    }

    @Override
    public int getItemCount() {
        if (mStaffRequisitionHeaderList != null)
            return mStaffRequisitionHeaderList.size();
        else return 0;
    }

}
