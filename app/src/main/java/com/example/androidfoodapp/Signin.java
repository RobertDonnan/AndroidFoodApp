package com.example.androidfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfoodapp.UserVariables.UVariables;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.example.androidfoodapp.model.User;

public class Signin extends AppCompatActivity {

    //declaring variables
    EditText editPhone;
    EditText editPassword;

    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editPassword = findViewById(R.id.edtPassword);
        editPhone = findViewById(R.id.edtName);
        btnSignIn = findViewById(R.id.btnSignIn);

        //init firebase
       final FirebaseDatabase database = FirebaseDatabase.getInstance();//instance of database
        final DatabaseReference table_User = database.getReference("User");//reference to user field

        btnSignIn.setOnClickListener(view -> {
//displays a message asking the user to wait so they know activity is happening once the sign in button is pressed
            final ProgressDialog mDialog = new ProgressDialog((Signin.this));
            mDialog.setMessage("Please wait");
            mDialog.show();

            table_User.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange( DataSnapshot snapshot) {
                mDialog.dismiss();
                    //check to see if user doesn't already exist
                    if(snapshot.child(editPhone.getText().toString()).exists()) {
                        //get user data
                        User user = snapshot.child(editPhone.getText().toString()).getValue(User.class);
                        user.setPhone(editPhone.getText().toString());
                        if (user.getPassword().equals(editPassword.getText().toString())) {
                           Intent homeIntent = new Intent(Signin.this,HomePage.class);
                            UVariables.activeUser = user;
                            startActivity(homeIntent);
                            finish();;
                        } else {
                            Toast.makeText(Signin.this, "Sign in failed: Wrong password used", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Signin.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        );








    }
}