package com.example.budgeter_v02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Member;

public class MembersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
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
