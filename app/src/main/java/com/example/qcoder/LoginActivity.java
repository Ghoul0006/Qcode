package com.example.qcoder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText cpfEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cpfEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.senhaEditText);
        loginButton = findViewById(R.id.okButton);

        // Desabilitar o botão "Login" inicialmente
        loginButton.setEnabled(false);

        // Adiciona listeners para os campos de CPF e senha
        cpfEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                checkFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                checkFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // Ação para o botão "Login"
        loginButton.setOnClickListener(view -> {
            String cpf = cpfEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            DatabaseHelper dbHelper = new DatabaseHelper(LoginActivity.this);

            if (dbHelper.checkCredentials(cpf, password)) {
                // Se as credenciais forem válidas, obter os dados do usuário
                String[] userData = dbHelper.getUserDataByCpf(cpf);
                String name = userData[0];
                String userCpf = userData[1];

                // Passar os dados para a próxima activity
                Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("cpf", userCpf);
                startActivity(intent);
            } else {
                // Caso contrário, mostrar uma mensagem de erro
                Toast.makeText(LoginActivity.this, "Usuário não identificado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Verifica se ambos os campos foram preenchidos
    private void checkFields() {
        loginButton.setEnabled(!cpfEditText.getText().toString().isEmpty() && !passwordEditText.getText().toString().isEmpty());
    }
}