package com.fes.notas.BD;

public class Nota {

    String id;
    String tituloN;
    String textoN;

    public Nota() {
    }

    public Nota(String id, String tituloN, String textoN) {
        this.id = id;
        this.tituloN = tituloN;
        this.textoN = textoN;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTituloN() {
        return tituloN;
    }

    public void setTituloN(String tituloN) {
        this.tituloN = tituloN;
    }

    public String getTextoN() {
        return textoN;
    }

    public void setTextoN(String textoN) {
        this.textoN = textoN;
    }
}
