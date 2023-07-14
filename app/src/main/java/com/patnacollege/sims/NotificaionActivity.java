package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

public class NotificaionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaion);

        ImageButton backBtn = findViewById(R.id.back_btn);

        CardView b1 = findViewById(R.id.cardView);
        CardView b2 = findViewById(R.id.cardView2);
        CardView b3 = findViewById(R.id.cardView3);


        backBtn.setOnClickListener(v -> {
            finish();
        });

        b1.setOnClickListener(v -> {
            String myLink = "https://pup.ac.in/exams.aspx";
            openWebPage(myLink);
        });
        b2.setOnClickListener(v -> {
            String myLink = "https://pup.ac.in/SemesterFee.aspx";
            openWebPage(myLink);
        });
        b3.setOnClickListener(v -> {
            String myLink = "https://pup.ac.in";
            openWebPage(myLink);
        });

    }

    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}