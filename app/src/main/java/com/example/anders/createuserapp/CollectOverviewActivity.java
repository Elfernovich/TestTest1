package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


            listArtwork.add(new Artwork("So Simple", "Michael Kvium", R.drawable.so_simple));
            listArtwork.add(new Artwork("Social Dream Painting", "Michael Kvium", R.drawable.social_dream_painting));
            listArtwork.add(new Artwork("Kulturkreds", "Michael Kvium", R.drawable.kulturkreds));
            listArtwork.add(new Artwork("Naturkreds", "Michael Kvium", R.drawable.naturkreds));

            RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listArtwork);
            myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            myRecyclerView.setAdapter(myAdapter);
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


    protected void onData(DataSnapshot datasnapshot){

        final Boolean check_artwork_entered = true;
        String checked = (String) datasnapshot.child("users").child(""+userID).child("user_artwork1").getValue();
        if (check_artwork_entered.equals(checked)){

        }
        else{

        }
    }
}