package com.nobelcorp.barcoderead;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity<potected> extends AppCompatActivity {
    private Button btnScan;
    private TextView tvBarCode;
    private int requestCode;
    private int resultCode;
    @Nullable
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        tvBarCode = findViewById(R.id.tvBarCode);

        btnScan.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){
                tvBarCode.setText("El código de barras es:\n" + result.getContents());
            }else{
                tvBarCode.setText("Error al escanear el código de barras");
            }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnScan:
                    new IntentIntegrator(MainActivity.this).initiateScan();
                    break;
            }
        }
    };


}