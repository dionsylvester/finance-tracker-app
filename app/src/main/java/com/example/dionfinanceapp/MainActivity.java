package com.example.dionfinanceapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<Record> records;
    private ArrayAdapter<Record> adapter;
    private ListView listView;
    private TextView totalMoneyTextView;
    private Button buttonAdd;
    // Declare the OnItemClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        records = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        listView = findViewById(R.id.listView);
        totalMoneyTextView = findViewById(R.id.totalMoneyTextView);
        buttonAdd = findViewById(R.id.buttonAdd);
        listView.setAdapter(adapter);
        buttonAdd.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    private void updateTotalMoney() {
        double totalMoney = 0.0;
        new DecimalFormat("#,###.##");
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
        for (Record record : records) {
            if(record.getType().equals("Income")){  // apabila record merupakan income, maka total akan bertambah
                totalMoney += record.getMoney();
            } else if(record.getType().equals("Outcome")){  // apabila record merupakan outcome, maka total akan berkurang
                totalMoney -= record.getMoney();
            }
        }
        if(totalMoney > 0){
            totalMoneyTextView.setTextColor(Color.GREEN);   // warna hijau menandakan total saldo positif
        } else{
            totalMoneyTextView.setTextColor(Color.RED);     // warna merah menandakan total saldo negatif
        }
        totalMoneyTextView.setText("Total Balance: Rp" + df.format(totalMoney));
    }

    public void addRecord(Record record) {
        records.add(record);
        adapter.notifyDataSetChanged();
        updateTotalMoney();
    }

    private void openAddRecordDialog() {
        DialogUtils.openAddRecordDialog(this, new DialogUtils.AddRecordListener() {
            @Override
            public void onAddRecord(String labelName, double money, Date date, String type) {
                MoneyRecord newRecord = new MoneyRecord(labelName, money, date, type);
                addRecord(newRecord);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd) {
            openAddRecordDialog();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view == listView){
            Record record = records.get(position);
            Toast.makeText(MainActivity.this, "Selected: " + record.getDetails(), Toast.LENGTH_SHORT).show();
        }
    }
}