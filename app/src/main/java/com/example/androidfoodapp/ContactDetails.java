package com.example.androidfoodapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ContactDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

//Code used too add images to carousel
        ImageSlider carousel = findViewById(R.id.carousel);

        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://i.ibb.co/Y2m8WrS/women-at-food-bank.png"));
        slideModels.add(new SlideModel("https://i.ibb.co/D1HDLcz/smiling-food-bank-family.png"));
        slideModels.add(new SlideModel("https://i.ibb.co/y5QHQX2/smiling-food-bank-child.png"));

        carousel.setImageList(slideModels, true);


        //embedding map
        ImageView map;
        map = (ImageView) findViewById(R.id.map);
        map.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://goo.gl/maps/1tezFZh4MCVwFXih8");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
    }
}
