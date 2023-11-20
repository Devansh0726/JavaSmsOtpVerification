package com.example.javasmsotpverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.javasmsotpverification.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(this, ""+ currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
    }
}