package com.example.dam.lego;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView nombre;
    TextView color;
    TextView cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        nombre = (TextView) findViewById(R.id.nombrePieza);
        color = (TextView) findViewById(R.id.Color);
        cantidad = (TextView) findViewById(R.id.cantidad);
        Intent intent = this.getIntent();
        nombre.setText(intent.getStringExtra("nombre"));
        color.setText(intent.getStringExtra("color"));
        cantidad.setText(intent.getStringExtra("cantidad"));
    }
}
