package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockRetrieval {

    private int binNumber;
    private String itemDescription;
    private int itemsRetrieved;
    private String collectionPointDescription;


    public StockRetrieval(int binNumber, String itemDescription, int itemsRetrieved, String collectionPointDescription){
        this.binNumber = binNumber;
        this.itemDescription = itemDescription;
        this.itemsRetrieved = itemsRetrieved;
        this.collectionPointDescription = collectionPointDescription;
    }

    public static List<StockRetrieval> listStockRetrievals(String IPAddress){
        List<StockRetrieval> list = new ArrayList<StockRetrieval>();

        try {

//            final String host = "http://" + IPAddress + "/api/Restful/GetStockRetrievalList";
//
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/StoR-3");

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.101/adtest2/api/Restful/GetStockRetrievalList/StoR-3");

            JSONObject jsonObject;

            int dataBinNumber = 0;
            String dataItemDescription = null;
            int dataItemsRetrieved = 0;
            String dataCollectionPointDescription = null;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataBinNumber = jsonObject.getInt("Bin");
                dataItemDescription = jsonObject.getString("Description");
                dataItemsRetrieved = jsonObject.getInt("QuantityRetrieved");
                dataCollectionPointDescription = jsonObject.getString("CollectionPointDescription");

                StockRetrieval stockRetrieval = new StockRetrieval(dataBinNumber, dataItemDescription, dataItemsRetrieved, dataCollectionPointDescription);

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
