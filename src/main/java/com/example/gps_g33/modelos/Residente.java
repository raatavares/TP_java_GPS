package com.example.gps_g33.modelos;

public class Residente {
    private int id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String nif;
    private String contato;
    private String email;

    private String prefAli;

    private String alergias;

    // Construtor da classe
    public Residente(int id, String nome, String sobrenome, String dataNascimento, String nif, String contato, String email, String prefAli, String alergias) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nif = nif;
        this.contato = contato;
        this.email = email;
        this.prefAli = prefAli;
        this.alergias = alergias;
    }

    // Métodos getters e setters

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String familiar) {
        this.sobrenome = familiar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrefAli(String prefAli) {
        this.prefAli = prefAli;
    }

    public String getPrefAli() {
        return prefAli;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getAlergias() {
        return alergias;
    }
}

