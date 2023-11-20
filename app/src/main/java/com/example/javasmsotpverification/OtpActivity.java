package com.example.javasmsotpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.javasmsotpverification.databinding.ActivityOtpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    private ActivityOtpBinding binding;
    String getotpbackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tvShowMobileNumber.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend = getIntent().getStringExtra("backendotp");

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etBox1.getText().toString().trim().isEmpty() && !binding.etBox2.getText().toString().trim().isEmpty()
                && !binding.etBox3.getText().toString().trim().isEmpty() && !binding.etBox4.getText().toString().trim().isEmpty()
                && !binding.etBox5.getText().toString().trim().isEmpty() && !binding.etBox6.getText().toString().trim().isEmpty()){

                    String enterCodeOtp = binding.etBox1.getText().toString() +
                            binding.etBox2.getText().toString() +
                            binding.etBox3.getText().toString() +
                            binding.etBox4.getText().toString() +
                            binding.etBox5.getText().toString() +
                            binding.etBox6.getText().toString();

                    if (getotpbackend != null){
                        binding.progressBar.setVisibility(View.VISIBLE);
                        binding.btnVerify.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend, enterCodeOtp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        binding.progressBar.setVisibility(View.GONE);
                                        binding.btnVerify.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(OtpActivity.this, "Enter the correct OTP please", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                });

                    } else {
                        Toast.makeText(OtpActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }


                    Toast.makeText(OtpActivity.this, "OTP verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OtpActivity.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        moveAfterFill();

        binding.tvResentOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        OtpActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(OtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getotpbackend = newbackendotp;
                                Toast.makeText(OtpActivity.this, "Otp Sended Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

//    This method will help to move directly to the next box without clicking on it
    private void moveAfterFill() {
        binding.etBox1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (!s.toString().trim().isEmpty()){
                   binding.etBox2.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etBox2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (!s.toString().trim().isEmpty()){
                   binding.etBox3.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etBox3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (!s.toString().trim().isEmpty()){
                   binding.etBox4.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etBox4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (!s.toString().trim().isEmpty()){
                   binding.etBox5.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.etBox5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (!s.toString().trim().isEmpty()){
                   binding.etBox6.requestFocus();
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}