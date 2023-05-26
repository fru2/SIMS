package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ImageButton backBtn = findViewById(R.id.back_btn);

        CardView studentInfo = findViewById(R.id.student_information);
        CardView notification = findViewById(R.id.notification);
        CardView aboutApp = findViewById(R.id.about_app);
        CardView help = findViewById(R.id.help);
        CardView settings = findViewById(R.id.settings);


        backBtn.setOnClickListener(v -> {
            finish();
        });

        studentInfo.setOnClickListener(v -> {
            Intent intent = new Intent(OptionsActivity.this, StudentInformationActivity.class);
            startActivity(intent);
        });

        notification.setOnClickListener(v -> {
            Intent intent = new Intent(OptionsActivity.this, NotificaionActivity.class);
            startActivity(intent);
        });

        aboutApp.setOnClickListener(v -> {
            Intent intent = new Intent(OptionsActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(OptionsActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

    }
}