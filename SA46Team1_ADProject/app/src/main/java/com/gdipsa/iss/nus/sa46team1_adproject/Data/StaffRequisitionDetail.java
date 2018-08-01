package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaffRequisitionDetail {

//    private String formId;

    private String itemDescription;
    private String itemUoM;
    private int qtyOrdered;
    private int qtyDelivered;
    private int qtyBackOrdered;

    private static String host = "http://192.168.1.3/adtest2";

    public StaffRequisitionDetail(String itemDescription, String itemUoM, int qtyOrdered, int qtyDelivered, int qtyBackOrdered) {
        this.itemDescription = itemDescription;
        this.itemUoM = itemUoM;
        this.qtyOrdered = qtyOrdered;
        this.qtyDelivered = qtyDelivered;
        this.qtyBackOrdered = qtyBackOrdered;
    }


    public static List<StaffRequisitionDetail> listStaffRequisitionDetail(String formId){

        List<StaffRequisitionDetail> list = new ArrayList<StaffRequisitionDetail>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetRequisitionHistoryDetail/" + formId);

            JSONObject jsonObject;

            String dataItemDescription;
            String dataItemUoM;
            int dataQtyOrdered;
            int dataQtyDelivered;
            int dataQtyBackOrdered;


            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataItemDescription = jsonObject.getString("Description");
                dataItemUoM = jsonObject.getString("UoM");
                dataQtyOrdered = jsonObject.getInt("QuantityOrdered");
                dataQtyDelivered = jsonObject.getInt("QuantityDelivered");
                dataQtyBackOrdered = jsonObject.getInt("QuantityBackOrdered");

                StaffRequisitionDetail staffRequisitionDetail = new StaffRequisitionDetail(dataItemDescription, dataItemUoM, dataQtyOrdered, dataQtyDelivered, dataQtyBackOrdered);

                list.add(staffRequisitionDetail);

            }
        } catch (Exception e) {
        }

        return list;

    }


    //Getter and Setter
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

    public int getQtyDelivered() {
        return qtyDelivered;
    }

    public void setQtyDelivered(int qtyDelivered) {
        this.qtyDelivered = qtyDelivered;
    }

    public int getQtyBackOrdered() {
        return qtyBackOrdered;
    }

    public void setQtyBackOrdered(int qtyBackOrdered) {
        this.qtyBackOrdered = qtyBackOrdered;
    }











}
