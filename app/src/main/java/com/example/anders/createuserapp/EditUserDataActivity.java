package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class EditUserDataActivity extends AppCompatActivity {
    private static final String TAG = "EditUserDataActivity";

    //Add Firebase database/authenticator stuff
    private FirebaseAuth mAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseUsers;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private ListView mListView;

    // UI references
    EditText email, firstName, lastName, memberNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);

        mAuth = FirebaseAuth.getInstance();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseUsers = myFirebaseDatabase.getReference("");//.child("users");
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.listViewTest);
/*        email = (EditText) findViewById(R.id.textViewEditEmail);
        firstName = (EditText) findViewById(R.id.textViewEditFirstName);
        lastName = (EditText) findViewById(R.id.textViewEditLastName);
        memberNumber = (EditText) findViewById(R.id.textViewEditMemberNumber);*/


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

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              // This method is called once with the initial value and again
              // whenever data at this location is updated.
              showData(dataSnapshot);  
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        // Datasnapshot takes a snapshot of the entie database, and then iterate through the
        // database and pick the data of the current logged in user
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User userInfo = new User();
            userInfo.setEmail(ds.child(userID).getValue(User.class).getEmail()); // set the email
            userInfo.setFirstName(ds.child(userID).getValue(User.class).getFirstName()); // set the first name
            userInfo.setLastName(ds.child(userID).getValue(User.class).getLastName()); // set the last name
            userInfo.setMemberNumber(ds.child(userID).getValue(User.class).getMemberNumber()); // set the member number

            // display all the information
            Log.d(TAG, "showData: email: " + userInfo.getEmail());
            Log.d(TAG, "showData: firstName: " + userInfo.getFirstName());
            Log.d(TAG, "showData: lastName: " + userInfo.getLastName());
            Log.d(TAG, "showData: memberNumber: " + userInfo.getMemberNumber());

            ArrayList<String> array = new ArrayList<>();
            array.add(userInfo.getEmail());
            array.add(userInfo.getFirstName());
            array.add(userInfo.getLastName());
            array.add(userInfo.getMemberNumber());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mListView.setAdapter(adapter);

/*            email.setText(userInfo.email);
            firstName.setText(userInfo.firstName);
            lastName.setText(userInfo.lastName);
            memberNumber.setText(userInfo.memberNumber);*/

        }
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
}
