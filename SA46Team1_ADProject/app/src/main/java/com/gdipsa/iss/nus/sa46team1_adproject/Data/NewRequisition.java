package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONObject;

public class NewRequisition {

    private String employeeId;
    private String itemDescription;
    private String itemUoM;
    private int orderQty;

    private int requisitionSize;
    private int requisitionIdAndroid;

    public NewRequisition(String employeeId, String itemDescription, String itemUoM, int orderQty) {
        this.employeeId = employeeId;
        this.itemDescription = itemDescription;
        this.itemUoM = itemUoM;
        this.orderQty = orderQty;
    }

    public static void createStockAdjustment(NewRequisition newRequisition) {
        JSONObject jNewRequisition = new JSONObject();
        try {

            jNewRequisition.put("EmployeeId", newRequisition.getEmployeeId());
            jNewRequisition.put("ItemDescription", newRequisition.getItemDescription());
            jNewRequisition.put("OrderedQuantity", newRequisition.getOrderQty());
            jNewRequisition.put("RequisitionSize", newRequisition.getRequisitionSize());
            jNewRequisition.put("RequisitionAndroidId", newRequisition.getRequisitionIdAndroid());

        } catch (Exception e) {
        }

//        String result = JSONParser.postStream("http://192.168.1.3/adtest2/api/Restful/CreateNewRequisition", jNewRequisition.toString());

        String result = JSONParser.postStream("http://172.17.50.85/AdProj/api/Restful/CreateNewRequisition", jNewRequisition.toString());
    }


    //Getter and setter
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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


    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getRequisitionSize() {
        return requisitionSize;
    }

    public void setRequisitionSize(int requisitionSize) {
        this.requisitionSize = requisitionSize;
    }

    public int getRequisitionIdAndroid() {
        return requisitionIdAndroid;
    }

    public void setRequisitionIdAndroid(int requisitionIdAndroid) {
        this.requisitionIdAndroid = requisitionIdAndroid;
    }


}
