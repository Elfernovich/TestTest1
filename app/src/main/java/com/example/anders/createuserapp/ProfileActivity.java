package com.example.anders.createuserapp;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseUsers;
    TextView email, progres;
    ProgressBar prgBar;
    private String userID;
    private int currentpoints;
    int points = 0;
    BottomNavigationView bottomNavigationView;
    Query databaseQuery;
    private ArrayList<Integer> pointArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();


        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseQuery = myFirebaseDatabase.getReference().child("users");
        final FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = (TextView) findViewById(R.id.textViewEmail);
        progres = (TextView) findViewById(R.id.textview_progress);

        prgBar = (ProgressBar) findViewById(R.id.progressBar);

        loadUserInformation();


        databaseQuery.addValueEventListener (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int points = dataSnapshot.child(userID).child("points").getValue(int.class);
                currentpoints = currentpoints+points;
                pointArray.add(points);
                Log.d(TAG,"Value"+currentpoints);
                setProgressBar();
                points_for_progress();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                    //String userID = user.getUid();
                }
            }
        };


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
                            Intent intent2 = new Intent(ProfileActivity.this, CollectOverviewActivity.class);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent2);
                            finish();
                            //activity.startActivity(new Intent(activity, ProfileActivity.class));
                            break;

                        case R.id.id_reward:
                            Intent intent3 = new Intent (ProfileActivity.this, LeaderboardActivity.class);
                            intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent3);
                            finish();
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
    protected void onPause() {
        super.onPause();
        //databaseQuery.removeEventListener((ValueEventListener) this);
    }

    //Load the users e-mail from Firebase Auth
    private void loadUserInformation(){
        FirebaseUser user = mAuth.getCurrentUser();

        String displayEmail = user.getEmail();
        email.setText("Velkommenn " + displayEmail+"!");
    }

    public void points_for_progress(){
        String helper = Integer.toString(currentpoints);
        progres.setText(helper+"/100 Point");
    }

    public void setProgressBar(){
        //currentpoints = pointArray.get(0);
        prgBar.setProgress(currentpoints);
    }

    public void BtnArtworkOverviewActivity (View view) {
        Intent intent = new Intent(ProfileActivity.this, CollectOverviewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    //Inflate the top toolbar with a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    //Functions to the menus in the top toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent (this, MainActivity.class));
        }
        return true;
    }
    }

