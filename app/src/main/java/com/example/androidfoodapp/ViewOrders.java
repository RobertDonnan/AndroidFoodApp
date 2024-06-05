package com.example.androidfoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidfoodapp.UserVariables.UVariables;
import com.example.androidfoodapp.ViewHolder.OrderViewHolder;
import com.example.androidfoodapp.model.Packages;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewOrders extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Packages, OrderViewHolder> adapter;

    FirebaseDatabase fdatabase;
    DatabaseReference references;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        //Connection to firebase database to retrieve data created once order was placed
        fdatabase = FirebaseDatabase.getInstance();
        references = fdatabase.getReference("Packages");



        recyclerView = (RecyclerView) findViewById(R.id.previousOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        pullOrders(UVariables.activeUser.getPhone());


    }

    public void pullOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<Packages, OrderViewHolder>(
                Packages.class,
                R.layout.previous_orders_layout,
                OrderViewHolder.class,
                references.orderByChild("phone").equalTo(phone)//as each user has their own number, orders are ordered by phone so they are unique to that customer
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Packages reference, int i) {
                orderViewHolder.txtOrderName.setText(reference.getName());
                orderViewHolder.txtOrderPhone.setText(reference.getPhone());
                orderViewHolder.txtOrderpord.setText(reference.getPord());
                orderViewHolder.txtOrderAddress.setText(reference.getAddress());
                orderViewHolder.txtOrderpicktime.setText(reference.getTime());
                orderViewHolder.txtOrderpickdate.setText(reference.getDate());
            }


        };
        recyclerView.setAdapter(adapter);
    }
}

