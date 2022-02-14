package com.guoyucao.cashierapp;

import java.math.BigDecimal;

public class Product {
    String name = "";
    int stock = 0;
    double price = 0;


    public Product(String n, int s, double p){
        name = n;
        stock = s;
        price = p;
    }
    public Product(){
        name = "";
        stock = 0;
        price = 0.00;
    }

    public String getName(){
        return name;
    }
    public int getStock(){
        return stock;
    }
    public double getPrice(){
        return price;
    }

    public void setStock(int n){
        stock = n;
    }

    public void setName(String n){
        name = n;
    }

    public void setPrice(double n){
        BigDecimal bg = new BigDecimal(n);
        double res = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        price = res;
    }


}
