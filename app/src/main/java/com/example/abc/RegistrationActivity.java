package com.example.abc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class    RegistrationActivity extends AppCompatActivity {
    Button mBtnReg;
    TextView mTextViewSignIn;
    TextInputLayout fullName,Email,Password,Phone;

    ProgressDialog dialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBtnReg = findViewById(R.id.register);
        mTextViewSignIn = findViewById(R.id.sign_in);
        fullName = findViewById(R.id.full_name);
        Email = findViewById(R.id.email_add);
        Password = findViewById(R.id.reg_password);
        Phone = findViewById(R.id.phone_no);

        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Registering");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
            finish();
        }

//        //setting onclick listeners
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String full_name, email, password, confirmPassword;
                full_name = fullName.getEditText().toString().trim();
                email = Email.getEditText().toString().trim();
                password = Password.getEditText().toString().trim();

//                if (TextUtils.isEmpty(email)) {
//                    Email.setError("Please enter your email");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(password)) {
//                    Password.setError("Please enter your password");
//                    return;
//                }
//
//                if (password.length()<6) {
//                    Password.setError("Password must have 6 or more characters");
//                    return;
//                }

//                dialog.show();

                //registering user
//                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        dialog.dismiss();
//
//                            if (task.isSuccessful()){
//                                Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
//                            } else {
//                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
//                            }
//
//                    }
//                });


                if(TextUtils.isEmpty(full_name)){
                    fullName.setError("Please Your Full Name");
                }else if(TextUtils.isEmpty(email)){
                    Email.setError("Please Enter Email");
                }else if(TextUtils.isEmpty(password)){
                    Password.setError("Please Enter Password");
                }else if(password.length()<6){
                    Password.setError("Please enter password with 6 or above characters");
//                }else if(confirmPassword.isEmpty()){
//                    ConfirmPassword.setError("Please confirm your password");
//                }else if (!confirmPassword.equals(password)) {
//                    ConfirmPassword.setError("Password do not match");
                } else {
                    dialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();

                            if (task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Dashboard.class));
                            } else {
                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

        mTextViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}