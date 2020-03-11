package com.example.budgeter_v02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class InfoActivity extends AppCompatActivity {
    // Variables for database
    private static boolean train;
    private static boolean car;
    private static boolean bus;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        configureTrainSwitch();
        configureCarSwitch();
    }

    public void configureTrainSwitch() {
        final Switch trainSwitch = (Switch) findViewById(R.id.trainTravel_Switch);

        trainSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    train = true;
                }
            }
        });
    }

    public void configureCarSwitch() {
        final Switch carSwitch = (Switch) findViewById(R.id.carTravel_Switch);

        carSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    car = true;
                }
            }
        });
    }

    public void configureBusSwitch() {
        final Switch busSwitch = (Switch) findViewById(R.id.busTravel_Switch);

        busSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) {
                    bus = true;

                    AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                }
            }
        });
    }
}
