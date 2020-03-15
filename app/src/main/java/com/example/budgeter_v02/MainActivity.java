package com.example.budgeter_v02;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
// Google
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private UserLookup userLookup;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    public Boolean setupComplete;

    String uID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLookup = new UserLookup();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();



        // Checking if user is logged in before page is viewed
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            Log.d(TAG,"USER IS CURRENTLY NOT LOGGED IN");
        } else
        {
            uID = mAuth.getCurrentUser().getUid();

            if(userLookup.isSetupComplete()) {
                startActivity(new Intent(MainActivity.this, MembersActivity.class));
                Log.d(TAG, "USER IS CURRENTLY LOGGED IN AND HAS COMPLETED SETUP");
            } else {
                startActivity(new Intent(MainActivity.this, SetupActivity.class));
            }

            // If there is a user login take them to member activity
            Log.d(TAG, "USER IS CURRENTLY LOGGED IN");
            startActivity(new Intent(MainActivity.this, MembersActivity.class));
        }
        // Initialising UI elements
        configureLoginButton();
        configureRegisterButton();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void configureLoginButton(){
        Button loginButton = findViewById(R.id.Login_Btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
    private void configureRegisterButton(){
        Button registerButton = findViewById(R.id.Register_Btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            UserLookup userLookup = new UserLookup();
            if(uID == null)
            {
                Log.d("showData", "USER HAS NOT COMPLETED SETUP OR IS A NEW USER");
            } else {
                userLookup.setSetupComplete(ds.child(uID).getValue(UserLookup.class).isSetupComplete());
            }

            setupComplete = userLookup.isSetupComplete();
        }
    }
}
