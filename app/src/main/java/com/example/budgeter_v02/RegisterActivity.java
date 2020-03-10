package com.example.budgeter_v02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firestore.v1.FirestoreGrpc;

import java.util.Set;

import javax.security.auth.login.LoginException;

public class RegisterActivity extends AppCompatActivity {

    private String TAG = "BudgeterAppTest";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        configureRegisterContinueButton();

    }

    private void configureRegisterContinueButton() {
        final Button registerContinueButton = findViewById(R.id.RegisterContinue_Button);
        final EditText emailTextBox = findViewById(R.id.editText);
        final EditText passwordTextBox = findViewById(R.id.editText2);
        final EditText nameTextBox = findViewById(R.id.editText3);

        registerContinueButton.setOnClickListener(new Button.OnClickListener() {
            String email;
            String pw;
            String name;

            @Override
            public void onClick(View v) {
                email = emailTextBox.getText().toString();
                pw = passwordTextBox.getText().toString();
                name = nameTextBox.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    startActivity(new Intent(RegisterActivity.this, SetupActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());

                                    Toast.makeText(getApplicationContext(),"WHATS UP",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}
