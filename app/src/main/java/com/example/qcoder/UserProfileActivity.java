package com.example.qcoder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class UserProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView cpfTextView;
    private ImageView qrCodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        nameTextView = findViewById(R.id.nameTextView);
        cpfTextView = findViewById(R.id.cpfTextView);
        qrCodeImageView = findViewById(R.id.qrCodeImageView);

        // Receber os dados da activity anterior
        String name = getIntent().getStringExtra("name");
        String cpf = getIntent().getStringExtra("cpf");

        nameTextView.setText(name);
        cpfTextView.setText(cpf);

        try {
            // Gerar o QR code para o CPF
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(cpf, com.google.zxing.BarcodeFormat.QR_CODE, 400, 400);
            qrCodeImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}