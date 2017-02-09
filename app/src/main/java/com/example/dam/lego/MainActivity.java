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
    //Creo las clases y los elementos del layout necesarios
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
        //Relaciono las variables con los elementos de la vista
        spinner = (Spinner) findViewById(R.id.spinner_cajas);
        editText = (EditText) findViewById(R.id.codigo);
        button = (Button) findViewById(R.id.btn_buscar);
        lista_parts = (ListView) findViewById(R.id.lista_partes);
        editText.setText("");
        //Creo el evento del boton para buscar
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamo a la función de descarga
                downloadBox();
            }
        });
        //Añado un onItemSelectedListener al spinner para que cuando cambie la selección cambien la lista de piezas
        spinner.setOnItemSelectedListener(this);
    }
    //Creo una función para la descarga las cajas
    public void downloadBox() {
        //Creo un WebDownloader para que se descargue
        WebDownloader dd = new WebDownloader(this, editText.getText().toString());
        //Indico que se modifique aquí
        dd.setMainActivity(this);
        //Ejecuto la descarga
        dd.execute();
    }
    //Recibo la información descargada de las cajas
    public void notifyCajaList(CajaList cajaList) {
        //indico que la variable que he creado antes "cajaList" es igual a la que recibo de la descarga
        this.cajaList = cajaList;
        //Como en el adapter me pide un identificador de tipo Long pero en la descarga no hay ninguno, cambio la información del año de la caja ya que no la útilizo
        //por un incrementable que creo yo, de está manera cada caja tendrá un identificador númerico
        int i = 0;
        for(Caja c : cajaList.getResults()){
            c.setYear(i);
            i++;
        }
        //Creo un nuevo adapter para llenar el Spinner (este adapter hará referencia al layout "lisa_caja")
        BoxAdapter adapter = new BoxAdapter(this, cajaList.getResults());
        //Lleno el spinner con el adapter
        spinner.setAdapter(adapter);
    }
    //Creo un adapter propio para las cajas
    public class BoxAdapter extends BaseAdapter {
        //Creo una variable context, que indica que el adapter se ejecutará en esta MainActivity, y una lista de Caja, que es donde está la información de las cajas
        private Context context;
        private List<Caja> cajas;
        //Creo el constructor
        public BoxAdapter(Context context, List<Caja> cajas) {
            this.context = context;
            this.cajas = cajas;
        }
        //Creo las variables del adapter que necesito para la información que voy a mostrar
        public class ViewHolder {
            public TextView tvNom;
            public ImageView myImage;
            public TextView tvDescripcion;
        }
        //Indico la longitud del Spinner
        @Override
        public int getCount() {
            return cajas.size();
        }
        //Indico que objeto va a cada posición del spinner
        @Override
        public Object getItem(int position) {
            return cajas.get(position);
        }
        //Indico una id Long para cada caja (la que he creado antes ya que no tenía inicialmente)
        @Override
        public long getItemId(int position) {
            Caja c = cajas.get(position);
                long id = c.getYear();
            return id;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Creo una nueva vista y indico que es igual a la vista que le he pasado
            View myView = convertView;
            //Si la vista que le he pasado no existe, entonces inflamos
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.lista_cajas, parent, false);
                ViewHolder holder = new ViewHolder();
                //Relaciono las variables con las del layout
                holder.tvNom = (TextView) myView.findViewById(R.id.nom);
                holder.myImage = (ImageView) myView.findViewById(R.id.imatge);
                holder.tvDescripcion = (TextView) myView.findViewById(R.id.descripcion);
                myView.setTag(holder);
            }
            //Selecciono la caja
            ViewHolder holder = (ViewHolder) myView.getTag();
            Caja caja = cajas.get(position);
            //Modifico las variables del layout con la información que quiero mostrar de la caja
            String nom = caja.getName();
            holder.tvNom.setText(nom);
            //Con la libreria/api de Picasso para descargar y modificar la imagen
            Picasso.with(this.context).load(caja.getSetImgUrl()).into(holder.myImage, new Callback() {
                @Override
                public void onSuccess() {}
                @Override
                public void onError() {}
            });

            String descripcion = caja.getSetNum();
            holder.tvDescripcion.setText(descripcion +"");
            //Devuelvo la vista
            return myView;
        }
    }
    //Indico un onItemSelected para que cuando seleccione una caja del Spinner descargue las partes de la caja
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Selecciono el codigo de la caja
        String codigoCaja = cajaList.getResults().get(position).getSetNum();
        //Llamo a la función que descarga las partes de la caja
        downloadParts(codigoCaja);
    }
    //Función de para descargar las partes
    public void downloadParts(String codigo) {
        //Creo un DownloaderParts para descargar las partes que tiene la caja que se le pasa
        DownloaderParts dd = new DownloaderParts(this, codigo);
        dd.setMainActivity(this);
        //Ejecutamos la descarga
        dd.execute();
    }
    //Obtenemos la descarga de las partes
    public void notifyPartsList(final PartsList partsList) {
        this.partsList = partsList;
        //Creamos el adapter para la lista
        PartsAdapter adapter = new PartsAdapter(this, partsList.getResults());
        //llenamos la vista con el adapter
        lista_parts.setAdapter(adapter);
        //Creamos un OnItemClickListener para que cuando haga click encima de un item de la lista vaya a otra vista y le envie información para mostrar
        lista_parts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //creamos el intent para ir a la siguiente activity
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                //creamos los putExtra para pasarle la información que queremos
                intent.putExtra("cantidad", partsList.getResults().get(position).getQuantity().toString());
                intent.putExtra("nombre", partsList.getResults().get(position).getPart().getName());
                intent.putExtra("color", partsList.getResults().get(position).getColor().getName());
                intent.putExtra("url", partsList.getResults().get(position).getPart().getPartImgUrl());
                intent.putExtra("urlWeb", partsList.getResults().get(position).getPart().getPartUrl());
                //iniciamos el intent
                startActivity(intent);
            }
        });

    }
    //Adapter para las partes, es exactamente igual que el de la caja, pero este hace referencia al layout "lista_piezas"
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
    @Override public void onNothingSelected(AdapterView<?> parent) {}

}
