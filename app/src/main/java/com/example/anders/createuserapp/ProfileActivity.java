package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (TextView) findViewById(R.id.textViewEmail);

        loadUserInformation();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent (this, MainActivity.class));

                break;
            case R.id.menuEditUserData:

                finish();
                startActivity(new Intent (this, EditUserDataActivity.class));
        }

        return true;
    }

    private void loadUserInformation(){
        FirebaseUser user = mAuth.getCurrentUser();

        String displayEmail = user.getEmail();
        email.setText("Du er logget ind som:\n" + displayEmail);
    }
}
