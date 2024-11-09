package com.example.qcoder;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScanResultActivity extends AppCompatActivity {
    private TextView tvName, tvCpf;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        tvName = findViewById(R.id.tvName);
        tvCpf = findViewById(R.id.tvCpf);
        dbHelper = new DatabaseHelper(this);

        String cpf = getIntent().getStringExtra("cpf");
        Cursor cursor = dbHelper.getUserByCpf(cpf);

        if (cursor != null && cursor.moveToFirst()) {
            tvName.setText("Nome: " + cursor.getString(cursor.getColumnIndex("name")));
            tvCpf.setText("CPF: " + cursor.getString(cursor.getColumnIndex("cpf")));
        } else {
            Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}