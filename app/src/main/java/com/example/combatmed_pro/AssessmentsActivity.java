package com.example.combatmed_pro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssessmentsActivity extends AppCompatActivity {
    private TextView assessmentInfoTextView;
    private FirebaseFirestore db;
    private String username;  // This should be passed from the LoginActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);

        // Retrieve username from intent

        username = getIntent().getStringExtra("username");
        if (username == null) {
            Toast.makeText(this, "Username not provided", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity if no username is provided
            return;
        }


        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        assessmentInfoTextView = findViewById(R.id.assessment_info_text_view);

        // Load assessments for the logged-in user


        Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssessmentsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button performAssessmentButton = findViewById(R.id.perform_assessment_btn);
        performAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to perform an assessment
                Toast.makeText(AssessmentsActivity.this, "Performing assessment...", Toast.LENGTH_SHORT).show();
            }
        });

        Button viewAssessmentHistoryButton = findViewById(R.id.view_assessment_history_btn);
        viewAssessmentHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to view assessment history
                Toast.makeText(AssessmentsActivity.this, "Viewing Assessment History...", Toast.LENGTH_SHORT).show();
                loadUserAssessments(username);
            }
        });
    }

    private void loadUserAssessments(String username) {
        db.collection("users").document(username).collection("assessments")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        StringBuilder assessments = new StringBuilder();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String assessmentType = document.getString("type");
                            String date;
                            Timestamp timestamp = document.getTimestamp("date");
                            if (timestamp != null) {
                                Date dateObj = timestamp.toDate();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                date = dateFormat.format(dateObj);
                            } else {
                                date = "No date available";
                            }
                            String observations = document.getString("notes");

                            assessments.append("Type: ").append(assessmentType).append("\n");
                            assessments.append("Date: ").append(date).append("\n");
                            assessments.append("Notes: ").append(observations).append("\n\n");
                        }
                        assessmentInfoTextView.setText(assessments.toString());
                    } else {
                        Log.d("FirebaseError", "Error getting documents: ", task.getException());
                    }
                });
    }
}
