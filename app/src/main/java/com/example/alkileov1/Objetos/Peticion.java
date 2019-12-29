package com.example.alkileov1.Objetos;

public class Peticion {
    int id, id_prop, id_inq,num_alq, estado;
    String comentario;

    public String getNombre_prop() {
        return nombre_prop;
    }

    public void setNombre_prop(String nombre_prop) {
        this.nombre_prop = nombre_prop;
    }

    String nombre_prop;

    public Peticion(int id, int id_prop, int id_inq, int num_alq, int estado, String comentario) {
        this.id = id;
        this.id_prop = id_prop;
        this.id_inq = id_inq;
        this.num_alq = num_alq;
        this.estado = estado;
        this.comentario = comentario;
    }

    public Peticion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_prop() {
        return id_prop;
    }

    public void setId_prop(int id_prop) {
        this.id_prop = id_prop;
    }

    public int getId_inq() {
        return id_inq;
    }

    public void setId_inq(int id_inq) {
        this.id_inq = id_inq;
    }

    public int getNum_alq() {
        return num_alq;
    }

    public void setNum_alq(int num_alq) {
        this.num_alq = num_alq;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
