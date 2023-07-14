package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

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

        Button logOut = findViewById(R.id.log_out);


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

        logOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        });

    }
}