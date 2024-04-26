//package com.example.projectuas_taskmate;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//
//@SuppressLint("CustomSplashScreen")
//public class SplashScreen extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash_screen);
//
//        final Handler handler = new Handler();
//        long delayMillis = 3000L; // Waktu tunda dalam milidetik (3 detik)
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), SignInSignUp.class));
//                finish();
//            }
//        }, delayMillis);
//    }
//}



package com.example.projectuas_taskmate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        final Handler handler = new Handler();
        long delayMillis = 3000L; // Waktu tunda dalam milidetik (3 detik)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Periksa apakah pengguna sudah login
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    // Pengguna sudah login, arahkan ke MainActivity
                    startActivity(new Intent(SplashScreen.this, Beranda.class));
                } else {
                    // Pengguna belum login, arahkan ke SignInSignUp
                    startActivity(new Intent(SplashScreen.this, SignInSignUp.class));
                }
                finish();
            }
        }, delayMillis);
    }
}
