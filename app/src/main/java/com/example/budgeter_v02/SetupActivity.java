package com.example.budgeter_v02;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
// Imports for UI elements
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SeekBar;
import android.view.View;
import android.widget.TextView;
// Imports for database
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SetupActivity extends AppCompatActivity {

    private static final String TAG = "SetupActivity";

    // Access a Cloud FireStore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Variables for firebase
    int income;
    int saving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        configureContinueButton();
        configureIncomeSeekBar();
        configureSavingSeekBar();
    }

    private void configureIncomeSeekBar() {
        final SeekBar incomeSeekBar = findViewById(R.id.Income_SeekBar);
        final TextView incomeTextView = findViewById(R.id.Income_Text);

        incomeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // Variable to get current progress of SeekBar
            int y;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                y = seekBar.getProgress();
                incomeTextView.setText(String.valueOf(y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                income = y;
            }
        });
    }

    private void configureSavingSeekBar() {
        final SeekBar savingSeekBar = findViewById(R.id.Saving_SeekBar);
        final TextView savingTextView = findViewById(R.id.Saving_Text);
        final Button continueButton = findViewById(R.id.Continue_Button);


        savingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // Variable to get current progress of SeekBar
            int y;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                y = seekBar.getProgress();
                savingTextView.setText(String.valueOf(y));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                continueButton.setVisibility(View.VISIBLE);
                saving = y;
            }
        });
    }

    private void configureContinueButton() {
        final Button continueButton = findViewById(R.id.Continue_Button);

        continueButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("income", income);
                data.put("saving", saving);

                db.collection("data")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "Successful! ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document WARNING THIS IS ERROR MESSAGE THOMAS ", e);
                            }
                        });

                startActivity(new Intent(SetupActivity.this, InfoActivity.class));

            }
        });
    }
}
