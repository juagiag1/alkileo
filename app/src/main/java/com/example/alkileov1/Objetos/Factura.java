package com.example.alkileov1.Objetos;

public class Factura {
    private int id, padre, num_alq;
    private  String concepto, archivo;
    private  float importe;

    public Factura() {
    }

    public Factura(int id, int padre, int num_alq, String concepto, String archivo, float importe) {
        this.id = id;
        this.padre = padre;
        this.num_alq = num_alq;
        this.concepto = concepto;
        this.archivo = archivo;
        this.importe = importe;
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
}
