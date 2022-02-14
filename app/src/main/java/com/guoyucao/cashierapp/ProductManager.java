package com.guoyucao.cashierapp;

import java.util.ArrayList;

public class ProductManager {
ArrayList<Product> products = new ArrayList(0);

    ProductManager(ArrayList<Product> l ){
        products = l;
    }
    ProductManager(){

    }

    public void addProduct(Product p){
        products.add(p);
    }
    public void setProducts(ArrayList<Product> p){
        products = p;
    }

}
