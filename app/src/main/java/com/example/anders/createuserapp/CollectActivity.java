package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "CollectActivity";

    private FirebaseAuth mAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseArtworks;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    Button submitBtn;

    EditText inputAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);


        mAuth = FirebaseAuth.getInstance();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseArtworks = myFirebaseDatabase.getReference();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();


        inputAnswer = (EditText) findViewById(R.id.textViewArtworkCode);
        findViewById(R.id.btn_addCode).setOnClickListener(this);
        submitBtn = (Button) findViewById(R.id.btn_addCode);

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

    private void onData(DataSnapshot dataSnapshot) {
            final String passcode1 = inputAnswer.getText().toString();
            String artwork = (String) dataSnapshot.child("artworkss").child("artwork1").child("code").getValue();
            if (passcode1.equals(artwork)) {
                //do something
                Toast.makeText(getApplicationContext(),"Nice",Toast.LENGTH_LONG).show();


            } else if (!passcode1.equals(artwork)) {
                Toast.makeText(getApplicationContext(),"Not so nice",Toast.LENGTH_LONG).show();

            }
        }

    @Override
    public void onClick(View view) {


    }
}



