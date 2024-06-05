package com.example.androidfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.androidfoodapp.database.Database;
import com.example.androidfoodapp.model.Item;
import com.example.androidfoodapp.model.Orders;
import com.example.androidfoodapp.ui.basket.Basket;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ItemInfo extends AppCompatActivity  {

    //Declaring Variables
    TextView productName;
    TextView productDescription;
    ImageView productImage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnBasket;
    ElegantNumberButton numberButton;

    ImageButton btnViewBasket;

    String ItemsId="";

    FirebaseDatabase fdatabase;
    DatabaseReference items;

    Item currentItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        //Firebase database connection
        fdatabase = FirebaseDatabase.getInstance();
        items = fdatabase.getReference("Items");//table reference

        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        btnBasket = (FloatingActionButton) findViewById(R.id.btnBasket);
        btnViewBasket = (ImageButton) findViewById(R.id.btnbasket);


        btnViewBasket.setOnClickListener(view -> {
            Intent orderIntent = new Intent(ItemInfo.this, Basket.class);
            startActivity(orderIntent);
        });


        btnBasket.setOnClickListener(view -> {
            new Database(getBaseContext()).addDataToBasket(new Orders(
                    ItemsId,
                    currentItem.getName(),
                    numberButton.getNumber()

            ));

            Toast.makeText(ItemInfo.this, "Added to Basket", Toast.LENGTH_SHORT).show();
        });

        productDescription = (TextView) findViewById(R.id.productDescription);
        productName = (TextView) findViewById(R.id.product_name);
        productImage = (ImageView) findViewById(R.id.image_item);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

     if(getIntent() != null)
       ItemsId = getIntent().getStringExtra("ItemsID");
      if(!ItemsId.isEmpty())
     {
         getDetailItem(ItemsId);
     }

    }

    private void getDetailItem(String ItemsId) {
        items.child(ItemsId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentItem = snapshot.getValue(Item.class);

               Picasso.get().load(currentItem.getImage()).into(productImage);

               collapsingToolbarLayout.setTitle(currentItem.getName());

               productName.setText(currentItem.getName());

               productDescription.setText(currentItem.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}