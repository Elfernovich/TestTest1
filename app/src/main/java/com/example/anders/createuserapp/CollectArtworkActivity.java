package com.example.anders.createuserapp;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CollectArtworkActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "CollectArtworkActivity";
//POGGERS
    private FirebaseAuth mAuth;
    private FirebaseDatabase myFirebaseDatabase;
    private DatabaseReference databaseUsers;
    private DatabaseReference databaseArtworks;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private int pointsint;
    private boolean checked_artwork;

    private TextView textViewTitle, textViewArtist;
    private ImageView img, img_reward;
    private EditText inputAnswer;
    Button submitBtn;
    String artwork_name;
    int image_notification;
    int currentpoints;
    int adding_points_from_artwork = 10;
    boolean checked;
    //Notification
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_artwork);

        mAuth = FirebaseAuth.getInstance();
        myFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseArtworks = myFirebaseDatabase.getReference();
        databaseUsers = myFirebaseDatabase.getReference();
        FirebaseUser usersID = mAuth.getCurrentUser();
        userID = usersID.getUid();

        textViewTitle = (TextView) findViewById(R.id.txtTitle);
        textViewArtist = (TextView) findViewById(R.id.txtArtist);
        img = (ImageView) findViewById(R.id.artworkThumbnail);
        inputAnswer = (EditText) findViewById(R.id.editTextArtworkCode);
        submitBtn = (Button) findViewById(R.id.btn_enter_art_code);
        linearLayout = (LinearLayout) findViewById(R.id.collect_artwork_linearlayout);
        img_reward = (ImageView) findViewById(R.id.img_completed_artwork);

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


        databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                calculatedPoints(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                check_database_value(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Receive data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("ARTWORK_TITLE");
        String Artist = intent.getExtras().getString("ARTWORK_ARTIST");
        int image = intent.getExtras().getInt("THUMBNAIL");

        //Set values
        textViewTitle.setText(Title);
        textViewArtist.setText(Artist);
        img.setImageResource(image);
        artwork_name = Title;
        image_notification = image;


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



    //If the user is not logged in he will be directed to the MainActivity.class
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

//
    public void check_database_value(DataSnapshot dataSnapshot) {

        boolean check = (boolean) dataSnapshot.child("users").child(userID).child("user_artwork1").getValue();
        checked = check;
        checked_artwork = checked;

        Intent i = new Intent(CollectArtworkActivity.this, RecyclerViewAdapter.class);
        i.putExtra("key",check);
        startActivity(i);
    }

    public void calculatedPoints(DataSnapshot dataSnapshot){

        String points = (String) dataSnapshot.child("users").child(userID).child("points").getValue().toString();
        pointsint = Integer.parseInt(points);
        currentpoints = pointsint+adding_points_from_artwork;
    }

    private void addPoints() {
        databaseUsers.child("users").child(userID).child("points").setValue(currentpoints);
    }

    private void changeArtworkvalue() {
        databaseUsers.child("users").child(userID).child("user_artwork1").setValue(false);
    }

    protected void onData(DataSnapshot dataSnapshot) {
        boolean database_value = checked_artwork;
        final String passcode1 = inputAnswer.getText().toString().trim();
        String artwork = (String) dataSnapshot.child("artworkss").child(""+artwork_name).child("code").getValue();
        if ((passcode1.equals(artwork)) && (database_value == true)) {
            addPoints();
            changeArtworkvalue();
            //do something
            layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.notification_layout,null);
            popupWindow = new PopupWindow(container, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
            //Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_LONG).show();

            LinearLayout linearLayout = (LinearLayout) container.findViewById(R.id.linear_layout);

            linearLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    popupWindow.dismiss();
                    Intent intent = new Intent(view.getContext(), CollectOverviewActivity.class);
                    startActivity(intent);
                }
            });

/*            container.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent){
                    popupWindow.dismiss();
                    return true;
                }
            });*/


        } else if (!passcode1.equals(artwork)) {
            Toast.makeText(getApplicationContext(), "Not so nice", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onClick(View v) {

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
