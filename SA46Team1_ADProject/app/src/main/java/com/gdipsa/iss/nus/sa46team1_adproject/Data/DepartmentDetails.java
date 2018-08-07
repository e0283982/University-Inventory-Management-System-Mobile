package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class DepartmentDetails {

    private String departmentName;
    private String departmentCode;
    private String contactName;
    private String telephoneNo;
    private String faxNo;
    private String collectionPointName;

    private static String host = "http://172.17.190.9/lu";

    public DepartmentDetails(String departmentName, String departmentCode, String contactName, String telephoneNo, String faxNo, String collectionPointName) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.contactName = contactName;
        this.telephoneNo = telephoneNo;
        this.faxNo = faxNo;
        this.collectionPointName = collectionPointName;
    }

    public static List<DepartmentDetails> departmentDetailsList(){

        List<DepartmentDetails> list = new ArrayList<DepartmentDetails>();

        try {
            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetDeptsList");

            JSONObject jsonObject;

            String dataDeptName;
            String dataDeptCode;
            String dataContactName;
            String dataTelephoneNo;
            String dataFaxNo;
            String dataCollectionPointName;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataDeptName = jsonObject.getString("DepartmentName");
                dataDeptCode = jsonObject.getString("DepartmentCode");
                dataContactName = jsonObject.getString("ContactName");
                dataTelephoneNo = jsonObject.getString("TelephoneNo");
                dataFaxNo = jsonObject.getString("FaxNo");
                dataCollectionPointName = jsonObject.getString("CollectionPointName");

                DepartmentDetails departmentDetails = new DepartmentDetails(dataDeptName, dataDeptCode, dataContactName, dataTelephoneNo, dataFaxNo, dataCollectionPointName);

                list.add(departmentDetails);
            }
        } catch (Exception e) {
        }

        return list;

    }

    //Getter and Setter
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getCollectionPointName() {
        return collectionPointName;
    }

    public void setCollectionPointName(String collectionPointName) {
        this.collectionPointName = collectionPointName;
    }

}
