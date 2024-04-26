package com.example.projectuas_taskmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninManager {

    private FirebaseAuth mAuth;

    public SigninManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void signIn(final Context context, String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, redirect to main activity
                            Intent intent = new Intent(context, Beranda.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else {
                            // Sign in failed
                            Toast.makeText(context, "Email/Password Salah!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
