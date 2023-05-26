package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText registrationNo = findViewById(R.id.registration_edit_txt);
        EditText dob = findViewById(R.id.dob_edit_txt);
        Button logIn = findViewById(R.id.log_in);

        registrationNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.hasFocus()){
                    registrationNo.setText("");
                }
                else
                    registrationNo.setText(R.string.registration_no);
            }
        });


        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v.hasFocus()){
                    dob.setText("");
                }
                else
                    dob.setText(R.string.dob);
            }
        });

        logIn.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, OtpVerificationActivity.class);
            startActivity(intent);
        });
    }
}