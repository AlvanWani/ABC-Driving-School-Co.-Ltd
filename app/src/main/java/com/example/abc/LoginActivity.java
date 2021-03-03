package com.example.abc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
     Button mBtnSignIn;
     TextView mTextViewSignUp,mTvForgotPassword;
     TextInputLayout Email, Password;

     FirebaseAuth firebaseAuth;
     ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBtnSignIn = findViewById(R.id.sign_in);
        mTextViewSignUp = findViewById(R.id.sign_up);
        mTvForgotPassword = findViewById(R.id.forgot_password);

        Email = findViewById(R.id.username);
        Password = findViewById(R.id.password);


        //setting onclick listeners
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String email, password;
//                email = Email.getEditText().toString().trim();
//                password = Password.getEditText().toString().trim();
//
//
//                if(email.isEmpty()){
//                    Email.setError("Please enter your email");
//                }else if(password.isEmpty()){
//                    Password.setError("Password Required");
//                }else if(password.length()>8){
//                    Password.setError("Please enter password with 8 or more Characters");
//                }else {
//                    dialog.show();
//                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            dialog.dismiss();
//                            if (task.isSuccessful()){
//                                Toast.makeText(LoginActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
//                            }else {
//                                Toast.makeText(LoginActivity.this,"Sign In failed",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }

                Intent intent = new Intent(LoginActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

        mTextViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });


//
//        if(firebaseAuth.getCurrentUser() != null){
//            finish();
//            startActivity(new Intent(getApplicationContext(),Dashboard.class));
//        }

        mTvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PasswordResetting.class));
            }
        });

    }
}