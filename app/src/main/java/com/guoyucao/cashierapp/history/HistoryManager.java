package com.guoyucao.cashierapp.history;

import java.util.ArrayList;

public class HistoryManager {
    ArrayList<History> histories = new ArrayList(0);

    public HistoryManager(){

    }

    public void addHistory(History h){
        histories.add(h);
    }

}
