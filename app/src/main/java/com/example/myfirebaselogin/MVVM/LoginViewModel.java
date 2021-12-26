package com.example.myfirebaselogin.MVVM;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";
FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
MutableLiveData<String> loginSuccessLiveData= new MutableLiveData<>();
MutableLiveData<String > loginFailureLiveData=new MutableLiveData<>();

// live data ,  MutableLiveData الفرق بينهم في تنظيم االكود وماحدش يعمل بوست فاليو من بره بلغلط


    public void login(String email,String password){

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        loginSuccessLiveData.postValue("Login Success");

//                       finish();
//                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loginFailureLiveData.postValue(e.getLocalizedMessage());
//                        String error=e.getLocalizedMessage();
//                        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
