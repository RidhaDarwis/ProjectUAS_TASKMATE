package com.example.projectuas_taskmate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Bantuan extends AppCompatActivity {

    ListView lvOptions;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bantuan);

        lvOptions = findViewById(R.id.lvOptions);
        btnBack = findViewById(R.id.buttonKembali);

        String[] options = new String[]{
                "Cara menambahkan tugas",
                "Cara edit tugas",
                "Cara hapus tugas",
                "Cara cek tugas terlewat",
                "Cara ubah tema",
                "Cara ubah notifikasi"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.warnatext_bantuan,
                android.R.id.text1,
                options);
        lvOptions.setAdapter(adapter);

        lvOptions.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            showInstructions(selectedItem);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void showInstructions(String option) {
        String title = "";
        String message = "";

        switch (option) {
            case "Cara menambahkan tugas":
                title = "Instruksi Menambahkan Tugas";
                message = "- \tMenekan logo \"+\" pada kanan bawah\n" +
                        "- \tMemasukkan nama mata kuliah\n" +
                        "- \tMemasukkan rincian atau instruksi \n\t\ttugas\n" +
                        "- \tMemasukkan tempat pengumpulan\n" +
                        "- \tMemilih tanggal deadline\n" +
                        "- \tMenekan tombol \"TAMBAHKAN\"";
                break;
            case "Cara edit tugas":
                title = "Instruksi Edit Tugas";
                message = "Instruksi untuk mengedit tugas...";
                break;
            case "Cara hapus tugas":
                title = "Instruksi Hapus Tugas";
                message = "Instruksi untuk menghapus tugas...";
                break;
            case "Cara cek tugas terlewat":
                title = "Instruksi Cek Tugas Terlewat";
                message = "Instruksi untuk memeriksa tugas yang terlewat...";
                break;
            case "Cara ubah tema":
                title = "Instruksi Ubah Tema";
                message = "Instruksi untuk mengubah tema aplikasi...";
                break;
            case "Cara ubah notifikasi":
                title = "Instruksi Ubah Notifikasi";
                message = "Instruksi untuk mengubah notifikasi...";
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
