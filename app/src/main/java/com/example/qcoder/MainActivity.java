package com.example.qcoder;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnLogin).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });

        findViewById(R.id.btnScan).setOnClickListener(v -> {
            // Solicitar senha do admin antes de abrir scanner
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Senha Admin");
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                String password = input.getText().toString();
                if (password.equals("Admin123")) {
                    startActivity(new Intent(MainActivity.this, ScanActivity.class));
                } else {
                    Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
            builder.show();
        });
    }
}
