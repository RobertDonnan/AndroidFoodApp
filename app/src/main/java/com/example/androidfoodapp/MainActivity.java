package com.example.androidfoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //variables for buttons
    Button btnSignIn;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(view -> {

        });

        btnSignUp.setOnClickListener(view -> {
            Intent signUp = new Intent(MainActivity.this, Signup.class);
            startActivity(signUp);
        });

        btnSignIn.setOnClickListener(view -> {
           Intent signIn = new Intent(MainActivity.this, Signin.class);
           startActivity(signIn);
        });










    }
}