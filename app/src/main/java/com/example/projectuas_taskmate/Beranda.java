package com.example.projectuas_taskmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Beranda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

            FloatingActionButton tambahTugasButton = findViewById(R.id.tambah_tugas);
            tambahTugasButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Beranda.this, TambahTugas.class);
                    startActivity(intent);
                }
            });
        }

    }
