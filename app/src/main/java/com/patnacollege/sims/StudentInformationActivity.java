package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class StudentInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        ImageButton backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(v -> {
            finish();
        });
    }
}