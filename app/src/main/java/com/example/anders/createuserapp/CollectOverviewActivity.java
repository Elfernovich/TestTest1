package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollectOverviewActivity extends AppCompatActivity {
    private static final String TAG = "CollectOverviewActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseUsers;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private boolean checked_artwork;
    BottomNavigationView bottomNavigationView;

    boolean checked;

    TextView editArtworkCode;

    List<Artwork> listArtwork;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_overview);





        mAuth = FirebaseAuth.getInstance();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseUsers = myFirebaseDatabase.getReference();//.child("users");
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                    //String userID = user.getUid();
                }
            }
        };


        listArtwork = new ArrayList<>();


            listArtwork.add(new Artwork("So Simple", "Michael Kvium","artwork1", R.drawable.sex_hype));
            listArtwork.add(new Artwork("Social Dream Painting", "Michael Kvium","artwork2", R.drawable.forar_sager));
            listArtwork.add(new Artwork("Kulturkreds", "Michael Kvium","artwork3", R.drawable.hverdags_ikoner));
            listArtwork.add(new Artwork("Naturkreds", "Michael Kvium","artwork4", R.drawable.labestift_bombemaskine));
            listArtwork.add(new Artwork("Naturkreds", "Michael Kvium","artwork4", R.drawable.ornens_ret));

            RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listArtwork);
            myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            myRecyclerView.setAdapter(myAdapter);


        //Toolbar at the top
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BottomNavigation Bar
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.id_profile:
                        Intent intent1 = new Intent(CollectOverviewActivity.this, ProfileActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent1);
                        //activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;

                    case R.id.id_collect:
                        Intent intent2 = new Intent(CollectOverviewActivity.this, CollectOverviewActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        //activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;

                    case R.id.id_reward:
                        Intent intent3 = new Intent (CollectOverviewActivity.this, LeaderboardActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        //mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        break;
                }
                return false;
            }
        });
        }





    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    //Inflate the top toolbar with a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
}