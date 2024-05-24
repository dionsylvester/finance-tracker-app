package com.example.dionfinanceapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogUtils {
    public interface AddRecordListener {
        void onAddRecord(String labelName, double money, Date date, String type);
    }

    public static void openAddRecordDialog(Context context, final AddRecordListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.activity_add_record, null);

        final EditText editTextLabelName = dialogView.findViewById(R.id.editTextLabelName);
        final EditText editTextMoney = dialogView.findViewById(R.id.editTextMoney);
        final EditText editTextDate = dialogView.findViewById(R.id.editTextDate);
        final EditText editTextType = dialogView.findViewById(R.id.editTextType);

        builder.setView(dialogView)
                .setTitle("Add Record")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    String labelName = editTextLabelName.getText().toString().trim();
                    String moneyText = editTextMoney.getText().toString().trim();
                    String dateText = editTextDate.getText().toString().trim();
                    String type = editTextType.getText().toString().trim();

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String labelName = editTextLabelName.getText().toString().trim();
                        String moneyText = editTextMoney.getText().toString().trim();
                        String dateText = editTextDate.getText().toString().trim();
                        String type = editTextType.getText().toString().trim();

                        if (TextUtils.isEmpty(labelName) || TextUtils.isEmpty(moneyText) || TextUtils.isEmpty(dateText) || (!TextUtils.equals("Income", type) && !TextUtils.equals("Outcome", type))) {     // validasi
                            Toast.makeText(context, "Please fill out all required fields", Toast.LENGTH_SHORT).show();
                        } else {
                            double money = Double.parseDouble(moneyText);   // mengubah string menjadi double
                            try {
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");    // mengubah string menjadi date
                                Date date = formatter.parse(dateText);
                                if (listener != null) {
                                    listener.onAddRecord(labelName, money, date, type);
                                }
                            } catch (ParseException e) {    // handling pada parse
                                throw new RuntimeException(e);
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.create().show();
    }
}
