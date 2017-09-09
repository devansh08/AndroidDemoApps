package com.example.devansh.qrcodedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button qrGenerator, qrScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrGenerator = (Button) findViewById(R.id.button2);
        qrScanner = (Button) findViewById(R.id.button3);

        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGeneratorActivity();
            }
        });
        qrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScannerActivity();
            }
        });
    }

    public void startGeneratorActivity() {
        Intent intent = new Intent(this, QRGenerator.class);

        startActivity(intent);
    }

    public void startScannerActivity() {
        Intent intent = new Intent(this, QRScanner.class);

        startActivity(intent);
    }
}
