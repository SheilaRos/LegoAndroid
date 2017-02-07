package com.example.dam.lego;

import android.content.Intent;
import android.graphics.*;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Activity2 extends AppCompatActivity {
    TextView nombre;
    TextView color;
    TextView cantidad;
    ImageView imagen;
    TextView urlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        nombre = (TextView) findViewById(R.id.nombrePieza);
        color = (TextView) findViewById(R.id.Color);
        cantidad = (TextView) findViewById(R.id.cantidad);
        imagen = (ImageView) findViewById(R.id.imagenPieza);
        urlText = (TextView) findViewById(R.id.urlText);
        Intent intent = this.getIntent();
        nombre.setText(intent.getStringExtra("nombre"));
        color.setText(intent.getStringExtra("color"));
        cantidad.setText(intent.getStringExtra("cantidad"));
        final String urlPart = intent.getStringExtra("urlWeb");
        urlText.setText(urlPart);
        urlText.setTextColor(Color.BLUE);
        urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(urlPart);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        String urlImagen = intent.getStringExtra("url");
        Picasso.with(this).load(urlImagen).into(imagen, new Callback() {
            @Override
            public void onSuccess() {}
            @Override
            public void onError() {}
        });
    }
}
