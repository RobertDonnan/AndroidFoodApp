package com.example.androidfoodapp.ui.basket;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amulyakhare.textdrawable.TextDrawable;
import com.example.androidfoodapp.Interface.ItemClickListener;
import com.example.androidfoodapp.R;
import com.example.androidfoodapp.model.Orders;
import java.util.ArrayList;
import java.util.List;

class BasketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //declaring variables
    public TextView txt_item_name;
    public ImageView img_basket_count;

    private ItemClickListener itemClickListener;

    public void setTxt_item_name(TextView txt_item_name){
        this.txt_item_name = txt_item_name;
    }

    public BasketViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_item_name = (TextView) itemView.findViewById(R.id.basket_item_name);
        img_basket_count = (ImageView) itemView.findViewById(R.id.basket_item_count);
    }


    @Override
    public void onClick(View view) {

    }
}


public class BasketAdapter extends RecyclerView.Adapter<BasketViewHolder>{

    private List<Orders> listData = new ArrayList<>();
    private android.content.Context context;

    public BasketAdapter(List<Orders> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.basket_layout,parent,false);
        return new BasketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.img_basket_count.setImageDrawable(drawable);
        holder.txt_item_name.setText(listData.get(position).getItemName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
