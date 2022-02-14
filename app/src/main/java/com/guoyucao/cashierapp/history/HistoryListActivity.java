package com.guoyucao.cashierapp.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.guoyucao.cashierapp.ManagerActivity;
import com.guoyucao.cashierapp.MyApp;
import com.guoyucao.cashierapp.R;

public class HistoryListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryRecycleAdapter hisAdapter;
    HistoryRecycleAdapter.ItemListener historyListener = new HistoryRecycleAdapter.ItemListener() {
        @Override
        public void onItemClicked(History item, int position) {

            Intent myIntent = new Intent(HistoryListActivity.this, HistoryDetail.class);// messaging object
            myIntent.putExtra("Name",  item.getItem().getName());
            myIntent.putExtra("Price",  String.valueOf(item.getItem().getPrice()));
            myIntent.putExtra("Date",  item.getDate());
            System.out.println("Debug-----------------------");
            System.out.println(myIntent.getExtras());
            System.out.println("-----------------------Debug");
            startActivity(myIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        recyclerView = findViewById(R.id.historyList);
        hisAdapter = new HistoryRecycleAdapter(this,((MyApp)getApplication()).getHistoryManager().histories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hisAdapter);

        hisAdapter.setItemListener(historyListener);


    }
}