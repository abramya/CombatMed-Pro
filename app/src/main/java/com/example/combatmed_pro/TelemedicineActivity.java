package com.example.combatmed_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelemedicineActivity extends AppCompatActivity {
    private TextView telemedicineInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telemedicine);

        telemedicineInfoTextView = findViewById(R.id.telemedicine_info_text_view);

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelemedicineActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button scheduleConsultationButton = findViewById(R.id.schedule_consultation_btn);
        scheduleConsultationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigating to ScheduleConsultationActivity
                Intent intent = new Intent(TelemedicineActivity.this, ScheduleConsultationActivity.class);
                startActivity(intent);
            }
        });

        Button viewPastConsultationsButton = findViewById(R.id.view_past_consultations_btn);
        viewPastConsultationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display static data in the TextView
                String pastConsultations = "Past Consultation 1: 10 AM, 5th Oct\n" +
                        "Past Consultation 2: 2 PM, 12th Oct\n" +
                        "Past Consultation 3: 11 AM, 18th Oct";
                telemedicineInfoTextView.setText(pastConsultations);
                Toast.makeText(TelemedicineActivity.this, "Viewing Past Consultations...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
