package com.example.androidfoodapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfoodapp.Interface.ItemClickListener;
import com.example.androidfoodapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderPhone;
    public TextView txtOrderpord;

    public TextView txtOrderpickdate;

    public TextView txtOrderpicktime;
    public TextView txtOrderAddress;

    public TextView txtOrderName;
    private ItemClickListener itemClickListener;


    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);

        txtOrderPhone = (TextView) itemView.findViewById(R.id.order_phone);
        txtOrderpord = (TextView) itemView.findViewById(R.id.order_pord);
        txtOrderAddress = (TextView) itemView.findViewById(R.id.order_address);
        txtOrderpicktime = (TextView) itemView.findViewById(R.id.order_picktime);
        txtOrderpickdate = (TextView) itemView.findViewById(R.id.order_pickdate);
        txtOrderName = (TextView) itemView.findViewById(R.id.order_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(), false);
    }
}
