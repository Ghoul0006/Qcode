package com.example.qcoder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.BarcodeEncoder;

public class UserProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView cpfTextView;
    private ImageView qrCodeImageView;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Vinculando os elementos da interface
        nameTextView = findViewById(R.id.nameTextView);
        cpfTextView = findViewById(R.id.cpfTextView);
        qrCodeImageView = findViewById(R.id.qrCodeImageView);
        btnBack = findViewById(R.id.btnBack);

        // Recebendo os dados da Intent
        String name = getIntent().getStringExtra("name");
        String cpf = getIntent().getStringExtra("cpf");

        // Exibindo os dados na interface
        nameTextView.setText(name);
        cpfTextView.setText(cpf);

        // Gerando o QR Code para o CPF
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(cpf, com.google.zxing.BarcodeFormat.QR_CODE, 400, 400);
            qrCodeImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configurando a ação do botão "Voltar"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}