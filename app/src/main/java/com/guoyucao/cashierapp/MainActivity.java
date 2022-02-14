package com.guoyucao.cashierapp;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.guoyucao.cashierapp.history.History;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity  extends AppCompatActivity {

    TextView productType,total,quantity;
    Button manager,buy;
    NumberPicker numberPicker;
    ListView listView;
    ProductBaseAdapter adapter;
    ArrayList<Product> list;
    Product p;
    int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productType = findViewById(R.id.productType);
        manager = findViewById(R.id.manager);
        numberPicker = findViewById(R.id.quantityPicker);
        total = findViewById(R.id.total);
        quantity = findViewById(R.id.selectedQuantity);
        buy = findViewById(R.id.buy);
        listView = findViewById(R.id.productList);

        list = ((MyApp) getApplication()).productManager.products;

        adapter = new ProductBaseAdapter(list, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                p = (Product)adapter.getItem(position);
                pos = position;
                productType.setText(p.name);
                total.setText("Total");
                numberPicker.setMaxValue(p.stock+1);
                numberPicker.setMinValue(0);
                numberPicker.setValue(0);
                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();

            }
        });

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int num = numberPicker.getValue();
                double res = 0.00;
                if (num <= p.stock) {
                    double sum = num * p.price;
                    BigDecimal bg = new BigDecimal(sum);
                    res = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                }
                else {
                    String err = "No enough quantity in the stock!!!";
                    Toast toast = Toast.makeText(MainActivity.this, "No enough quantity in the stock!!!", Toast.LENGTH_SHORT);
                    toast.show();
                    numberPicker.setValue(numberPicker.getMaxValue()-1);
                    int num2 = numberPicker.getValue();
                    double sum = num2 * p.price;
                    BigDecimal bg = new BigDecimal(sum);
                    res = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                }
                total.setText(Double.toString(res));

            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int num = numberPicker.getValue();
                if(num == 0){
                    Toast toast = Toast.makeText(MainActivity.this, "All fields are required!!!", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Product boughtProduct = new Product();
                    boughtProduct.setName((String)productType.getText());
                    boughtProduct.setStock((int)numberPicker.getValue());
                    boughtProduct.setPrice(Double.parseDouble((String)total.getText()));
                    purchaseDialog(boughtProduct);
                    purchaseItem(boughtProduct);

                    Product restProduct = new Product();
                    restProduct.setName(list.get(pos).getName());
                    int rest = list.get(pos).getStock()-(int)numberPicker.getValue();
                    restProduct.setStock(rest);
                    restProduct.setPrice(list.get(pos).getPrice());
                    list.set(pos,restProduct);
                    ((MyApp) getApplication()).productManager.setProducts(list);
                    adapter.notifyDataSetChanged();


                    productType.setText("Product Type");
                    total.setText("Total");
                    numberPicker.setMaxValue(0);
                    numberPicker.setMinValue(0);
                    numberPicker.setValue(0);


                }
            }
        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // I need to open the Manager Activity
                // we need Intent Object
                Intent myIntent = new Intent(MainActivity.this,ManagerActivity.class);// messaging object
                startActivity(myIntent);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void purchaseItem(Product item){
        History history = new History(item);
        ((MyApp) getApplication()).addHistory(history);
    }

    private void purchaseDialog(Product item){
        MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
        dialogBuilder.setPositiveButton("Close", (dialog, which) -> {
            dialog.dismiss();
        });
        dialogBuilder.setTitle(R.string.buy);
        dialogBuilder.setCancelable(true);
        StringBuilder sb = new StringBuilder(getString(R.string.purchase_prefix));
        sb.append(item.getStock()).append(' ');
        sb.append(item.getName()).append(' ').append(" for ");
        sb.append(item.getPrice()).append(" .");
        dialogBuilder.setMessage(sb.toString());
        dialogBuilder.show();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("position", pos);
        outState.putInt("picker", (int)numberPicker.getValue());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        resumeData(list.get((Integer) savedInstanceState.get("position")), (Integer) savedInstanceState.get("picker"));

    }

    private void resumeData(Product item,int num){
        productType.setText(item.name);
        double sum = num * item.price;
        BigDecimal bg = new BigDecimal(sum);
        double res = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        total.setText(Double.toString(res));
        numberPicker.setMaxValue(item.stock+1);
        numberPicker.setMinValue(0);
        numberPicker.setValue(num);
    }


}