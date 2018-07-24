package com.gdipsa.iss.nus.sa46team1_adproject.Data;

import com.gdipsa.iss.nus.sa46team1_adproject.JSONParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private String itemCode;
    private String category;
    private String description;
    private int quantity;
    private String UoM;
    private String supplier1;
    private String supplier2;
    private String supplier3;
    private int active;

    public Item(String itemCode, String category, String description, int quantity, String UoM, String supplier1, String supplier2, String supplier3, int active) {
        this.itemCode = itemCode;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.UoM = UoM;
        this.supplier1 = supplier1;
        this.supplier2 = supplier2;
        this.supplier3 = supplier3;
        this.active = active;
    }

    public static Item getItem(String searchItemCode){
        Item item = null;

        try {

            JSONObject jsonObject = JSONParser.getJSONFromUrl("http://192.168.1.3/adtest2/api/Restful/getitemsbyid/" + searchItemCode);
//            JSONObject jsonObject = JSONParser.getJSONFromUrl("http://192.168.1.3/adtest2/api/Restful/getitemsbyid/C001");

            if (jsonObject == null){
                return null;
            }

            String dataItemCode;
            String dataCategory;
            String dataDescription;
            int dataQuantity;
            String dataUoM;
            String dataSupplier1;
            String dataSupplier2;
            String dataSupplier3;
            int dataActive;

            dataItemCode = jsonObject.getString("ItemCode");
            dataCategory = jsonObject.getString("CategoryName");
            dataDescription = jsonObject.getString("Description");
            dataQuantity = jsonObject.getInt("Quantity");
            dataUoM = jsonObject.getString("UoM");
            dataSupplier1 = jsonObject.getString("s1");
            dataSupplier2 = jsonObject.getString("s2");
            dataSupplier3 = jsonObject.getString("s3");
            dataActive = jsonObject.getInt("Active");

            item = new Item(dataItemCode, dataCategory, dataDescription, dataQuantity, dataUoM, dataSupplier1, dataSupplier2, dataSupplier3, dataActive);


        } catch (Exception e) {

            return null;
        }
        return item;


    }



    public static List<Item> listItems(String IPAddress){
        List<Item> list = new ArrayList<Item>();

        try {

//            final String host = "http://" + IPAddress + "/api/Restful/GetStockRetrievalList";
//
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl(host+"/StoR-3");

            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://192.168.1.3/adtest2/api/Restful/getitemslist/");
//            JSONArray jsonArray = JSONParser.getJSONArrayFromUrl("http://172.17.191.101/adtest2/api/Restful/getitemslist/");

            JSONObject jsonObject;

            String dataItemCode;
            String dataCategory;
            String dataDescription;
            int dataQuantity;
            String dataUoM;
            String dataSupplier1;
            String dataSupplier2;
            String dataSupplier3;
            int dataActive;

            for (int i=0; i<jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                dataItemCode = jsonObject.getString("ItemCode");
                dataCategory = jsonObject.getString("CategoryName");
                dataDescription = jsonObject.getString("Description");
                dataQuantity = jsonObject.getInt("Quantity");
                dataUoM = jsonObject.getString("UoM");
                dataSupplier1 = jsonObject.getString("s1");
                dataSupplier2 = jsonObject.getString("s2");
                dataSupplier3 = jsonObject.getString("s3");
                dataActive = jsonObject.getInt("Active");

                Item item = new Item(dataItemCode, dataCategory, dataDescription, dataQuantity, dataUoM, dataSupplier1, dataSupplier2, dataSupplier3, dataActive);

                list.add(item);
            }
        } catch (Exception e) {
        }
        return list;


    }

    //Getter and setter
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUoM() {
        return UoM;
    }

    public void setUoM(String UoM) {
        this.UoM = UoM;
    }

    public String getSupplier1() {
        return supplier1;
    }

    public void setSupplier1(String supplier1) {
        this.supplier1 = supplier1;
    }

    public String getSupplier2() {
        return supplier2;
    }

    public void setSupplier2(String supplier2) {
        this.supplier2 = supplier2;
    }

    public String getSupplier3() {
        return supplier3;
    }

    public void setSupplier3(String supplier3) {
        this.supplier3 = supplier3;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


}
