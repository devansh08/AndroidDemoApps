package com.example.devansh.qrcodedemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScanner extends AppCompatActivity {

    Button qrScanner;

    TextView scanResult;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        qrScanner = (Button) findViewById(R.id.button4);
        scanResult = (TextView) findViewById(R.id.textView);

        activity = this;

        qrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScanner();
            }
        });
    }

    public void startScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                .setPrompt("Scan")
                .setCameraId(0)
                .setBeepEnabled(false)
                .setBarcodeImageEnabled(false)
                .setCaptureActivity(CaptureActivityPortrait.class)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Scanning cancelled", Toast.LENGTH_LONG).show();
            } else {
                scanResult.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
