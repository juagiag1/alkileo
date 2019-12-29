package com.example.alkileov1.Objetos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Notificacion {

    private int id, padre, num_alq;
    private String titulo, descripcion, data;

    private Bitmap foto;

    public Notificacion() {
    }

    public Notificacion(int id, int padre, int num_alq, Bitmap foto, String titulo, String descripcion) {
        this.id = id;
        this.padre = padre;
        this.num_alq = num_alq;
        this.foto = foto;
        this.titulo = titulo;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        try {
            byte[] byteData = Base64.decode(data, Base64.DEFAULT);
            this.foto = BitmapFactory.decodeByteArray( byteData, 0,
                    byteData.length);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap getFoto() {
        return foto;
    }
}
