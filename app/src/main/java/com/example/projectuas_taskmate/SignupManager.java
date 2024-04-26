package com.example.projectuas_taskmate;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SignupManager {
//
//    private FirebaseAuth mAuth;
//    private DatabaseReference mDatabase;
//
//    public SignupManager() {
//        mAuth = FirebaseAuth.getInstance();
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//    }
//
//    public void signUp(final Context context, String email, final String username, String password, String confirmPassword) {
//        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
//            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!password.equals(confirmPassword)) {
//            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        final ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Signing up...");
//        progressDialog.show();
//
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
//                        if (task.isSuccessful()) {
//                            // Sign up success, save username to database
//                            String userId = mAuth.getCurrentUser().getUid();
//                            mDatabase.child("signup").child("username").child(userId).setValue(username);
//                            Toast.makeText(context, "Sign up successful", Toast.LENGTH_SHORT).show();
//
//                            Intent intent = new Intent(context, Signin.class);
//                            context.startActivity(intent);
//                        } else {
//                            // Sign up failed
//                            Toast.makeText(context, "Sign up failed. " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//}


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectuas_taskmate.Signin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupManager {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public SignupManager() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void signUp(final Context context, final String email, final String username, String password, String confirmPassword) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(context, "Passwords tidak sesuai", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email is already registered
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (task.isSuccessful()) {
                            boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
                            if (!isNewUser) {
                                // Email is already registered
                                Toast.makeText(context, "Email sudah terdaftar", Toast.LENGTH_SHORT).show();
                            } else {
                                // Check if username is already taken
                                mDatabase.child("signup").child("username").orderByChild("username").equalTo(username)
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    // Username is already taken
                                                    Toast.makeText(context, "Username sudah digunakan", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // Email and username are unique, proceed with sign up
                                                    performSignUp(context, email, username, password);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle database error
                                                Toast.makeText(context, "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        } else {
                            // Handle task error
                            Toast.makeText(context, "Task error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void performSignUp(final Context context, String email, final String username, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign up success, save username to database
                            String userId = mAuth.getCurrentUser().getUid();
                            mDatabase.child("signup").child("username").child(userId).setValue(username);
                            Toast.makeText(context, "Sign up berhasi!", Toast.LENGTH_SHORT).show();

                            // Redirect to login activity
                            Intent intent = new Intent(context, Signin.class);
                            context.startActivity(intent);
                        } else {
                            // Sign up failed
                            String errorMessage;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                errorMessage = "password lemah";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                errorMessage = "email tidak valid";
                            } catch (FirebaseAuthUserCollisionException e) {
                                errorMessage = "pengguna sudah terdaftar";
                            } catch (Exception e) {
                                errorMessage = "Sign up gagal: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
