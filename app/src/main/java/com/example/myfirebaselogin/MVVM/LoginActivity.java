package com.example.myfirebaselogin.MVVM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.myfirebaselogin.MainActivity;
import com.example.myfirebaselogin.R;
import com.example.myfirebaselogin.RegisterActivity;
import com.example.myfirebaselogin.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
ActivityLoginBinding binding;
//FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
 LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
         viewModel=new ViewModelProvider(this).get(LoginViewModel.class);
         loginObservation();

    }

    public void login(View view) {
        String email= Objects.requireNonNull(binding.textFieldEmail.getEditText()).getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(this, "email Required", Toast.LENGTH_SHORT).show();
            return;
        }
        String password=binding.textFieldPassword.getEditText().getText().toString().trim();
        if(password.isEmpty()) {
            Toast.makeText(this, "password Required", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.login(email,password);

    }
    public void loginObservation(){
        viewModel.loginSuccessLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String successMessage) {
                Log.i(TAG, "onChanged: "+successMessage);
                finish();
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        viewModel.loginFailureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                Log.i(TAG, "onChanged: "+errorMessage);
               Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void register(View view) {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);


    }
}