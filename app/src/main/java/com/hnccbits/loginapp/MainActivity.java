package com.hnccbits.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.hnccbits.loginapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.login.setOnClickListener((v) -> {
            String username = binding.etUserName.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();
            if (!isUsernameCorrect(username)) {
                binding.etUserName.setError(getString(R.string.user_name_condition));
                binding.etUserName.requestFocus();
                return;
            }
            if (!isPasswordCorrect(password)) {
                binding.etPassword.setError(getString(R.string.enter_valid_password));
                binding.etPassword.requestFocus();
                return;
            }
            binding.progressBar.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1500);
        });
    }

    private boolean isUsernameCorrect(String username) {
        return username.length() >= 10;
    }

    private boolean isPasswordCorrect(String password) {
        if (password.length() < 7) return false;
        String regex = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9]).{7,}$";
        return password.matches(regex);
    }
}