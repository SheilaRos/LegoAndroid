package com.example.dam.lego;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Activity2 extends AppCompatActivity {
    TextView nombre;
    TextView color;
    TextView cantidad;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        nombre = (TextView) findViewById(R.id.nombrePieza);
        color = (TextView) findViewById(R.id.Color);
        cantidad = (TextView) findViewById(R.id.cantidad);
        imagen = (ImageView) findViewById(R.id.imagenPieza);
        Intent intent = this.getIntent();
        nombre.setText(intent.getStringExtra("nombre"));
        color.setText(intent.getStringExtra("color"));
        cantidad.setText(intent.getStringExtra("cantidad"));
        String urlImagen = intent.getStringExtra("url");
        Picasso.with(this).load(urlImagen).into(imagen, new Callback() {
            @Override
            public void onSuccess() {}
            @Override
            public void onError() {}
        });
    }
}
