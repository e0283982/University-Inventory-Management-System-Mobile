package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisbursementListDetail {

    private static String host = "http://172.17.191.74/adtest2";

    private String disbursementId;
    private String itemDescription;
    private String itemUoM;
    private int qtyOrdered;
    private int qtyReceived;
    private int qtyAdjusted = 0;

    private int disbursementIdAndroid = 0;

    public DisbursementListDetail(String disbursementId, String itemDescription, String itemUoM, int qtyOrdered, int qtyReceived) {
        this.disbursementId = disbursementId;
        this.itemDescription = itemDescription;
        this.itemUoM = itemUoM;
        this.qtyOrdered = qtyOrdered;
        this.qtyReceived = qtyReceived;
    }

    public static List<DisbursementListDetail> listDisbursementDetails(String disbursementIdChosen){

        List<DisbursementListDetail> list = new ArrayList<DisbursementListDetail>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetDisbursementListDetails/" + disbursementIdChosen);

            JSONObject jsonObject;

            String dataDisbursementId;
            String dataItemDescription;
            String dataItemUoM;
            int dataQtyOrdered;
            int dataQtyReceived;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataDisbursementId = jsonObject.getString("Id");
                dataItemDescription = jsonObject.getString("Description");
                dataItemUoM = jsonObject.getString("UoM");
                dataQtyOrdered = jsonObject.getInt("QuantityOrdered");
                dataQtyReceived = jsonObject.getInt("QuantityReceived");

                DisbursementListDetail disbursementListDetail = new DisbursementListDetail(dataDisbursementId, dataItemDescription, dataItemUoM, dataQtyOrdered, dataQtyReceived);

                list.add(disbursementListDetail);

            }
        } catch (Exception e) {
        }

        return list;

    }


    public static void updateDisbursementDetail(DisbursementListDetail disbursementDetails, Context context) {
        JSONObject jDisbursementDetail = new JSONObject();
        try {

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String requestorId = pref.getString("EmployeeID", "Employee ID");

            jDisbursementDetail.put("RequestorId", requestorId);
            jDisbursementDetail.put("DisbursementId", disbursementDetails.getDisbursementId());
            jDisbursementDetail.put("ItemDescription", disbursementDetails.getItemDescription());
            jDisbursementDetail.put("QuantityReceived", disbursementDetails.getQtyReceived());
            jDisbursementDetail.put("QuantityAdjusted", disbursementDetails.getQtyAdjusted());

            jDisbursementDetail.put("DisbursementAndroidId", disbursementDetails.getDisbursementIdAndroid());


        } catch (Exception e) {
        }

        String result = JSONParser.postStream(host + "/api/Restful/updatedisbursement", jDisbursementDetail.toString());
    }




    //Getter and setter
    public String getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(String disbursementId) {
        this.disbursementId = disbursementId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemUoM() {
        return itemUoM;
    }

    public void setItemUoM(String itemUoM) {
        this.itemUoM = itemUoM;
    }

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(int qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    public int getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(int qtyReceived) {
        this.qtyReceived = qtyReceived;
    }

    public int getQtyAdjusted() {
        return qtyAdjusted;
    }

    public void setQtyAdjusted(int qtyAdjusted) {
        this.qtyAdjusted = qtyAdjusted;
    }

    public int getDisbursementIdAndroid() {
        return disbursementIdAndroid;
    }

    public void setDisbursementIdAndroid(int disbursementIdAndroid) {
        this.disbursementIdAndroid = disbursementIdAndroid;
    }

}
