package com.example.qcoder;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ScanActivity extends AppCompatActivity {
    private DecoratedBarcodeView barcodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        barcodeScanner = findViewById(R.id.barcodeScanner);
        barcodeScanner.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result.getText() != null) {
                    Intent intent = new Intent(ScanActivity.this, ScanResultActivity.class);
                    intent.putExtra("cpf", result.getText());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeScanner.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScanner.pause();
    }
}
