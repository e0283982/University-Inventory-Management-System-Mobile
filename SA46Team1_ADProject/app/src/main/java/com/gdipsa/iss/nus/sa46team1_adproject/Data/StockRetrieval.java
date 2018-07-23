package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StockRetrieval {

    private String itemDescription;

    public StockRetrieval(String itemDescription){
        this.itemDescription = itemDescription;
    }

    public static List<StockRetrieval> listStockRetrievals(String IPAddress){
        List<StockRetrieval> list = new ArrayList<StockRetrieval>();

        try {

//            final String host = "http://" + IPAddress + "/api/Restful/GetStockRetrievalList";
//
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/StoR-3");

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetStockRetrievalList/StoR-3");

            JSONObject jsonObject;

            String dataItemDescription = null;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataItemDescription = jsonObject.getString("Description");

                StockRetrieval stockRetrieval = new StockRetrieval(dataItemDescription);

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

}
