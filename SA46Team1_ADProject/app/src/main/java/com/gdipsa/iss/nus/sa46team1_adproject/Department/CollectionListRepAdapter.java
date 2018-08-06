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
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDepartmentAdapter;
import com.gdipsa.iss.nus.sa46team1_adproject.DisbursementListDetailsActivity;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class CollectionListRepAdapter extends RecyclerView.Adapter<CollectionListRepAdapter.CollectionListRepViewHolder>{

    private LayoutInflater mInflater;
    List<DisbursementList> mDisbursementList;

    class CollectionListRepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView dateDisbursementDepartmentTextView;
        private TextView disbursementDepartmentDescriptionTextView;
        private TextView disbursementIdTextView;
        private TextView disbursementDeptRepTextView;

        private String disbursementId;
        private String disbursementDate;
        private String disbursementDepartment;
        private String disbursementRepName;

        public CollectionListRepViewHolder(View itemView) {
            super(itemView);

            disbursementIdTextView = itemView.findViewById(R.id.textView_disbursement_id);
            dateDisbursementDepartmentTextView = itemView.findViewById(R.id.textView_disbursement_department_date);
            disbursementDepartmentDescriptionTextView = itemView.findViewById(R.id.textView_disbursement_department_description);
            disbursementDeptRepTextView = itemView.findViewById(R.id.textView_disbursement_department_rep_name);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            Intent intent = new Intent(context, CollectionListRepDetailsActivity.class);
            intent.putExtra("DisbursementId", disbursementId);
            intent.putExtra("DisbursementDate", disbursementDate);
            intent.putExtra("DisbursementDepartment", disbursementDepartment);
            intent.putExtra("DisbursementRepName", disbursementRepName);

            ((Activity) context).startActivity(intent);

        }

    }

    public CollectionListRepAdapter(Context context, List<DisbursementList> mDisbursementList){
        mInflater = LayoutInflater.from(context);
        this.mDisbursementList = mDisbursementList;
    }


    @NonNull
    @Override
    public CollectionListRepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_disbursement_department, parent, false);
        return new CollectionListRepAdapter.CollectionListRepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionListRepViewHolder holder, int position) {

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

        holder.disbursementId = current.getDisbursementId();
        holder.disbursementDate = formatter.format(date);
        holder.disbursementDepartment = current.getDepartmentName();
        holder.disbursementRepName = current.getRepresentativeName();

    }

    @Override
    public int getItemCount() {
        if (mDisbursementList != null)
            return mDisbursementList.size();
        else return 0;
    }



}

