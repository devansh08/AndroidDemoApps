package com.example.devansh.qrcodedemo;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRGenerator extends AppCompatActivity {

    public static final int QR_CODE_WIDTH = 500;
    public static final int QR_CODE_HEIGHT = 500;

    Button generateCode;

    EditText codeText;

    ImageView qrCode;

    Bitmap bitmap;

    String qrCodeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        qrCode = (ImageView) findViewById(R.id.imageView);
        generateCode = (Button) findViewById(R.id.button);
        codeText = (EditText) findViewById(R.id.editText);

        generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genQRCode();
            }
        });
    }

    public void genQRCode() {
        qrCodeText = codeText.getText().toString();

        try {
            bitmap = textToImageEncode(qrCodeText);

            qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.e("genQRCode", "WriterException\n" + e.toString());
        }
    }

    public Bitmap textToImageEncode(String code) throws WriterException {
        BitMatrix bitMatrix = null;
        Log.d("qrCodeText", code);

        try {
            bitMatrix = new MultiFormatWriter().encode(
                    code,
                    BarcodeFormat.QR_CODE,
                    QR_CODE_WIDTH, QR_CODE_HEIGHT, null
            );

            Log.d("T2I", "bitMatrix created");
        } catch (IllegalArgumentException e) {
            Log.e("textToImageEncode", "IllegalArgumentException\n" + e.toString());
        }

        int bitMatrixWidth = 0;
        if (bitMatrix != null) {
            bitMatrixWidth = bitMatrix.getWidth();
        } else {
            Log.d("bitMatrix", "bitMatrix is null");
        }
        int bitMatrixHeight = 0;
        if (bitMatrix != null) {
            bitMatrixHeight = bitMatrix.getHeight();
        } else {
            Log.d("bitMatrix", "bitMatrix is null");
        }
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        int count = 0;

        for(int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for(int x = 0; x < bitMatrixWidth; x++) {
                if (Build.VERSION.SDK_INT >= 23) {
                    pixels[offset + x] = bitMatrix.get(x, y) ?
                            getResources().getColor(R.color.QRCodeBlackColor, getTheme()) :
                            getResources().getColor(R.color.QRCodeWhiteColor, getTheme());
                    count++;
                } else {
                    pixels[offset + x] = bitMatrix.get(x, y) ?
                            getResources().getColor(R.color.QRCodeBlackColor) :
                            getResources().getColor(R.color.QRCodeWhiteColor);
                    Log.d("T2I", "Pixels initialized");
                }
            }
        }
        Log.d("count", String.valueOf(count));

        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

        return bitmap;
    }
}
