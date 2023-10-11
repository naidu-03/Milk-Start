package com.example.myfinalproject;

public class farmerslist {
    private String FId;
    private String milk;
    private String milk_cost;
    farmerslist(String FId,String milk_cost,String milk ) {
        this.FId = FId;
        this.milk= milk;
        this.milk_cost=milk_cost;
    }
    public  String getId() {
        return FId;
    }
    public void setId(String FId) {
        this.FId = FId;
    }
    public String getcost() {
        return milk_cost;
    }
    public void setcost(String milk_cost) {
        this.milk_cost=milk_cost;
    }
    public String getavail() {
        return milk;
    }
    public void setavail(String milk) {
        this.milk=milk;
    }
}