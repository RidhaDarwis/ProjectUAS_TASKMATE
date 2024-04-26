//package com.example.projectuas_taskmate;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class Signin extends AppCompatActivity {
//
//    Button buttonSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signin);
//
//        buttonSignUp = findViewById(R.id.buttonSignup);
//
//        buttonSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent2 = new Intent(Signin.this, Signup.class);
//                startActivity(intent2);
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
import androidx.appcompat.app.AppCompatActivity;

public class Signin extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button buttonSignIn, buttonSignUp;

    private SigninManager signinManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonSignIn = findViewById(R.id.buttonSignin);
        buttonSignUp = findViewById(R.id.buttonSignup);

        signinManager = new SigninManager();

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                signinManager.signIn(Signin.this, email, password);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Signin.this, Signup.class);
                startActivity(intent2);
            }
        });
    }
}
