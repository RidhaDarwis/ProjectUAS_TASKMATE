package com.example.projectuas_taskmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SignInSignUp extends AppCompatActivity {

    Button buttonSignIn, buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinsignup);

        buttonSignIn = findViewById(R.id.buttonSignin);
        buttonSignUp = findViewById(R.id.buttonSignup);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignInSignUp.this, Signin.class);
                startActivity(intent1);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SignInSignUp.this, Signup.class);
                startActivity(intent2);
            }
        });
    }
}
