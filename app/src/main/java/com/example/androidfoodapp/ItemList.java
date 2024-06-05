package com.example.androidfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidfoodapp.Interface.ItemClickListener;
import com.example.androidfoodapp.ViewHolder.ItemsViewHolder;
import com.example.androidfoodapp.model.Item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ItemList extends AppCompatActivity {

    //Declaring variables
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase fDatabase;
    DatabaseReference itemList;

    String CatId="";

    FirebaseRecyclerAdapter<Item, ItemsViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //Firebase database connection and reference to table that will be used
        fDatabase = FirebaseDatabase.getInstance();
        itemList = fDatabase.getReference("Items");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        if(getIntent() != null)
            CatId = getIntent().getStringExtra("Item_CategoryId");
        if(!CatId.isEmpty() && CatId != null){
            loadListProducts(CatId);
        }
    }

    private void loadListProducts(String catId) {
        adapter = new FirebaseRecyclerAdapter<Item, ItemsViewHolder>(Item.class,R.layout.product_item,ItemsViewHolder.class,itemList.orderByChild("CatId").equalTo(catId))
        {
            @Override
            protected void populateViewHolder(ItemsViewHolder itemsViewHolder, Item item, int i) {
                itemsViewHolder.itemName.setText(item.getName());
                Picasso.get().load(item.getImage()).into(itemsViewHolder.itemImage);

                final Item local = item;
                itemsViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Once an item is clicked on the item details page is displayed
                      Intent ItemInfo = new Intent(ItemList.this, ItemInfo.class);
                       ItemInfo.putExtra("ItemsID", adapter.getRef(position).getKey());//Uses Items table
                      startActivity(ItemInfo);


                        if (Boolean.parseBoolean(fDatabase.getReference("ItemsID = 6").toString())) {
                            Intent helpMenu = new Intent(ItemList.this, com.example.androidfoodapp.helpMenu.class);
                            startActivity(helpMenu);
                        }
                    }


                    });
            }
        };

        //Setting the adapter
        recyclerView.setAdapter(adapter);
    }

}