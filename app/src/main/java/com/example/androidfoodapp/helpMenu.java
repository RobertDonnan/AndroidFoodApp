package com.example.androidfoodapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.androidfoodapp.ui.basket.Basket;

public class helpMenu extends Activity implements onNavigationItemSelect {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_menu);
        Button btnCountry;
        btnCountry = findViewById(R.id.Sweden);


        btnCountry.setOnClickListener(view -> {
            Intent orderIntent = new Intent(helpMenu.this, HomePage.class);
            startActivity(orderIntent);

        });
    }
    @Override
    public void onNavigationItemSelect(@NonNull MenuItem item) {

    }
}

