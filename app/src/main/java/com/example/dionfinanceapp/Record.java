package com.example.dionfinanceapp;

import java.util.Date;

public abstract class Record {
    private String labelName;
    private double money;
    private Date date;
    private String type;

    public Record(String labelName, double money, Date date, String type) {
        this.labelName = labelName;
        this.money = money;
        this.date = date;
        this.type = type;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(this.type.equals("Income") || this.type.equals("Outcome") ){
            this.type = type;
        }
    }

    public abstract String getDetails();
}
