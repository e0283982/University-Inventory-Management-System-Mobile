package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisbursementList {

    private String disbursementId;
    private String dateStr;
    private String departmentName;
    private String status;
    private String collectionPointDescription;
    private String representativeName;

    public DisbursementList(String disbursementId, String dateStr, String departmentName, String status, String collectionPointDescription, String representativeName) {
        this.disbursementId = disbursementId;
        this.dateStr = dateStr;
        this.departmentName = departmentName;
        this.status = status;
        this.collectionPointDescription = collectionPointDescription;
        this.representativeName = representativeName;
    }

    public static List<DisbursementList> listDepartmentDisbursementList(String collectionPointChosen){

        List<DisbursementList> list = new ArrayList<DisbursementList>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.101/adtest2/api/Restful/GetDisbursementList");
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetDisbursementList");

            JSONObject jsonObject;

            String dataDisbursementId;
            String dataDateStr;
            String dataDepartmentName;
            String dataStatus;
            String dataCollectionPointDescription;
            String dataRepresentativeName;


            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataDisbursementId = jsonObject.getString("Id");
                dataDateStr = jsonObject.getString("Date");
                dataDepartmentName = jsonObject.getString("DepartmentName");
                dataStatus = jsonObject.getString("Status");
                dataCollectionPointDescription = jsonObject.getString("CollectionPointDescription");
                dataRepresentativeName = jsonObject.getString("RepresentativeName");

                DisbursementList disbursementList = new DisbursementList(dataDisbursementId, dataDateStr, dataDepartmentName, dataStatus, dataCollectionPointDescription, dataRepresentativeName);

                if (dataStatus.equals("Open") && dataCollectionPointDescription.equals(collectionPointChosen)){
                    list.add(disbursementList);
                }


            }
        } catch (Exception e) {
        }

        return list;

    }




    //Getter and Setter
    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(String disbursementId) {
        this.disbursementId = disbursementId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCollectionPointDescription() {
        return collectionPointDescription;
    }

    public void setCollectionPointDescription(String collectionPointDescription) {
        this.collectionPointDescription = collectionPointDescription;
    }

    public String getRepresentativeName() {
        return representativeName;
    }

    public void setRepresentativeName(String representativeName) {
        this.representativeName = representativeName;
    }


}
