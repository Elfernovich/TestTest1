package com.example.anders.createuserapp;

import android.graphics.Point;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "CollectActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseArtworks;
    private DatabaseReference databaseUsers;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    Button submitBtn;
    Button btn_artwork;
    Button btn_artwork2;


    EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mAuth = FirebaseAuth.getInstance();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseArtworks = myFirebaseDatabase.getReference();
        databaseUsers = myFirebaseDatabase.getReference();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();






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


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseArtworks.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        onData(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    protected void onData(DataSnapshot dataSnapshot) {
            final String passcode1 = inputAnswer.getText().toString().trim();
            String artwork = (String) dataSnapshot.child("artworkss").child(""+artwork_string).child("code").getValue();
                if (passcode1.equals(artwork)) {
                    //do something                    Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_LONG).show();


                } else if (!passcode1.equals(artwork)) {
                    Toast.makeText(getApplicationContext(), "Not so nice", Toast.LENGTH_LONG).show();

                }
            }



    @Override
    public void onClick(View view) {


    }

    boolean identifier = false;
    String artwork_string = "";

    public void artwork1Select(View view) {
        selecting_artwork1();
    }


        public void artwork2Select(View view) {
        selecting_artwork2();
            }

    public void selecting_artwork1() {
        if(!identifier){
            artwork_string = "artwork1";
            Toast.makeText(getApplicationContext(), "Artwork1 selected", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Der sker ikke noget", Toast.LENGTH_LONG).show();
            artwork_string = "";
        }

    }

    public void selecting_artwork2() {
        if(!identifier){
            artwork_string = "artwork2";
            Toast.makeText(getApplicationContext(), "Artwork2 selected", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Der sker ikke noget", Toast.LENGTH_LONG).show();
            artwork_string = "";
        }

    }



    public void Display_selectedartwork(View view) {
        }

    }







