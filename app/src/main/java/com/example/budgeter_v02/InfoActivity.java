package com.example.budgeter_v02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    // Variables for database
    private static boolean train;
    private static boolean car;
    private static boolean bus;
    private static int currentTotal;


    // Methods for accessing above variables
    public static boolean getTrain() {
        return train;
    }
    public static boolean getCar() {
        return car;
    }
    public static boolean getBus() {
        return bus;
    }
    public static int getCurrentTotal() { return currentTotal; }
    public static int setCurrentTotal(int input) { currentTotal = input; return currentTotal; }

    private String text = "";
    int carCost, busCost, trainCost;
    int income, saving, total;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        income = SetupActivity.getIncome();
        saving = SetupActivity.getSaving();
        total = income - saving;

        configureTrainSwitch();
        configureCarSwitch();
        configureBusSwitch();
        final TextView currentMoney = (TextView) findViewById(R.id.currentMoney_TextView);
        currentMoney.setText(String.valueOf(total));

    }

    public void configureTrainSwitch() {
        final Switch trainSwitch = (Switch) findViewById(R.id.trainTravel_Switch);
        final TextView currentMoney = (TextView) findViewById(R.id.currentMoney_TextView);

        trainSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    // Create dialogue builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                    builder.setTitle("How much does your train cost per month?");
                    // Setup of Input box
                    final EditText input = new EditText(InfoActivity.this);
                    builder.setView(input);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);

                    // Dialogue buttons
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String output = input.getText().toString();

                            if (output.equals("")) {
                                trainSwitch.setChecked(false);
                                dialog.cancel();
                            } else {
                                carCost = Integer.parseInt(output);
                                // Setting funds left
                                total = total - carCost;
                                currentMoney.setText(String.valueOf(total));
                                setCurrentTotal(total);

                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            trainSwitch.setChecked(false);
                        }
                    });
                    // Render dialogue box
                    builder.show();
                }

            }
        });
    }

    public void configureCarSwitch() {
        final Switch carSwitch = (Switch) findViewById(R.id.carTravel_Switch);
        final TextView currentMoney = (TextView) findViewById(R.id.currentMoney_TextView);

        carSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    // Create dialogue builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                    builder.setTitle("How much does your petrol cost per month?");
                    // Setup of Input box
                    final EditText input = new EditText(InfoActivity.this);
                    builder.setView(input);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);

                    // Dialogue buttons
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String output = input.getText().toString();

                            if (output.equals("")) {
                                carSwitch.setChecked(false);
                                dialog.cancel();
                            } else {
                                carCost = Integer.parseInt(output);
                                // Setting funds left
                                total = total - carCost;
                                currentMoney.setText(String.valueOf(total));
                                setCurrentTotal(total);

                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            carSwitch.setChecked(false);
                        }
                    });
                    // Render dialogue box
                    builder.show();
                }
            }
        });
    }

    public void configureBusSwitch() {
        final Switch busSwitch = (Switch) findViewById(R.id.busTravel_Switch);
        final TextView currentMoney = (TextView) findViewById(R.id.currentMoney_TextView);

        busSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    // Create dialogue builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                    builder.setTitle("How much do you spend on the bus per month?");
                    // Setup of Input box
                    final EditText input = new EditText(InfoActivity.this);
                    builder.setView(input);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER);

                    // Dialogue buttons
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String output = input.getText().toString();

                            if (output.equals("")) {
                                busSwitch.setChecked(false);
                                dialog.cancel();
                            } else {
                                carCost = Integer.parseInt(output);
                                // Setting funds left
                                total = total - carCost;
                                currentMoney.setText(String.valueOf(total));
                                setCurrentTotal(total);
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            busSwitch.setChecked(false);
                        }
                    });
                    // Render dialogue box
                    builder.show();
                }
            }
        });
    }

}


