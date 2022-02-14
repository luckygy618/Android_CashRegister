package com.guoyucao.cashierapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    ArrayList<Product> products;
    Context context;

    private int selectItem = -1;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }


    public ProductBaseAdapter(ArrayList<Product> listProducts, Context context) {
        this.products = listProducts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row_layout,null);

        TextView name = view.findViewById(R.id.productName);
        TextView stock = view.findViewById(R.id.stock);
        TextView price = view.findViewById(R.id.price);

        name.setText(String.valueOf(products.get(i).name));
        stock.setText(String.valueOf(products.get(i).stock));
        price.setText(String.valueOf(products.get(i).price));

        // img.setImageResource(R.drawable.img);
        if (i == selectItem) {
            view.setBackgroundColor(Color.LTGRAY);
        }else{
            view.setBackgroundColor(Color.WHITE);
        }
            return view;
    }



}
