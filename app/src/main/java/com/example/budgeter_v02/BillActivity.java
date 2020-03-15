package com.example.budgeter_v02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BillActivity extends AppCompatActivity {
    // Variables
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    int currentMoney, costHolder;
    private UserLookup userlookup;
    User user;

    public String uId;

    int electricity,  gas,  telephone, broadband, mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        mAuth = FirebaseAuth.getInstance();
        currentMoney = InfoActivity.getCurrentTotal();


        final TextView currentMoney_TextView = (TextView) findViewById(R.id.currentMoney_TextView);
        currentMoney_TextView.setText(String.valueOf(InfoActivity.getCurrentTotal()));

        configureBroadband();
        configureElectricity();
        configureGas();
        configureMobilePhone();
        configureTelephone();
        configureContinueButton();
    }

    public void update(String id, String name, int total, Boolean finishedSetup) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mDatabase.getReference("users").child(uId);

        User user = new User(name, total, finishedSetup);
        myRef.setValue(user);
    }

    public void configureContinueButton() {
        final Button continueButton = (Button) findViewById(R.id.BillsContinue_Button);
        final EditText electricityEditText = (EditText) findViewById(R.id.Electricity_EditText);
        final EditText gasEditText = (EditText) findViewById(R.id.Gas_EditText);
        final EditText telephoneEditText = (EditText) findViewById(R.id.Telephone_EditText);
        final EditText broadbandEditText = (EditText) findViewById(R.id.Broadband_EditText);
        final EditText mobileEditText = (EditText) findViewById(R.id.MobilePhone_EditText);
        final TextView currentMoneyText = (TextView) findViewById(R.id.currentMoney_TextView);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this);
                builder.setTitle("Are you sure you want to continue?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    uId = mAuth.getCurrentUser().getUid();
                    Log.d("TAG", Integer.toString(currentMoney));

                    update(uId, RegisterActivity.name, currentMoney, true);



                    startActivity(new Intent(BillActivity.this, MembersActivity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Render dialogue box
                builder.show();
            }
        });
    }

    // Methods for taking away bill from current total
    public void configureElectricity() {
        final EditText electricity_EditText = (EditText) findViewById(R.id.Electricity_EditText);
        final TextView currentMoney_Text = (TextView) findViewById(R.id.currentMoney_TextView);

        electricity_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    costHolder = Integer.parseInt(s.toString());
                    currentMoney = currentMoney - costHolder;
                    currentMoney_Text.setText(String.valueOf(currentMoney));
                }
            }
        });
    }

    // Methods for taking away bill from current total
    public void configureGas() {
        final EditText gas_EditText = (EditText) findViewById(R.id.Gas_EditText);
        final TextView currentMoney_Text = (TextView) findViewById(R.id.currentMoney_TextView);

        gas_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    costHolder = Integer.parseInt(s.toString());
                    currentMoney = currentMoney - costHolder;
                    currentMoney_Text.setText(String.valueOf(currentMoney));
                }
            }
        });
    }
    // Methods for taking away bill from current total
    public void configureBroadband() {
        final EditText broadband_EditText = (EditText) findViewById(R.id.Broadband_EditText);
        final TextView currentMoney_Text = (TextView) findViewById(R.id.currentMoney_TextView);

        broadband_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    costHolder = Integer.parseInt(s.toString());
                    currentMoney = currentMoney - costHolder;
                    currentMoney_Text.setText(String.valueOf(currentMoney));
                }
            }
        });
    }
    // Methods for taking away bill from current total
    public void configureTelephone() {
        final EditText telephone_EditText = (EditText) findViewById(R.id.Telephone_EditText);
        final TextView currentMoney_Text = (TextView) findViewById(R.id.currentMoney_TextView);

        telephone_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    costHolder = Integer.parseInt(s.toString());
                    currentMoney = currentMoney - costHolder;
                    currentMoney_Text.setText(String.valueOf(currentMoney));
                }
            }
        });
    }
    // Methods for taking away bill from current total
    public void configureMobilePhone() {
        final EditText mobilePhone_EditText = (EditText) findViewById(R.id.MobilePhone_EditText);
        final TextView currentMoney_Text = (TextView) findViewById(R.id.currentMoney_TextView);

        mobilePhone_EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s != null) {
                    costHolder = Integer.parseInt(s.toString());
                    currentMoney = currentMoney - costHolder;
                    currentMoney_Text.setText(String.valueOf(currentMoney));
                }
            }
        });
    }

};

