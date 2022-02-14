package com.guoyucao.cashierapp.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guoyucao.cashierapp.R;

import java.util.ArrayList;

public class HistoryRecycleAdapter extends RecyclerView.Adapter<HistoryRecycleAdapter.MyViewHolder> {

    private ArrayList<History> historyList;
    private Context mContext;
    private ItemListener mItemListener;

    public interface ItemListener {
        void onItemClicked(History item, int position);
    }

    public HistoryRecycleAdapter(Context context,ArrayList<History> itemLst) {
        this.historyList = itemLst;
        mContext = context;
    }

    public void setItemListener(ItemListener mItemListener) {
        this.mItemListener = mItemListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.history_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(historyList.get(position).getItem().getName());
        holder.stock.setText(String.valueOf(historyList.get(position).getItem().getStock()));
        holder.price.setText(Double.toString(historyList.get(position).getItem().getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (mItemListener != null){
                mItemListener.onItemClicked(historyList.get(holder.getAdapterPosition()),holder.getAdapterPosition());
            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView productName, stock, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            stock = itemView.findViewById(R.id.stock);
            price = itemView.findViewById(R.id.price);

        }

        @Override
        public void onClick(View v) {


        }
    }

}
