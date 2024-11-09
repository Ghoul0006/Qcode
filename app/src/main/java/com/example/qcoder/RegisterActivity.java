package com.example.qcoder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName, etCpf, etPassword;
    private Button btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etCpf = findViewById(R.id.etCpf);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        dbHelper = new DatabaseHelper(this);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnRegister.setEnabled(
                        !etName.getText().toString().isEmpty() &&
                                !etCpf.getText().toString().isEmpty() &&
                                !etPassword.getText().toString().isEmpty()
                );
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etName.addTextChangedListener(watcher);
        etCpf.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String cpf = etCpf.getText().toString();
            String password = etPassword.getText().toString();

            if (dbHelper.addUser(name, cpf, password)) {
                // Gerar QR Code
                Intent intent = new Intent(RegisterActivity.this, QRCodeActivity.class);
                intent.putExtra("cpf", cpf);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}