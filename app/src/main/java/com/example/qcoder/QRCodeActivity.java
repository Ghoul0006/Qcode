package com.example.qcoder;

import android.graphics.Color;
import android.os.Bundle;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeActivity extends AppCompatActivity {
    private ImageView ivQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        ivQrCode = findViewById(R.id.ivQrCode);
        String cpf = getIntent().getStringExtra("cpf");

        QRCodeWriter writer = new QRCodeWriter();
        try {
            Bitmap bitmap = generateQRCode(cpf);
            ivQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    private Bitmap generateQRCode(String text) throws WriterException {
        BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, 200, 200);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bitmap;
    }
}