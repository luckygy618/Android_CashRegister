package com.guoyucao.cashierapp;

import android.app.Application;

import com.guoyucao.cashierapp.history.History;
import com.guoyucao.cashierapp.history.HistoryManager;

import java.util.ArrayList;

public class MyApp extends Application {
    ProductManager productManager = new ProductManager();
    HistoryManager historyManager = new HistoryManager();

    public void setStock( ArrayList<Product> list){
        productManager.setProducts(list);
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Product pants = new Product("Pants",10,20.44);
        Product shoes = new Product("Shoes",100,10.44);
        Product hats = new Product("Hats",30,5.9);
        productManager.addProduct(pants);
        productManager.addProduct(shoes);
        productManager.addProduct(hats);

    }


    public void addHistory(History his){
        historyManager.addHistory(his);
    }



}
