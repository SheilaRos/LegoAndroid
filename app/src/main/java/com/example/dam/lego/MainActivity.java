package com.example.dam.lego;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CajaList cajaList;
    Spinner spinner;
    EditText editText;
    Button button;
    public void downloadBox() {
        WebDownloader dd = new WebDownloader(this, editText.getText().toString());
        dd.setMainActivity(this);
        dd.execute();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner_piezas);
        editText = (EditText) findViewById(R.id.codigo);
        button = (Button) findViewById(R.id.btn_buscar);
        editText.setText("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadBox();
            }
        });
        spinner.setOnItemSelectedListener(this);
    }
    public void notifyCajaList(CajaList cajaList) {
        this.cajaList = cajaList;
        int i = 0;
        for(Caja c : cajaList.getResults()){
            c.setYear(i);
            i++;
        }
        BoxAdapter adapter = new BoxAdapter(this, cajaList.getResults());
        spinner.setAdapter(adapter);
    }

    public class BoxAdapter extends BaseAdapter {
        private Context context;
        private List<Caja> cajas;

        public BoxAdapter(Context context, List<Caja> cajas) {
            this.context = context;
            this.cajas = cajas;
        }
        public class ViewHolder {
            public TextView tvNom;
            public ImageView myImage;
            public TextView tvDescripcion;
        }
        @Override
        public int getCount() {
            return cajas.size();
        }

        @Override
        public Object getItem(int position) {
            return cajas.get(position);
        }

        @Override
        public long getItemId(int position) {
            Caja c = cajas.get(position);
                long id = c.getYear();
            return id;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.lista_cajas, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.tvNom = (TextView) myView.findViewById(R.id.nom);
                holder.myImage = (ImageView) myView.findViewById(R.id.imatge);
                holder.tvDescripcion = (TextView) myView.findViewById(R.id.descripcion);
                myView.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) myView.getTag();
            Caja caja = cajas.get(position);

            String nom = caja.getName();
            holder.tvNom.setText(nom);

            //String image = caja.getSetImgUrl();
            //holder.myImage.setImageIcon(image);

            String descripcion = caja.getSetNum();
            holder.tvDescripcion.setText(descripcion +"");
            return myView;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
