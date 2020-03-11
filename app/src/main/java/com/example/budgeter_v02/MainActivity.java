package com.example.budgeter_v02;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
// Google
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Checking if user is logged in before page is viewed
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null)
        {
            Log.d(TAG,"USER IS CURRENTLY NOT LOGGED IN");
        } else
        {
            // If there is a user login take them to member activity
            Log.d(TAG, "USER IS CURRENTLY LOGGED IN");
            startActivity(new Intent(MainActivity.this, MembersActivity.class));
        }
        // Initialising UI elements
        configureLoginButton();
        configureRegisterButton();
        configureCheatButton();
    }

    // Buttons for navigating. When button is pressed starts the next activity
    private void configureCheatButton() {
        Button cheatButton = findViewById(R.id.Cheat_Btn);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupActivity.class));
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
}
