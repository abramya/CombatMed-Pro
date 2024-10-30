package com.example.combatmed_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText usernameInput, passwordInput;
    private Button loginButton;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Get references to the UI elements
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_btn);

        // Set the login button click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });
    }

    private void validateLogin() {
        // Get the values entered by the user
        String enteredUsername = usernameInput.getText().toString();
        String enteredPassword = passwordInput.getText().toString();

        // Query Firestore to validate the login
        db.collection("users").document(enteredUsername).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(@NonNull DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // User exists, check the password
                            String storedPassword = documentSnapshot.getString("password");

                            if (storedPassword != null && storedPassword.equals(enteredPassword)) {
                                // Password matches, login successful
                                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                                // Navigate to the next activity (e.g., HomeActivity)
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                Intent intent1 = new Intent(MainActivity.this, TelemedicineActivity.class);

                                intent.putExtra("username", enteredUsername);
                                startActivity(intent1);// Ensure 'username' is not null here
                                startActivity(intent);



                                intent.putExtra("username", enteredUsername);
                                startActivity(intent);

                            } else {
                                // Password does not match
                                Toast.makeText(MainActivity.this, "Invalid password!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // User not found
                            Toast.makeText(MainActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors
                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
