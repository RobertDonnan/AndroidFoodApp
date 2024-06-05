package com.example.androidfoodapp.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfoodapp.Interface.ItemClickListener;
import com.example.androidfoodapp.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //declaring variables
    public TextView itemName;
    public ImageView itemImage;

    private ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemsViewHolder( View itemView) {
        super(itemView);

        itemName = (TextView) itemView.findViewById(R.id.product_name);
        itemImage = (ImageView) itemView.findViewById(R.id.product_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
