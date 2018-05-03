package com.example.anders.createuserapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    TextView email;
    ProgressBar prgBar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (TextView) findViewById(R.id.textViewEmail);

        prgBar = (ProgressBar) findViewById(R.id.progressBar);


        loadUserInformation();
        prgBar.setProgress(80);



            //BottomNavigation Bar
            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.id_profile:
                            Intent intent1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent1);
                            //activity.startActivity(new Intent(activity, ProfileActivity.class));
                            break;

                        case R.id.id_collect:
                            Intent intent3 = new Intent (ProfileActivity.this, CollectOverviewActivity.class);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent3);
                            //mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            break;
                    }
                    return false;
                }
            });
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

    public void setProgressBar(){
        setProgress(80);
    }

    public void ButtonCollectActivity (View view) {
            finish();
        Intent i = new Intent (this, CollectOverviewActivity.class);
            startActivity(i);
        }
    }

