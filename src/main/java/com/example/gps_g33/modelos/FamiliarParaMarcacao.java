package com.example.gps_g33.modelos;

public class FamiliarParaMarcacao {

    private String nome;
    private String nif;
    public FamiliarParaMarcacao(String nome, String nif) {
        this.nome = nome;
        this.nif = nif;
    }
    public String getNome() {
        return nome;
    }
    public String getNif() {
        return nif;
    }
}
