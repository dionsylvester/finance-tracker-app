package com.example.dionfinanceapp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MoneyRecord extends Record {
    public MoneyRecord(String labelName, double money, Date date, String type) {
        super(labelName, money, date, type);
    }
    @Override
    public String getDetails() {
        new DecimalFormat("#,###.##");  // menambahkan thousands separator pada variabel money
        DecimalFormat decF = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        SimpleDateFormat dateF = new SimpleDateFormat("d MMMM yyyy");  // mengubah format tanggal
        return dateF.format(getDate()) + "\n" + getLabelName() + " (" + getType() + "): Rp" + decF.format(getMoney());
    }

    @Override
    public String toString() {
        return getDetails();
    }
}
