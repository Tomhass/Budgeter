package com.example.budgeter_v02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.view.View.VISIBLE;

public class MembersActivity extends AppCompatActivity {
    private String TAG = "Budgeter";
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private UserLookup userLookup;

    String uId;
    String currentItem;
    Boolean setupComplete;
    int currentTotal;
    int amount;

    // List of items for Expenses screen
    // Adapter for handling listview data
    ArrayAdapter<String> adapter;

    ArrayList<String> listItems = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        uId = mAuth.getCurrentUser().getUid();

        ListView list = (ListView) findViewById(R.id.ListView);
        final TextView currentMoney = (TextView) findViewById(R.id.funds_TextView);
        currentMoney.setText(String.valueOf(InfoActivity.getCurrentTotal()));
        currentTotal = InfoActivity.getCurrentTotal();

        adapter = new ArrayAdapter<String>(MembersActivity.this,android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);


        if(uId == null)
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
        configureShowExpensesButton();
        configureAddExpensesButton();


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
    // Configuring add expenses button
    private void configureAddExpensesButton() {
        final Button addExpensesButton = findViewById(R.id.Expense_Button);
        final TextView currentMoney = (TextView) findViewById(R.id.funds_TextView);

        addExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create dialogue builder
                AlertDialog.Builder builder = new AlertDialog.Builder(MembersActivity.this);
                builder.setTitle("What was this expenditure?");
                // Setup of Input box
                final EditText input = new EditText(MembersActivity.this);
                builder.setView(input);
                input.setInputType(InputType.TYPE_CLASS_TEXT);

                // Dialogue buttons
                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String output = input.getText().toString();

                        if(output.equals("")) {
                            dialog.cancel();
                        } else {
                            currentItem = output;
                        }
                        // Create dialogue builder
                        AlertDialog.Builder builder = new AlertDialog.Builder(MembersActivity.this);
                        builder.setTitle("How much was this expenditure?");
                        // Setup of Input box
                        final EditText input2 = new EditText(MembersActivity.this);
                        builder.setView(input2);
                        input2.setInputType(InputType.TYPE_CLASS_NUMBER);

                        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String output2 = input2.getText().toString();

                                if (output2.equals("")) {
                                    dialog.cancel();
                                } else {
                                    amount = Integer.parseInt(output2);
                                    // Setting funds left
                                    currentTotal = currentTotal - amount;
                                    currentMoney.setText(String.valueOf(currentTotal));
                                    InfoActivity.setCurrentTotal(currentTotal);
                                    adapter.add(currentItem + "  -" + Integer.toString(amount));

                                }

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Render dialogue box
                builder.show();
            }

        });
    }
    // Configuring show expenses button
    private void configureShowExpensesButton() {
        final Button showExpensesButton = findViewById(R.id.Expense_Button);
        final ListView listview = findViewById(R.id.ListView);
        showExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listview.getVisibility() == VISIBLE) {
                    listview.setVisibility(View.GONE);

                } else {
                    // Create dialogue builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(MembersActivity.this);
                    builder.setTitle("View your list of expenditures?");
                    // Dialogue buttons
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.add("Test");
                            listview.setVisibility(VISIBLE);

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    // Render dialogue box
                    builder.show();
                    listview.setVisibility(VISIBLE);
                }
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {

        }
    }

}
