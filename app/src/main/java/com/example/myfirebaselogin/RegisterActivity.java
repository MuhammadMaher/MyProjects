package com.example.myfirebaselogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myfirebaselogin.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
ActivityRegisterBinding binding;
FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);


    }

    public void submit(View view) {
        String firstName=binding.rFirstName.getEditText().toString();
        if (firstName.isEmpty()){
            Toast.makeText(this, "First Name required", Toast.LENGTH_SHORT).show();
            return;
        }
        String lastName=binding.rLastName.getEditText().getText().toString().trim();
        if(lastName.isEmpty()){
            Toast.makeText(this, "Last Name Required", Toast.LENGTH_SHORT).show();
            return;
        }
         email=binding.rEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()){
            Toast.makeText(this, "Email Required", Toast.LENGTH_SHORT).show();
        return;
        }
        String password=binding.rPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()){
            Toast.makeText(this, "Password Required", Toast.LENGTH_SHORT).show();
            return;
        }
//        String confirmPassword=binding.rConfirmpassword.getEditText().toString().trim();
//        if (!password.equals(confirmPassword)){
//            Toast.makeText(this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
//            return;
//        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        String error=e.getLocalizedMessage();
                        Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "onFailure: " + error);

                    }
                });
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);



    }
}