package com.example.budgeter_v02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Initialising UI elements
        final SeekBar savingSeekBar = findViewById(R.id.Saving_SeekBar);
        final SeekBar incomeSeekBar = findViewById(R.id.Income_SeekBar);
        final TextView incomeTextView = findViewById(R.id.Income_Text);
        final Button continueButton = findViewById(R.id.Continue_Button);
        final TextView savingTextView = findViewById(R.id.Saving_Text);

        // SeekBar listeners
        incomeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int y = seekBar.getProgress();
                incomeTextView.setText(String.valueOf(y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        savingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int y = seekBar.getProgress();
                savingTextView.setText(String.valueOf(y));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                continueButton.setVisibility(View.VISIBLE);
            }
        });

    }



}
