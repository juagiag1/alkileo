package com.example.alkileov1.Objetos;

import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.File;
import java.sql.Blob;

public class Documento {
    private int id, padre, num_alq;
    private  String titulo, archivo;







    public Documento() {
    }

    public Documento(int id, int padre, int num_alq, String titulo, String archivo) {
        this.id = id;
        this.padre = padre;
        this.num_alq = num_alq;
        this.titulo = titulo;
        this.archivo = archivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public int getNum_alq() {
        return num_alq;
    }

    public void setNum_alq(int num_alq) {
        this.num_alq = num_alq;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
