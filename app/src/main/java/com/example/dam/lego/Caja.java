package com.example.dam.lego;
import android.media.Image;
import java.util.ArrayList;
import java.util.List;

public class Caja {
        /*public class Piezas {
             String nombre;
             Image imagenPieza;
             Integer cantidad;
        }*/
        private String codigo;
        private String descripcion;
        private Image imagenCaja;
        //private List<Piezas> piezas;


    public Caja() {
    }

    public Caja(String codigo, String descripcion, Image imagenCaja) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.imagenCaja = imagenCaja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Image getImagenCaja() {
        return imagenCaja;
    }

    public void setImagenCaja(Image imagenCaja) {
        this.imagenCaja = imagenCaja;
    }

}
