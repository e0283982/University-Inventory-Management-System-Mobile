package com.gdipsa.iss.nus.sa46team1_adproject;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementList;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.DisbursementListDetail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisbursementListDetailsAdapter extends RecyclerView.Adapter<DisbursementListDetailsAdapter.DisbursementListDetailsViewHolder>{

    private LayoutInflater mInflater;
    List<DisbursementListDetail> mDisbursementListDetails;

    class DisbursementListDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView disbursementDetailItemDescTextView;
        private TextView disbursementDetailUoMTextView;
        private TextView disbursementDetailQtyOrdTextView;
        private TextView disbursementDetailQtyRecTextView;

        private String itemDescription;
        private int qtyReceived;


        public DisbursementListDetailsViewHolder(View itemView) {
            super(itemView);

            disbursementDetailItemDescTextView = itemView.findViewById(R.id.textView_disbursement_detail_item_description);
            disbursementDetailUoMTextView = itemView.findViewById(R.id.textView_disbursement_detail_item_uom);
            disbursementDetailQtyOrdTextView = itemView.findViewById(R.id.textView_disbursement_detail_quantity_ordered);
            disbursementDetailQtyRecTextView= itemView.findViewById(R.id.textView_disbursement_detail_quantity_received);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_adjust_quantity_received_disbursement);

            TextView itemDescriptionTextView = dialog.findViewById(R.id.textView_dialog_adjust_qty_received_item_description);
            itemDescriptionTextView.setText(itemDescription);

            Button submitButton = dialog.findViewById(R.id.button_dialog_adjust_qty_received_submit);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    dialog.dismiss();
                }
            });

            Spinner adjustSpinner = dialog.findViewById(R.id.spinner_dialog_adjust_qty_received);
            List<Integer> quantitySpinner = new ArrayList<Integer>();
            for (int i = 1; i <= qtyReceived; i++){
                quantitySpinner.add(i);
            }

            ArrayAdapter<Integer> quantitySpinnerAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, quantitySpinner);

            quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            adjustSpinner.setAdapter(quantitySpinnerAdapter);

            dialog.show();

        }
    }

    DisbursementListDetailsAdapter(Context context, List<DisbursementListDetail> mDisbursementListDetails){
        mInflater = LayoutInflater.from(context);
        this.mDisbursementListDetails = mDisbursementListDetails;
    }


    @NonNull
    @Override
    public DisbursementListDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_disbursement_detail, parent, false);
        return new DisbursementListDetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DisbursementListDetailsViewHolder holder, int position) {

        DisbursementListDetail current = mDisbursementListDetails.get(position);

        holder.disbursementDetailItemDescTextView.setText(current.getItemDescription());
        holder.disbursementDetailUoMTextView.setText(current.getItemUoM());
        holder.disbursementDetailQtyOrdTextView.setText("" + current.getQtyOrdered());
        holder.disbursementDetailQtyRecTextView.setText("" + current.getQtyReceived());

        holder.itemDescription = current.getItemDescription();
        holder.qtyReceived = current.getQtyReceived();

    }

    @Override
    public int getItemCount() {
        if (mDisbursementListDetails != null)
            return mDisbursementListDetails.size();
        else return 0;
    }




}
