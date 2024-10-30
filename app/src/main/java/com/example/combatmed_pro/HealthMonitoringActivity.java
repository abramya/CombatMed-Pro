package com.example.combatmed_pro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class HealthMonitoringActivity extends AppCompatActivity {
    private TextView healthInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_monitoring);

        healthInfoTextView = findViewById(R.id.health_info_text_view);

        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthMonitoringActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button checkHeartRateButton = findViewById(R.id.check_heart_rate_btn);
        checkHeartRateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to check heart rate
                int heartRate = checkHeartRate();
                healthInfoTextView.setText("Heart Rate: " + heartRate + " bpm");
            }
        });

        Button checkBloodPressureButton = findViewById(R.id.check_blood_pressure_btn);
        checkBloodPressureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to check blood pressure
                String bloodPressure = checkBloodPressure();
                healthInfoTextView.setText("Blood Pressure: " + bloodPressure);
            }
        });

        Button viewHealthHistoryButton = findViewById(R.id.view_health_history_btn);
        viewHealthHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display initial Toast message for 5 seconds
                Toast.makeText(HealthMonitoringActivity.this, "Viewing Health History...", Toast.LENGTH_SHORT).show();

                // Use a Handler to introduce a 5-second delay
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // After 5 seconds, display static health history data
                        String healthHistory = "Health History:\n" +
                                "BP: 120/80 mmHg, 10th Oct\n" +
                                "Heart Rate: 75 bpm, 12th Oct\n" +
                                "Temperature: 98.6°F, 15th Oct";
                        healthInfoTextView.setText(healthHistory);
                    }
                }, 5000); // 5-second delay
            }
        });
    }

    // Method to simulate heart rate check
    private int checkHeartRate() {
        Random random = new Random();
        return 60 + random.nextInt(41); // Simulated heart rate between 60 and 100 bpm
    }

    // Method to simulate blood pressure check
    private String checkBloodPressure() {
        Random random = new Random();
        int systolic = 90 + random.nextInt(40); // Simulated systolic between 90 and 130
        int diastolic = 60 + random.nextInt(30); // Simulated diastolic between 60 and 90
        return systolic + "/" + diastolic; // Return formatted blood pressure
    }
}
