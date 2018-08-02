package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockRetrieval {

    private int binNumber;
    private String binLocation;
    private String itemDescription;
    private int itemsRetrieved;
    private String collectionPointDescription;

    private static String host = "http://172.17.191.74/adtest2";

    public StockRetrieval(int binNumber, String binLocation, String itemDescription, int itemsRetrieved, String collectionPointDescription){
        this.binNumber = binNumber;
        this.binLocation = binLocation;
        this.itemDescription = itemDescription;
        this.itemsRetrieved = itemsRetrieved;
        this.collectionPointDescription = collectionPointDescription;
    }

    public static String getLatestStockRetrievalid(){
        String stockRetrievalId = null;

        try{

            JSONObject jsonObject = JSONParser.getJSONFromUrl(host + "/api/Restful/GetLatestStockRetrievalId");

            stockRetrievalId = jsonObject.getString("ID");

        }catch (Exception e) {
        }

        return stockRetrievalId;

    }


    public static List<StockRetrieval> listStockRetrievals(String stockRetrievalId){
        List<StockRetrieval> list = new ArrayList<StockRetrieval>();

        try {

           JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetStockRetrievalList/" + stockRetrievalId);

            JSONObject jsonObject;

            int dataBinNumber = 0;
            String dataLocation = null;
            String dataItemDescription = null;
            int dataItemsRetrieved = 0;
            String dataCollectionPointDescription = null;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataBinNumber = jsonObject.getInt("Bin");
                dataLocation = jsonObject.getString("Location");
                dataItemDescription = jsonObject.getString("Description");
                dataItemsRetrieved = jsonObject.getInt("QuantityRetrieved");
                dataCollectionPointDescription = jsonObject.getString("CollectionPointDescription");

                StockRetrieval stockRetrieval = new StockRetrieval(dataBinNumber, dataLocation, dataItemDescription, dataItemsRetrieved, dataCollectionPointDescription);

                list.add(stockRetrieval);
            }
        } catch (Exception e) {
        }
        return list;


    }



    //Getter and setter
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(int binNumber) {
        this.binNumber = binNumber;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public int getItemsRetrieved() {
        return itemsRetrieved;
    }

    public void setItemsRetrieved(int itemsRetrieved) {
        this.itemsRetrieved = itemsRetrieved;
    }

    public String getCollectionPointDescription() {
        return collectionPointDescription;
    }

    public void setCollectionPointDescription(String collectionPointDescription) {
        this.collectionPointDescription = collectionPointDescription;
    }

}
