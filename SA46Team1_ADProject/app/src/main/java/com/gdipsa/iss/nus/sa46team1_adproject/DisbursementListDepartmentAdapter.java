package com.gdipsa.iss.nus.sa46team1_adproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.CollectionPoint;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DisbursementListDepartmentAdapter extends RecyclerView.Adapter<DisbursementListDepartmentAdapter.DisbursementListDepartmentViewHolder>{

    private LayoutInflater mInflater;
    List<DisbursementList> mDisbursementList;

    class DisbursementListDepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView dateDisbursementDepartmentTextView;
        private TextView disbursementDepartmentDescriptionTextView;
        private TextView disbursementIdTextView;
        private TextView disbursementDeptRepTextView;

        public DisbursementListDepartmentViewHolder(View itemView) {
            super(itemView);

            disbursementIdTextView = itemView.findViewById(R.id.textView_disbursement_id);
            dateDisbursementDepartmentTextView = itemView.findViewById(R.id.textView_disbursement_department_date);
            disbursementDepartmentDescriptionTextView = itemView.findViewById(R.id.textView_disbursement_department_description);
            disbursementDeptRepTextView = itemView.findViewById(R.id.textView_disbursement_department_rep_name);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }

    DisbursementListDepartmentAdapter(Context context, List<DisbursementList> mDisbursementList){
        mInflater = LayoutInflater.from(context);
        this.mDisbursementList = mDisbursementList;
    }


    @NonNull
    @Override
    public DisbursementListDepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_disbursement_department, parent, false);
        return new DisbursementListDepartmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DisbursementListDepartmentViewHolder holder, int position) {

        DisbursementList current = mDisbursementList.get(position);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;

        try{
            date = formatter2.parse(current.getDateStr().substring(0, 10));
        } catch (Exception e){
            e.printStackTrace();
        }

        holder.dateDisbursementDepartmentTextView.setText(formatter.format(date));
        holder.disbursementDepartmentDescriptionTextView.setText(current.getDepartmentName());
        holder.disbursementIdTextView.setText(current.getDisbursementId());
        holder.disbursementDeptRepTextView.setText(current.getRepresentativeName());

    }

    @Override
    public int getItemCount() {
        if (mDisbursementList != null)
            return mDisbursementList.size();
        else return 0;
    }




}
