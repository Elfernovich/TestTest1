package com.example.anders.createuserapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserDataActivity extends AppCompatActivity implements View.OnClickListener{

    EditText firstName;
    EditText lastName;
    EditText memberNumber;
    private String userID;
    //int points;


    DatabaseReference databaseUsers;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        databaseUsers = FirebaseDatabase.getInstance().getReference("");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstName = (EditText) findViewById(R.id.textViewEnterFirstName);
        lastName = (EditText) findViewById(R.id.textViewEnterLastName);
        memberNumber = (EditText) findViewById(R.id.textViewEnterMemberNumber);


        findViewById(R.id.btn_Save_Data).setOnClickListener(this);

    }

    //If the user is not logged in he will be directed to the MainActivity.class
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void addUser() {
        FirebaseUser user_Email = mAuth.getCurrentUser();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        String first_Name = firstName.getText().toString().trim();
        String last_Name = lastName.getText().toString().trim();
        String member_Number = memberNumber.getText().toString().trim();
        //points = 0;


        if (!TextUtils.isEmpty(first_Name) && (!TextUtils.isEmpty(last_Name))) {

            String displayEmail = user_Email.getEmail();
            String displayUserID = userID;
            int points = 0;
            int points_handler = 10000;
            Boolean artwork1 = true;
            Boolean artwork2 = true;
            Boolean artwork3 = true;
            Boolean artwork4 = true;
            Boolean artwork5 = true;

            User user = new User(displayEmail, first_Name, last_Name, member_Number, points, points_handler, artwork1, artwork2, artwork3, artwork4, artwork5);
            databaseUsers.child("users").child(displayUserID).setValue(user);
            //databaseUsers.setValue(user);

            Toast.makeText(this, "Din profil er opdateret", Toast.LENGTH_SHORT).show();
            finish();
            Intent intent = new Intent(UserDataActivity.this, ProfileActivity.class);
            //Prevent the user to be able to go back to the login activity while pressing "back" button
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Udfyld fornavn og efternavn!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        addUser();

    }


/*    private void addUser(){
        String first_Name = firstName.getText().toString().trim();
        String last_Name = lastName.getText().toString().trim();
        String member_Number = memberNumber.getText().toString().trim();
        FirebaseUser user_Email = mAuth.getCurrentUser();

        if(!TextUtils.isEmpty(first_Name) && (!TextUtils.isEmpty(last_Name))) {

            String displayEmail = user_Email.getEmail();

            User user = new User(displayEmail, first_Name, last_Name, member_Number);

            databaseUsers.child(displayEmail).setValue(user);

            Toast.makeText(this, "Din profil er opdateret", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Udfyld fornavn og efternavn!", Toast.LENGTH_LONG).show();
        }
    }*/


}
