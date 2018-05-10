package com.example.anders.createuserapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.google.firebase.internal.FirebaseAppHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {
    private static final String TAG = "LeaderboardActivity";

    BottomNavigationView bottomNavigationView;
    ListView leaderboardName, leaderboardScore;
    private String userID;
    TextView test;
    final List<User> score = new ArrayList<User>();

    //array adapter
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<Integer> arrayList2 = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    Query databaseQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Log.d(TAG, "onCreate: Started.");

        leaderboardName = (ListView) findViewById(R.id.listViewLeaderboardName);
        leaderboardScore = (ListView) findViewById(R.id.listViewLeaderboardScore);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        databaseQuery = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("points").limitToLast(5);

        final ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        final ArrayAdapter<Integer> userAdapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arrayList2);
        leaderboardName.setAdapter(userAdapter);
        leaderboardScore.setAdapter(userAdapter2);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);




        databaseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.child("firstName").getValue(String.class);
                Integer points = dataSnapshot.child("points").getValue(Integer.class);
                arrayList.add(value);
                arrayList2.add(points);

                userAdapter.notifyDataSetChanged();
                userAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Toolbar at the top
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_profile:
                        Intent intent1 = new Intent(LeaderboardActivity.this, ProfileActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent1);
                        //activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;

                    case R.id.id_collect:
                        Intent intent2 = new Intent(LeaderboardActivity.this, CollectOverviewActivity.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent2);
                        //activity.startActivity(new Intent(activity, ProfileActivity.class));
                        break;

                    case R.id.id_reward:
                        Intent intent3 = new Intent(LeaderboardActivity.this, LeaderboardActivity.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent3);
                        //mIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        break;
                }
                return false;
            }
        });

    }
      /*  databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get map of users in datasnapshot
                //collectScores((Map<Integer, Object>) dataSnapshot.getValue());

                String string = dataSnapshot.getValue(String.class);
                arrayList.add(string);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void collectScores(Map<Integer, Object> users){
        ArrayList<Long> scores = new ArrayList<>();

        for (Map.Entry<Integer, Object> entry : users.entrySet()){
            //Get user map
            Map singleUser = (Map) entry.getValue();
            //get points field and append to list
            scores.add((Long)singleUser.get("points"));

        }

        System.out.println(scores);
        test.setText(scores.toString());


    }*/




    //If the user is not logged in he will be directed to the MainActivity.class
    @Override
    protected void onStart() {
        super.onStart();

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
