package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hendri Setia Wardana
 */

public class StaffRequisitionHeader {

    private String requisitionFormId;
    private String dateRequestedStr;
    private String approvalStatus;
    private String status;

    private static String host = "http://172.17.190.9/lu";

    public StaffRequisitionHeader(String requisitionFormId, String dateRequestedStr, String approvalStatus, String status) {
        this.requisitionFormId = requisitionFormId;
        this.dateRequestedStr = dateRequestedStr;
        this.approvalStatus = approvalStatus;
        this.status = status;
    }

    public static List<StaffRequisitionHeader> listStaffRequisitionHeader(String employeeId){

        List<StaffRequisitionHeader> list = new ArrayList<StaffRequisitionHeader>();

        try {

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetStaffRequisitionHeader");

            JSONObject jsonObject;

            String dataRequisitionFormId;
            String dataDateRequestedStr;
            String dataApprovalStatus;
            String dataStatus;

            String dataEmployeeId;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataRequisitionFormId = jsonObject.getString("FormID");
                dataDateRequestedStr = jsonObject.getString("DateRequested");
                dataApprovalStatus = jsonObject.getString("ApprovalStatus");
                dataEmployeeId = jsonObject.getString("EmployeeID");
                dataStatus = jsonObject.getString("Status");

                StaffRequisitionHeader staffRequisitionHeader = new StaffRequisitionHeader(dataRequisitionFormId, dataDateRequestedStr, dataApprovalStatus, dataStatus);

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

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host + "/api/Restful/GetStaffRequisitionHeader");

            JSONObject jsonObject;

            String dataRequisitionFormId;
            String dataDateRequestedStr;
            String dataApprovalStatus;
            String dataDepartmentCode;
            String dataStatus;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataRequisitionFormId = jsonObject.getString("FormID");
                dataDateRequestedStr = jsonObject.getString("DateRequested");
                dataApprovalStatus = jsonObject.getString("ApprovalStatus");
                dataDepartmentCode = jsonObject.getString("DepartmentCode");
                dataStatus = jsonObject.getString("Status");

                StaffRequisitionHeader staffRequisitionHeader = new StaffRequisitionHeader(dataRequisitionFormId, dataDateRequestedStr, dataApprovalStatus, dataStatus);


                if (dataDepartmentCode.equals(departmentId)){
                    list.add(staffRequisitionHeader);
                }
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
