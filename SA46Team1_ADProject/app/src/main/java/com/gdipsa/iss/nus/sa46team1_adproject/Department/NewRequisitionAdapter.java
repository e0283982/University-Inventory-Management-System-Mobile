package com.gdipsa.iss.nus.sa46team1_adproject.Department;

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
import android.widget.Toast;

import com.gdipsa.iss.nus.sa46team1_adproject.Data.NewRequisition;
import com.gdipsa.iss.nus.sa46team1_adproject.Data.StaffRequisitionHeader;
import com.gdipsa.iss.nus.sa46team1_adproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewRequisitionAdapter extends RecyclerView.Adapter<NewRequisitionAdapter.NewRequisitionViewHolder> {

    private LayoutInflater mInflater;
    List<NewRequisition> mNewRequisitionList;

    private int maxNewItemQuantity = 50;

    class NewRequisitionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView newRequisitionItemDescription;
        private TextView newRequisitionItemUoM;
        private TextView newRequisitionOrderQty;

        private String itemDescription;
        private int qtyOrdered;

        private int currentPosition;

        public NewRequisitionViewHolder(View itemView) {
            super(itemView);

            newRequisitionItemDescription = itemView.findViewById(R.id.textView_new_requisition_item_description);
            newRequisitionItemUoM = itemView.findViewById(R.id.textView_new_requisition_item_uom);
            newRequisitionOrderQty = itemView.findViewById(R.id.textView_new_requisition_order_qty);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Context context = v.getContext();

            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_edit_new_requisition);

            TextView itemDescriptionTextView = dialog.findViewById(R.id.textView_dialog_edit_new_requisition_item_description);
            itemDescriptionTextView.setText(itemDescription);

            final Spinner editSpinner = dialog.findViewById(R.id.spinner_dialog_edit_new_requisition_item_quantity);
            List<Integer> quantitySpinner = new ArrayList<Integer>();
            for (int i = 1; i <= maxNewItemQuantity; i++){
                quantitySpinner.add(i);
            }

            ArrayAdapter<Integer> quantitySpinnerAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, quantitySpinner);

            quantitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

            editSpinner.setAdapter(quantitySpinnerAdapter);

            Button submitButton = dialog.findViewById(R.id.button_dialog_edit_new_requisition_submit);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int editQuantity = Integer.parseInt(editSpinner.getSelectedItem().toString());
                    mNewRequisitionList.get(currentPosition).setOrderQty(editQuantity);

                    notifyDataSetChanged();
                    dialog.dismiss();
                }
            });



            dialog.show();



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

        holder.itemDescription = current.getItemDescription();
        holder.qtyOrdered = current.getOrderQty();

        holder.currentPosition = position;

    }

    @Override
    public int getItemCount() {
        if (mNewRequisitionList != null)
            return mNewRequisitionList.size();
        else return 0;
    }






}
