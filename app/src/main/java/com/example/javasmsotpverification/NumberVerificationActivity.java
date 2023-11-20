package com.example.javasmsotpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.javasmsotpverification.databinding.ActivityNumberVerificationBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NumberVerificationActivity extends AppCompatActivity {

    private ActivityNumberVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumberVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etMobileNumber.getText().toString().trim().isEmpty()) {
                    if (binding.etMobileNumber.getText().toString().trim().length() == 10) {

                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.btnGetOtp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + binding.etMobileNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                NumberVerificationActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        binding.progressBar.setVisibility(View.GONE);
                                        binding.btnGetOtp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        binding.progressBar.setVisibility(View.GONE);
                                        binding.btnGetOtp.setVisibility(View.VISIBLE);
                                        Toast.makeText(NumberVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        Intent intent = new Intent(NumberVerificationActivity.this, OtpActivity.class);
                                        intent.putExtra("mobile", binding.etMobileNumber.getText().toString());
                                        intent.putExtra("backendotp", backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );

                    } else {
                        Toast.makeText(NumberVerificationActivity.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NumberVerificationActivity.this, "Enter your number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}