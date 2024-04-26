package com.example.projectuas_taskmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TambahTugas extends AppCompatActivity {
    Button buttonTambahTugas;
    Button buttonBatal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        buttonTambahTugas = findViewById(R.id.buttonTambahTugas);
        buttonBatal = findViewById(R.id.buttonbatal);

        buttonTambahTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahTugas.this, Beranda.class);
                startActivity(intent);
            }

        });

        buttonBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahTugas.this, Beranda.class);
                startActivity(intent);
            }
        });
    }
}
