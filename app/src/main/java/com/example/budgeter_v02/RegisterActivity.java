package com.example.budgeter_v02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firestore.v1.FirestoreGrpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.security.auth.login.LoginException;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String TAG = "BudgeterAppTest";
    private FirebaseAuth mAuth;
    User db;
    int total = InfoActivity.getCurrentTotal();
    public static String name = "";
    private static String id;
    public String uId = "";
    public boolean finishedSetup = false;
    String pw, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new User("", 0, false);
        mAuth = FirebaseAuth.getInstance();
        configureRegisterContinueButton();

    }

    private void configureRegisterContinueButton() {
        final Button registerContinueButton = findViewById(R.id.RegisterContinue_Button);
        final EditText emailTextBox = findViewById(R.id.editText);
        final EditText passwordTextBox = findViewById(R.id.editText2);
        final EditText nameTextBox = findViewById(R.id.editText3);

        registerContinueButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting variables from text
                email = emailTextBox.getText().toString();
                pw = passwordTextBox.getText().toString();
                name = nameTextBox.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    uId = mAuth.getCurrentUser().getUid();
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");


                                    db.setId(uId);
                                    db.writeNewUser(name, InfoActivity.getCurrentTotal(), finishedSetup);

                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                }
                            }
                        });
            }
        });
    }

}


