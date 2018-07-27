package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CollectionPoint {

    private String collectionPointId;
    private String collectionPointDescription;
    private String collectionPointTime;
    private int active;

    public CollectionPoint(String collectionPointId, String collectionPointDescription, String collectionPointTime, int active) {
        this.collectionPointId = collectionPointId;
        this.collectionPointDescription = collectionPointDescription;
        this.collectionPointTime = collectionPointTime;
        this.active = active;
    }


    public static List<CollectionPoint> listCollectionPoints(){

        List<CollectionPoint> list = new ArrayList<CollectionPoint>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.101/adtest2/api/Restful/GetCollectionPointList");

//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetCollectionPointList");

            JSONObject jsonObject;

            String dataCollectionPointId;
            String dataCollectionPointDescription;
            String dataCollectionPointTime;
            int dataActive;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataCollectionPointId = jsonObject.getString("CollectionPointID");
                dataCollectionPointDescription = jsonObject.getString("CollectionPointDescription");
                dataCollectionPointTime = jsonObject.getString("CollectionTime");
                dataActive = jsonObject.getInt("Active");

                CollectionPoint collectionPoint = new CollectionPoint(dataCollectionPointId, dataCollectionPointDescription, dataCollectionPointTime, dataActive);

                if (dataActive == 1){
                    list.add(collectionPoint);
                }


            }
        } catch (Exception e) {
        }

        return list;


    }




    //Getter and setter
    public String getCollectionPointId() {
        return collectionPointId;
    }

    public void setCollectionPointId(String collectionPointId) {
        this.collectionPointId = collectionPointId;
    }

    public String getCollectionPointDescription() {
        return collectionPointDescription;
    }

    public void setCollectionPointDescription(String collectionPointDescription) {
        this.collectionPointDescription = collectionPointDescription;
    }

    public String getCollectionPointTime() {
        return collectionPointTime;
    }

    public void setCollectionPointTime(String collectionPointTime) {
        this.collectionPointTime = collectionPointTime;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


}
