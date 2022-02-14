package com.guoyucao.cashierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.guoyucao.cashierapp.history.HistoryListActivity;

public class ManagerActivity extends AppCompatActivity {

    Button historyBtn;
    Button restockBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        historyBtn = findViewById(R.id.historyBtn);
        restockBtn = findViewById(R.id.restockBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ManagerActivity.this, HistoryListActivity.class);// messaging object
                startActivity(myIntent);
            }
        });

        restockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ManagerActivity.this, RestockActivity.class);// messaging object
                startActivity(myIntent);
            }
        });


    }
}