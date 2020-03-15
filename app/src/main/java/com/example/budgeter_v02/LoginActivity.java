package com.example.budgeter_v02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "BudgeterLogin";
    private FirebaseAuth mAuth;
    public String uID = "";
    public Boolean setupComplete;
    private FirebaseDatabase mDatabase;
    private UserLookup userLookup;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Getting instance of FireBase user
        mAuth = FirebaseAuth.getInstance();
        // Initialise UI elements
        configureLoginContinueButton();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        userLookup = new UserLookup();


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

    private void configureLoginContinueButton() {
        final Button loginContinueButton = findViewById(R.id.loginContinue_Button);
        final EditText emailText = findViewById(R.id.LoginEmail_EditText);
        final EditText passwordText = findViewById(R.id.LoginPassword_EditText);

        loginContinueButton.setOnClickListener(new View.OnClickListener() {
            String pw;
            String email;

            @Override
            public void onClick(View v) {
                pw = passwordText.getText().toString();
                email = emailText.getText().toString();
                // Use email and password to attempt to sign in with Firebase
                mAuth.signInWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    uID = mAuth.getCurrentUser().getUid();
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");

                                    if(userLookup.isSetupComplete()) {
                                        startActivity(new Intent(LoginActivity.this, MembersActivity.class));
                                    } else {
                                        startActivity(new Intent(LoginActivity.this, SetupActivity.class));
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                }
                            }
                        });
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            UserLookup userLookup = new UserLookup();
            userLookup.setSetupComplete(ds.child(uID).getValue(UserLookup.class).isSetupComplete());

            setupComplete = userLookup.isSetupComplete();
        }
    }

}

