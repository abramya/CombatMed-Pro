package com.example.combatmed_pro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ScheduleConsultationActivity extends AppCompatActivity {
    private EditText consultationDateEditText;
    private EditText consultationTimeEditText;
    private EditText consultationNotesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_consultation);

        consultationDateEditText = findViewById(R.id.consultation_date_edit_text);
        consultationTimeEditText = findViewById(R.id.consultation_time_edit_text);
        consultationNotesEditText = findViewById(R.id.consultation_notes_edit_text);
        Button scheduleButton = findViewById(R.id.schedule_btn);
        Button backButton = findViewById(R.id.back_btn);

        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleConsultation();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Go back to the previous activity
            }
        });
    }

    private void scheduleConsultation() {
        String date = consultationDateEditText.getText().toString();
        String time = consultationTimeEditText.getText().toString();
        String notes = consultationNotesEditText.getText().toString();

        // Add your logic here to save the consultation details
        // For now, just show a toast
        Toast.makeText(this, "Consultation Scheduled for " + date + " at " + time, Toast.LENGTH_SHORT).show();

        // Optionally, clear the fields after scheduling
        consultationDateEditText.setText("");
        consultationTimeEditText.setText("");
        consultationNotesEditText.setText("");
    }
}
