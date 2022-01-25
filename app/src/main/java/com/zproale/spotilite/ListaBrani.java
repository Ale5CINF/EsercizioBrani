package com.zproale.spotilite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ListaBrani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_brani);
        Intent intent = getIntent();
        String dati = intent.getStringExtra("data");
        TextView testo = (TextView) findViewById(R.id.textView);
        testo.setText(dati);
    }
}