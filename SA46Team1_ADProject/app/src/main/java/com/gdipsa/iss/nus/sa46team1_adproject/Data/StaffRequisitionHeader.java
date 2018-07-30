package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StaffRequisitionHeader {

    private String requisitionFormId;
    private String dateRequestedStr;
    private String approvalStatus;

    public StaffRequisitionHeader(String requisitionFormId, String dateRequestedStr, String approvalStatus) {
        this.requisitionFormId = requisitionFormId;
        this.dateRequestedStr = dateRequestedStr;
        this.approvalStatus = approvalStatus;
    }

    public static List<StaffRequisitionHeader> listStaffRequisitionHeader(String employeeId){

        List<StaffRequisitionHeader> list = new ArrayList<StaffRequisitionHeader>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.74/adtest2/api/Restful/GetStaffRequisitionHeader");
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetDisbursementList");

            JSONObject jsonObject;

            String dataRequisitionFormId;
            String dataDateRequestedStr;
            String dataApprovalStatus;

            String dataEmployeeId;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataRequisitionFormId = jsonObject.getString("FormID");
                dataDateRequestedStr = jsonObject.getString("DateRequested");
                dataApprovalStatus = jsonObject.getString("ApprovalStatus");
                dataEmployeeId = jsonObject.getString("EmployeeID");

                StaffRequisitionHeader staffRequisitionHeader = new StaffRequisitionHeader(dataRequisitionFormId, dataDateRequestedStr, dataApprovalStatus);

                //TODO: For now assume that the employee id is E4 which is passed from the req history activity
                if (dataEmployeeId.equals(employeeId)){
                    list.add(staffRequisitionHeader);
                }

            }
        } catch (Exception e) {
        }

        return list;

    }

    public static List<StaffRequisitionHeader> listStaffRequisitionHeaderDepartmentRep(String departmentId){

        List<StaffRequisitionHeader> list = new ArrayList<StaffRequisitionHeader>();

        try {

//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/GetRequisitionHistoryDepartmentRep/" + departmentId);

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.74/adtest2/api/Restful/GetRequisitionHistoryDepartmentRep/" + departmentId);

            JSONObject jsonObject;

            String dataRequisitionFormId;
            String dataDateRequestedStr;
            String dataApprovalStatus;

            String dataEmployeeId;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataRequisitionFormId = jsonObject.getString("FormID");
                dataDateRequestedStr = jsonObject.getString("DateRequested");
                dataApprovalStatus = jsonObject.getString("ApprovalStatus");

                StaffRequisitionHeader staffRequisitionHeader = new StaffRequisitionHeader(dataRequisitionFormId, dataDateRequestedStr, dataApprovalStatus);

                list.add(staffRequisitionHeader);

            }
        } catch (Exception e) {
        }

        return list;

    }




    //Getter and setter
    public String getRequisitionFormId() {
        return requisitionFormId;
    }

    public void setRequisitionFormId(String requisitionFormId) {
        this.requisitionFormId = requisitionFormId;
    }

    public String getDateRequestedStr() {
        return dateRequestedStr;
    }

    public void setDateRequestedStr(String dateRequestedStr) {
        this.dateRequestedStr = dateRequestedStr;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }







}
