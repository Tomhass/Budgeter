package com.example.budgeter_v02;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureLoginButton();
        configureRegisterButton();
        configureCheatButton();
    }

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
