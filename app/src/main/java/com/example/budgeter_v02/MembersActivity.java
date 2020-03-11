package com.example.budgeter_v02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Member;

public class MembersActivity extends AppCompatActivity {
    private String TAG = "Budgeter";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        // Checking if user is logged in before page is viewed
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            startActivity(new Intent(MembersActivity.this, MainActivity.class));
            Log.d(TAG,"USER IS CURRENTLY NOT LOGGED IN");
        } else
        {
            // If there is a user login take them to member activity
            Log.d(TAG, "USER IS CURRENTLY LOGGED IN");
        }


        // Initialising UI elements
        configureLogoutButton();
    }
    // Configuring logout button
    private void configureLogoutButton() {
        final Button logoutButton = findViewById(R.id.MembersLogout_Button);
        // When logout button is clicked
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out of firebase.auth
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MembersActivity.this, MainActivity.class));
            }
        });
    }
}
