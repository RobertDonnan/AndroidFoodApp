package com.example.androidfoodapp.ui.basket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.androidfoodapp.UserVariables.UVariables;
import com.example.androidfoodapp.R;
import com.example.androidfoodapp.ViewOrders;
import com.example.androidfoodapp.database.Database;
import com.example.androidfoodapp.model.Orders;
import com.example.androidfoodapp.model.Packages;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class 
Basket extends AppCompatActivity {
    
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase fdatabase;
    DatabaseReference references;
    FButton btnMake;
    FButton btnView;
    List<Orders> basket = new ArrayList<>();
    BasketAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        //Firebase database connectivity
        fdatabase = FirebaseDatabase.getInstance();
        references = fdatabase.getReference("Packages");

//Used to get the current date so user will pick another date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        //gets current time
        String date = sdf.format(c.getTime());
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

        recyclerView = (RecyclerView) findViewById(R.id.listBasket);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btnMake = (FButton) findViewById(R.id.btnMakeOrder);
        btnView = (FButton) findViewById(R.id.btnViewOrders);


        btnView.setOnClickListener(view -> {
            Intent orderIntent = new Intent(Basket.this, ViewOrders.class);
            startActivity(orderIntent);
        });

        //declaring variables for pickup or delivery and address, date and time values for user to enter
        EditText edtaddress;
        EditText edtpickdate;



        //as done before, finding the variables to the xml item so items added by the user will
        //get stored successfully
        Spinner dropdownpord = findViewById(R.id.edtpord);
        Spinner dropdowntime = findViewById(R.id.edttime);
        edtaddress = (EditText) findViewById(R.id.edtaddress);
        edtpickdate = (EditText) findViewById(R.id.edtdate);



        //pairing the items to the variables declared above so data is ready to be stored
        EditText finalEdtaddress = edtaddress;
        EditText finalEdtpickdate = edtpickdate;

        //code used for spinners, adding items as a String array
       String[] pord = new String[]{"Pickup", "Delivery"};
       ArrayAdapter<String> pordadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, pord);
        dropdownpord.setAdapter(pordadapter);
        String[] time = new String[]{"09:00", "09:30", "10:00", "10:30", "11:00", "11:35","11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        ArrayAdapter<String> timeadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, time);
        dropdowntime.setAdapter(timeadapter);
        //when user presses the make an order button it stores the info into the firebase database
        //the code before the end of the function makes sure of this by setting the values into the
        //reference table created when orders are made and currenttimemillis is used to save and store the
        //info in real time.
        btnMake.setOnClickListener(view -> {
            Packages reference = new Packages(
                    UVariables.activeUser.getPhone(),
                    UVariables.activeUser.getName(),
                    dropdownpord.getSelectedItem().toString(),
                    finalEdtaddress.getText().toString(),
                    finalEdtpickdate.getText().toString(),
                    dropdowntime.getSelectedItem().toString(),
                    basket
            );
//if else statement showing toast message for user if they enter their pickup/delivery time to be right now, save method
            //is stored in else statement to avoid user being able to save information if they enter an invalid date.
                if(dropdowntime.getSelectedItem().toString().equals(currentTime)&&(finalEdtpickdate.getText().toString().equals(date)))
                {
                  Toast.makeText(this, "Can't use current date and time!", Toast.LENGTH_SHORT).show();
               } else {
                references.child(String.valueOf(System.currentTimeMillis()))
            .setValue(reference);
                  new Database(getBaseContext()).clearBasket();
                  Toast.makeText(Basket.this, "Order Made Successfully", Toast.LENGTH_SHORT).show();
                  finish();
               }
           });
        loadListItems();
    }
    private void loadListItems() {
        basket = new Database(this).getBaskets();
        adapter = new BasketAdapter(basket,this);
        recyclerView.setAdapter(adapter);
    }
}