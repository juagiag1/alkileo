package com.example.alkileov1.Objetos;

public class Alquiler {
    int id, num_alq, id_prop;
    String titulo;

    public Alquiler() {
    }

    public Alquiler(int id, int num_alq, int id_prop, String titulo) {
        this.id = id;
        this.num_alq = num_alq;
        this.id_prop = id_prop;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_alq() {
        return num_alq;
    }

    public void setNum_alq(int num_alq) {
        this.num_alq = num_alq;
    }

    public int getId_prop() {
        return id_prop;
    }

    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
