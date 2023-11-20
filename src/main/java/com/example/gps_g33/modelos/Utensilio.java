package com.example.gps_g33.modelos;

public class Utensilio {
    private int id;
    private String nome;
    private boolean falta;

    public Utensilio(int id, String nome, boolean falta) {
        this.id = id;
        this.nome = nome;
        this.falta = falta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getFalta() {
        return falta;
    }

    public void setFalta(boolean falta) {
        this.falta = falta;
    }
}
