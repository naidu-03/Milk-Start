package com.example.myfinalproject;

public class history {
    private String date;
    private String milk_given;
    private String milk;
    private String total;
    history(String date,String total,String milk,String milk_given ) {
        this.date = date;
        this.milk= milk;
        this.milk_given=milk_given;
        this.total=total;
    }
    public  String getdate() {
        return date;
    }
    public void setdate(String FId) {
        this.date= FId;
    }
    public String getcost() {
        return total;
    }
    public String gettotal(){
        return Integer.toString(Integer.parseInt(total)*Integer.parseInt(milk));
    }
    public void setcost(String milk_cost) {
        this.total=milk_cost;
    }
    public String getorder() {
        return milk;
    }
    public void setorder(String milk) {
        this.milk=milk;
    }
    public String getMilk_given(){
        return milk_given;
    }
}

