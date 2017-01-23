package com.example.dam.lego;
import android.media.Image;
import java.util.ArrayList;
import java.util.List;

public class Caja {
        public class Piezas {
             String nombre;
             Image imagenPieza;
             Integer cantidad;
        }

        private String codigo;
        private String descripcion;
        private Image imagenCaja;
        private List<Piezas> piezas;

        public Caja() {
                descripcion = "";
                imagenCaja  = null;
                List<Piezas> piezas = new ArrayList<>();
        }


}
