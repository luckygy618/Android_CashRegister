package com.guoyucao.cashierapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {

    EditText editText;
    Button ok, cancel;
    ListView listView;
    ProductBaseAdapter adapter;
    ArrayList<Product> list;
    Product p;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        editText = findViewById(R.id.newQuantity);
        ok = findViewById(R.id.okBtn);
        cancel = findViewById(R.id.cancelBtn);
        listView = findViewById(R.id.productList);
        list = ((MyApp) getApplication()).productManager.products;
        adapter = new ProductBaseAdapter(list, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                p = (Product)adapter.getItem(position);
                pos = position;
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
                editText.setText("");

            }
        });



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString().trim();
                if(TextUtils.isEmpty(s)){
                    Toast toast = Toast.makeText(RestockActivity.this, "All fields are REQUIRED!!!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    int num = Integer.valueOf(s);

                    Product restProduct = new Product();
                    restProduct.setName(list.get(pos).getName());
                    int rest = list.get(pos).getStock() + num;
                    restProduct.setStock(rest);
                    restProduct.setPrice(list.get(pos).getPrice());
                    list.set(pos,restProduct);
                    ((MyApp) getApplication()).productManager.setProducts(list);
                    adapter.notifyDataSetChanged();
                    editText.setText("");


                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
}