package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONObject;

public class StockAdjustment {

    private String requestorId;
    private String itemDescription;
    private int adjustedQuantity;
    private String remarks;
    private String stockRetrievalId;

    private static String host = "http://172.17.191.74/adtest2";

    public StockAdjustment(String requestorId, String itemDescription, int adjustedQuantity, String remarks, String stockRetrievalId) {
        this.requestorId = requestorId;
        this.itemDescription = itemDescription;
        this.adjustedQuantity = adjustedQuantity;
        this.remarks = remarks;
        this.stockRetrievalId = stockRetrievalId;
    }

    public static void createStockAdjustment(StockAdjustment stockAdjustment) {
        JSONObject jStockAdjustment = new JSONObject();
        try {

            jStockAdjustment.put("RequestorId", stockAdjustment.getRequestorId());
            jStockAdjustment.put("ItemDescription", stockAdjustment.getItemDescription());
            jStockAdjustment.put("AdjustedQuantity", stockAdjustment.getAdjustedQuantity());
            jStockAdjustment.put("Remarks", stockAdjustment.getRemarks());
            jStockAdjustment.put("StockRetrievalId", stockAdjustment.getStockRetrievalId());

        } catch (Exception e) {
        }

        String result = JSONParser.postStream(host + "/api/Restful/CreateNewStockAdjustment", jStockAdjustment.toString());
    }

    //Getter and Setter
    public String getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(String requestorId) {
        this.requestorId = requestorId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getAdjustedQuantity() {
        return adjustedQuantity;
    }

    public void setAdjustedQuantity(int adjustedQuantity) {
        this.adjustedQuantity = adjustedQuantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStockRetrievalId() {
        return stockRetrievalId;
    }

    public void setStockRetrievalId(String stockRetrievalId) {
        this.stockRetrievalId = stockRetrievalId;
    }

}
