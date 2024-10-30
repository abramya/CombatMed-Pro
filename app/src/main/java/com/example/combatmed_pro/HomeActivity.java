package com.example.combatmed_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // This should refer to your activity_home.xml layout

        // Initialize buttons
        Button btnHealthMonitoring = findViewById(R.id.btn_health_monitoring);
        Button btnAssessments = findViewById(R.id.btn_assessments);
        Button btnTelemedicine = findViewById(R.id.btn_telemedicine);

        // Set click listeners to navigate to the respective activities

        btnHealthMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HealthMonitoringActivity
                Intent intent = new Intent(HomeActivity.this, HealthMonitoringActivity.class);
                startActivity(intent);
            }
        });

        btnAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AssessmentsActivity
                String username = getIntent().getStringExtra("username");
                if (username != null) {
                    Intent intent = new Intent(HomeActivity.this, AssessmentsActivity.class);
                    intent.putExtra("username", username);  // Pass username to next activity
                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Username is missing!", Toast.LENGTH_SHORT).show();


                }
            }
        });

        btnTelemedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to TelemedicineActivity
                Intent intent = new Intent(HomeActivity.this, TelemedicineActivity.class);
                startActivity(intent);
            }
        });
    }
}
