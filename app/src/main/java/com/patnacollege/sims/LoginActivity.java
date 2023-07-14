package com.patnacollege.sims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase.getInstance().setPersistenceEnabled(false);

        EditText registrationNo = findViewById(R.id.registration_edit_txt);
        EditText dob = findViewById(R.id.dob_edit_txt);
        Button logIn = findViewById(R.id.log_in);

        CheckBox tcButton = findViewById(R.id.checkbox);

        Boolean active = false;

        tcButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                logIn.setOnClickListener(v -> {
                    checkCredential();
                });
            }
            else{
                logIn.setOnClickListener(v -> {
                    makeToast("Please check the T&C button");
                });
            }
        });

        }




    public void makeToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void checkCredential() {
        EditText registrationEditText = findViewById(R.id.registration_edit_txt);
        EditText dobEditText = findViewById(R.id.dob_edit_txt);

        String registrationNumber = String.valueOf(registrationEditText.getText());

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", registrationNumber);
        editor.apply();

//        String dob = dobEditText.getText().toString().trim();
        String dob = String.valueOf(dobEditText.getText());

//        FirebaseDatabase.getInstance().setPersistenceEnabled(false);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Registration Number");

        databaseReference.child(registrationNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("DOB").getValue(String.class), dob)) {
                    String contactNumber = dataSnapshot.child("Contact").getValue(String.class);
                    Intent intent = new Intent(LoginActivity.this, OtpVerificationActivity.class);
                    intent.putExtra("CONTACT_NUMBER", contactNumber);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration no and DOB does not match", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Make sure you are connected to internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}