package com.gdipsa.iss.nus.sa46team1_adproject.Data;

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

    public Item(String description){
        this.description = description;
    }

    public Item(String itemCode, String description, String category, int quantity, String UoM, String supplier1, String supplier2, String supplier3, int active) {
        this.itemCode = itemCode;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.UoM = UoM;
        this.supplier1 = supplier1;
        this.supplier2 = supplier2;
        this.supplier3 = supplier3;
        this.active = active;
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
