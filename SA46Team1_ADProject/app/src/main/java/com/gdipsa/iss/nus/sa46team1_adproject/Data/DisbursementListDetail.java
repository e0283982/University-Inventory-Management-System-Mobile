package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisbursementListDetail {

    private String disbursementId;
    private String itemDescription;
    private String itemUoM;
    private int qtyOrdered;
    private int qtyReceived;
    private int qtyAdjusted = 0;


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

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetDisbursementListDetails/" + disbursementIdChosen);

//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetDisbursementListDetails/" + disbursementIdChosen);

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


    //TODO: To change to updateDisbursementDetail
    public static void updateDisbursementDetail(DisbursementListDetail disbursementDetails) {
        JSONObject jDisbursementDetail = new JSONObject();
        try {

            //TODO: to link to the login id of the user, temporary put E2
            jDisbursementDetail.put("RequestorId", "E2");
            jDisbursementDetail.put("DisbursementId", disbursementDetails.getDisbursementId());
            jDisbursementDetail.put("ItemDescription", disbursementDetails.getItemDescription());
            jDisbursementDetail.put("QuantityReceived", disbursementDetails.getQtyReceived());
            jDisbursementDetail.put("QuantityAdjusted", disbursementDetails.getQtyAdjusted());

        } catch (Exception e) {
        }

        String result = JSONParser.postStream("http://172.17.191.101/adtest2/api/Restful/updatedisbursement", jDisbursementDetail.toString());

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


}