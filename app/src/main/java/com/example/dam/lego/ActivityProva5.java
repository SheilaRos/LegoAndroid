package com.example.dam.lego;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityProva5 extends AppCompatActivity {
    public class Product extends HashMap<String, Object> {
        private long id;
        private String name;
        private float price;
        private boolean inStock;
        private int image;

        public Product(int id, String name, float price, boolean inStock, int image) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.inStock = inStock;
            this.image = image;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public boolean isInStock() {
            return inStock;
        }

        public void setInStock(boolean inStock) {
            this.inStock = inStock;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova5);
        Spinner llista5 = (Spinner) findViewById(R.id.llista5);

        List<Product> dades = new ArrayList<>();
        dades.add(new Product(1, "Disc dur 1TByte", 70.0f, true, R.drawable.discdur));
        dades.add(new Product(2, "Monitor 25'", 179.99f, false, R.drawable.monitor));
        dades.add(new Product(3, "Sorpresa", 19.99f, true, R.drawable.sorpresa));
        dades.add(new Product(4, "Arbol", 39.99f, false, R.drawable.arbre));
        dades.add(new Product(5, "Gorro", 29.99f, true, R.drawable.barret));
        dades.add(new Product(6, "Bola", 9.99f, true, R.drawable.bola));
        dades.add(new Product(7, "Campanetes", 9.99f, true, R.drawable.campanetes));
        dades.add(new Product(8, "Corona", 16.99f, false, R.drawable.corona));
        dades.add(new Product(9, "Santa", 29.99f, false, R.drawable.klaus));
        dades.add(new Product(10, "Tux", 109.99f, true, R.drawable.tux));
        dades.add(new Product(11, "Vidriera", 49.99f, true, R.drawable.vidre));

//        SimpleAdapter adapter = new SimpleAdapter(ActivityProva5.this, dades,
//                R.layout.llista5_item, new String[] {"name", "price", "inStock", "image"},
//                new int[] {R.id.nom, R.id.preu , R.id.stock, R.id.imatge});
//        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
//            @Override
//            public boolean setViewValue(View view, Object data, String textRepresentation) {
//                if(view.getId() == R.id.stock){
//                        TextView textView = (TextView) view;
//                        if ((boolean) data){
//                            textView.setText("En stock");
//                            textView.setTextColor(Color.GREEN);
//                        } else {
//                            textView.setText("No disponible");
//                            textView.setTextColor(Color.RED);
//                        }
//                        return true;
//                    }
//                return false;
//            }
//        });
        CatalogAdapter adapter = new CatalogAdapter(this, dades);
        llista5.setAdapter(adapter);
        llista5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ActivityProva5.this, "Click en "+id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ActivityProva5.this, "Click fora", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public class CatalogAdapter extends BaseAdapter {
        private Context context;
        private List<Product> catalog;

        public CatalogAdapter(Context context, List<Product> catalog) {
            this.context = context;
            this.catalog = catalog;
        }

        @Override
        public int getCount() {
            return catalog.size();
        }

        @Override
        public Object getItem(int position) {
            return catalog.get(position);
        }

        @Override
        public long getItemId(int position) {
            Product p = catalog.get(position);
            long id = p.getId();
            return id;
        }

        public class ViewHolder {
            public TextView tvNom;
            public ImageView myImage;
            public TextView tvPreu;
            public TextView tvStock;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = convertView;
            if (myView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.llista5_item, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.tvNom = (TextView) myView.findViewById(R.id.nom);
                holder.myImage = (ImageView) myView.findViewById(R.id.imatge);
                holder.tvPreu = (TextView) myView.findViewById(R.id.preu);
                holder.tvStock = (TextView) myView.findViewById(R.id.stock);
                myView.setTag(holder);
            }
            ViewHolder holder = (ViewHolder) myView.getTag();
            Product product = catalog.get(position);

            String nom = product.getName();
            holder.tvNom.setText(nom);

            Integer image = product.getImage();
            holder.myImage.setImageResource(image);

            float preu = product.getPrice();
            holder.tvPreu.setText(preu + "€");

            Boolean stock = product.isInStock();
            if (stock) {
                holder.tvStock.setText("Disponible");
                holder.tvStock.setTextColor(Color.GREEN);
            } else {
                holder.tvStock.setText("No disponible");
                holder.tvStock.setTextColor(Color.RED);
            }
            return myView;
        }
    }
}
