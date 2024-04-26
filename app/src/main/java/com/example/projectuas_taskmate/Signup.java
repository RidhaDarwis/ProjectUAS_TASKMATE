//package com.example.projectuas_taskmate;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class Signup extends AppCompatActivity {
//
//    Button buttonSignIn;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        buttonSignIn = findViewById(R.id.buttonSignin);
//
//        buttonSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(Signup.this, Signin.class);
//                startActivity(intent1);
//            }
//        });
//    }
//}




package com.example.projectuas_taskmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    private EditText editTextEmail, editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button buttonSignUp, buttonSignIn;
    private SignupManager signupManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupManager = new SignupManager();

        editTextEmail = findViewById(R.id.email);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.konfirmasipassword);
        buttonSignUp = findViewById(R.id.buttonSignup);
        buttonSignIn = findViewById(R.id.buttonSignin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                signupManager.signUp(Signup.this, email, username, password, confirmPassword);
            }
        });

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Signup.this, Signin.class);
                startActivity(intent1);
            }
        });
    }
}
