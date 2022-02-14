package com.guoyucao.cashierapp.history;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.guoyucao.cashierapp.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class History {
    String date;
    Product item;
    History(){
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public History(Product p){
        item = p;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = now.format(formatter);
        date = formattedDateTime;

    }

    public Product getItem(){
        return item;
    }
    public String getDate (){
        return date;
    }



}
