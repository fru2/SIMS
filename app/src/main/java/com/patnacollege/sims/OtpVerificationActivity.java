package com.patnacollege.sims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        otpFocus();

        Button submitBtn = findViewById(R.id.submit_btn);
        ImageButton backBtn = findViewById(R.id.back_btn);

        firebaseSendOtp();

        submitBtn.setOnClickListener(v -> {
//            Intent intent = new Intent(OtpVerificationActivity.this, HomeNavigationActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            finish();

            sumitBtnEvent();
        });

        backBtn.setOnClickListener(v -> {
            finish();
        });

    }


    public void otpFocus() {
        EditText otp1 = findViewById(R.id.otp1);
        EditText otp2 = findViewById(R.id.otp2);
        EditText otp3 = findViewById(R.id.otp3);
        EditText otp4 = findViewById(R.id.otp4);
        EditText otp5 = findViewById(R.id.otp5);
        EditText otp6 = findViewById(R.id.otp6);

        otp1.setOnKeyListener((v, keyCode, event) -> {
            if (otp1.getText().length() == 1) {
                otp2.requestFocus(); // Move focus to the next EditText
            }
            return false;
        });

        otp2.setOnKeyListener((v, keyCode, event) -> {
            if (otp2.getText().length() == 1) {
                otp3.requestFocus(); // Move focus to the next EditText
            }
            return false;
        });

        otp3.setOnKeyListener((v, keyCode, event) -> {
            if (otp3.getText().length() == 1) {
                otp4.requestFocus(); // Move focus to the next EditText
            }
            return false;
        });

        otp4.setOnKeyListener((v, keyCode, event) -> {
            if (otp4.getText().length() == 1) {
                otp5.requestFocus(); // Move focus to the next EditText
            }
            return false;
        });

        otp5.setOnKeyListener((v, keyCode, event) -> {
            if (otp5.getText().length() == 1) {
                otp6.requestFocus(); // Move focus to the next EditText
            }
            return false;
        });

        otp6.setOnKeyListener((v, keyCode, event) -> {
            if (otp6.getText().length() == 1) {

                // All OTP digits are filled, perform the necessary action (e.g., verify OTP)
                otp6.setImeOptions(EditorInfo.IME_ACTION_DONE); // Set "Submit" button on keyboard
                otp6.setOnEditorActionListener((textView, actionId, keyEvent) -> {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        return true;
                    }
                    return false;
                });

            }
            return false;
        });
    }

    public void firebaseSendOtp() {
        String contactNumber = getIntent().getStringExtra("CONTACT_NUMBER");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(contactNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                        Intent intent = new
//                                Intent(OtpVerificationActivity.this,
//                                HomeNavigationActivity.class);
//                        startActivity(intent);
//                        finish();

                        Toast.makeText(getApplicationContext(), "Otp send successfully", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(), "Unable to verify phone number, make sure you are connected to internet", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        OtpVerificationActivity.this.verificationId = verificationId;
                        Toast.makeText(getApplicationContext(), "OTP sent successfully.", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


//        PhoneAuthProvider.getInstance().verifyPhoneNumber(contactNumber, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void
//            onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                Intent intent = new
//                        Intent(OtpVerificationActivity.this,
//                        HomeNavigationActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onVerificationFailed(FirebaseException e) {
//                Toast.makeText(getApplicationContext(), "Unable to verify phone number, make sure you are connected to internet", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCodeSent(String verificationId,
//                                   PhoneAuthProvider.ForceResendingToken token) {
//                OtpVerificationActivity.this.verificationId = verificationId;
//                Toast.makeText(getApplicationContext(), "OTP sent successfully.", Toast.LENGTH_SHORT).show();
//            }
//        });


    }


    public void sumitBtnEvent() {
        EditText otp1EditText = findViewById(R.id.otp1);
        EditText otp2EditText = findViewById(R.id.otp2);
        EditText otp3EditText = findViewById(R.id.otp3);
        EditText otp4EditText = findViewById(R.id.otp4);
        EditText otp5EditText = findViewById(R.id.otp5);
        EditText otp6EditText = findViewById(R.id.otp6);


        String otp1 = otp1EditText.getText().toString().trim();
        String otp2 = otp2EditText.getText().toString().trim();
        String otp3 = otp3EditText.getText().toString().trim();
        String otp4 = otp4EditText.getText().toString().trim();
        String otp5 = otp5EditText.getText().toString().trim();
        String otp6 = otp6EditText.getText().toString().trim();

        String enteredOtp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, enteredOtp);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Intent intent = new Intent(OtpVerificationActivity.this, HomeNavigationActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "OTP verification failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginKardoOriginal() {
        String contactNumber = getIntent().getStringExtra("CONTACT_NUMBER");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(contactNumber, 60, TimeUnit.SECONDS, this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void
            onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Intent intent = new
                        Intent(OtpVerificationActivity.this,
                        HomeNavigationActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(), "Unable to verify phone number, make sure you are connected to internet", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                OtpVerificationActivity.this.verificationId = verificationId;
                Toast.makeText(getApplicationContext(), "OTP sent successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}