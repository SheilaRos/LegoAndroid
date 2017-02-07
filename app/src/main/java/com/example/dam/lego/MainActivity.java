package com.example.dam.lego;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CajaList cajaList;
    PartsList partsList;
    Spinner spinner;
    EditText editText;
    ListView lista_parts;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner_cajas);
        editText = (EditText) findViewById(R.id.codigo);
        button = (Button) findViewById(R.id.btn_buscar);
        lista_parts = (ListView) findViewById(R.id.lista_partes);
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

            Picasso.with(this.context).load(caja.getSetImgUrl()).into(holder.myImage, new Callback() {
                @Override
                public void onSuccess() {}
                @Override
                public void onError() {}
            });

            String descripcion = caja.getSetNum();
            holder.tvDescripcion.setText(descripcion +"");
            return myView;
        }
    }
    public void downloadBox() {
        WebDownloader dd = new WebDownloader(this, editText.getText().toString());
        dd.setMainActivity(this);
        dd.execute();
    }
    public void downloadParts(String codigo) {
        DownloaderParts dd = new DownloaderParts(this, codigo);
        dd.setMainActivity(this);
        dd.execute();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String codigoCaja = cajaList.getResults().get(position).getSetNum();
        downloadParts(codigoCaja);
    }
    public void notifyPartsList(final PartsList partsList) {
        this.partsList = partsList;
        PartsAdapter adapter = new PartsAdapter(this, partsList.getResults());
        lista_parts.setAdapter(adapter);

        lista_parts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("cantidad", partsList.getResults().get(position).getQuantity().toString());
                intent.putExtra("nombre", partsList.getResults().get(position).getPart().getName());
                intent.putExtra("color", partsList.getResults().get(position).getColor().getName());
                intent.putExtra("url", partsList.getResults().get(position).getPart().getPartImgUrl());
                intent.putExtra("urlWeb", partsList.getResults().get(position).getPart().getPartUrl());
                startActivity(intent);
            }
        });

    }

    public class PartsAdapter extends BaseAdapter {
        private Context context;
        private List<Parts> parts;

        public PartsAdapter(Context context, List<Parts> parts) {
            this.context = context;
            this.parts = parts;
        }
        public class ViewHolder {
            public TextView tvNom;
            public ImageView myImage;
        }
        @Override
        public int getCount() {
            return parts.size();
        }

        @Override
        public Object getItem(int position) {
            return parts.get(position);
        }

        @Override
        public long getItemId(int position) {
            Parts p = parts.get(position);
            long id = p.getId();
            return id;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.lista_piezas, parent, false);
                MainActivity.PartsAdapter.ViewHolder holder = new MainActivity.PartsAdapter.ViewHolder();
                holder.tvNom = (TextView) myView.findViewById(R.id.nom);
                holder.myImage = (ImageView) myView.findViewById(R.id.imatge);
                myView.setTag(holder);
            }
            MainActivity.PartsAdapter.ViewHolder holder = (MainActivity.PartsAdapter.ViewHolder) myView.getTag();
            Parts part = parts.get(position);

            String nom = part.getPart().getName();
            holder.tvNom.setText(nom);

            Picasso.with(this.context).load(part.getPart().getPartImgUrl()).into(holder.myImage, new Callback() {
                @Override
                public void onSuccess() {}
                @Override
                public void onError() {}
            });

            return myView;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
