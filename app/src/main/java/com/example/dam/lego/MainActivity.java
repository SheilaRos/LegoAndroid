package com.example.dam.lego;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Caja caja;
    Spinner spinner;
    EditText editText;
    //Button buscar = (Button) findViewById(R.id.btn_buscar);

     public void updateSpinner(){

     }
    public void downloadBox() {
        WebDownloader dd = new WebDownloader(this, editText.getText().toString());
        dd.execute();

    }

    public void updateSpinners() {
        SimpleCursorAdapter adapter;
       // adapter = new SimpleCursorAdapter(this, R.layout.lista_cajas, cursor, new String[]{"currency", "name"}, new int[]{R.id.textView1, R.id.textView2}, 0);

        //spinner.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner_piezas);
        editText = (EditText) findViewById(R.id.codigo);
        //spinner.setOnItemSelectedListener(this);
        editText.setText("");
//        buscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                downloadBox();
//            }
//        });
        downloadBox();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
