package com.guoyucao.cashierapp.history;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.guoyucao.cashierapp.R;

public class HistoryDetail extends AppCompatActivity {
    TextView name, price, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        date = findViewById(R.id.date);
        String n = getIntent().getExtras().getString("Name");
        String p = getIntent().getExtras().getString("Price");
        String d = getIntent().getExtras().getString("Date");
        name.setText(n);
        price.setText(p);
        date.setText(d);

    }
}