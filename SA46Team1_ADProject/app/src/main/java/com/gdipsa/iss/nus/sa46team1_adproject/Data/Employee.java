package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import android.util.Log;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;
import com.gdipsa.iss.nus.sa46team1_adproject.StackTrace;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Employee {

    private String email;
    private String password;
    private String role;
    private String token;

    //New stuff
    private String employeeId;
    private String employeeName;
    private String departmentCode;

//    static  final String host = "http://172.17.50.85/AdProj";

//    static  final String host = "http://192.168.1.3/adtest2";

    static  final String host = "http://172.17.191.74/adtest2";

    public Employee(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static String getToken(Employee employee) throws IOException {
        String token = "";
        InputStream is = null;
        String username = employee.getEmail();
        String password = employee.getPassword();
        try {
            String str = "grant_type=password&username="+username+"&password="+password;
            URL url = new URL(host + "/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setFixedLengthStreamingMode(str.getBytes().length);
            conn.connect();
            OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            os.write(str.getBytes());
            os.flush();
            int status = conn.getResponseCode();
            is = conn.getInputStream();
            String data = JSONParser.readStream(is);
            try {
                JSONObject jObj = new JSONObject(data);
                token = jObj.getString("access_token");
            } catch (JSONException e) {
                Log.e("Exception", StackTrace.trace(e));
            }
        }catch (UnsupportedEncodingException e) {
            Log.e("postStream Exception", StackTrace.trace(e));
        } catch (ProtocolException e) {
            Log.e("postStream Exception", StackTrace.trace(e));
        }
        return token;
    }

    public static Employee getEmployeeRole(Employee employee) throws MalformedURLException {
        //String str = "{ \"email\": \""+employee.getEmail()+"\"}";
        String email = employee.getEmail();
        InputStream is = null;
        try {
            URL url = new URL(host + "/api/Restful/GetEmployeeRole?email="+email);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            String token = employee.getToken();
            conn.setRequestProperty("Authorization", "Bearer"+ " "+ token);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jObj = null;
        try {
            jObj = new JSONObject(JSONParser.readStream(is));

            employee.employeeId = jObj.getString("EmployeeID");
            employee.employeeName = jObj.getString("EmployeeName");
            employee.departmentCode = jObj.getString("DepartmentCode");
            employee.role = jObj.getString("Designation");

        } catch (JSONException e) {
            Log.e("Exception", StackTrace.trace(e));
        }

        return employee;
    }


    //Getter and Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }


}