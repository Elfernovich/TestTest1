package com.example.anders.createuserapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    // Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        welcomeText = (TextView) findViewById(R.id.textView_Welcome);
    }

    //If the user already is logged in he will be directed to the ProfileActivity.class
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
        }

    }

public void welcome_Click(View view){
    finish();
    Intent i = new Intent (this, MainActivity.class);
    startActivity(i);
}
}
