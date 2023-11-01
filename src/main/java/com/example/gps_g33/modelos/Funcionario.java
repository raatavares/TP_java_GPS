package com.example.gps_g33.modelos;

import java.util.Date;

public class Funcionario {
    private int id;
    private String nome;
    private String sobrenome;
    private String dataNascimento;
    private String nif;
    private String contato;
    private String email;

    // Construtor da classe
    public Funcionario(int id, String nome, String sobrenome, String dataNascimento, String nif, String contato, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.nif = nif;
        this.contato = contato;
        this.email = email;
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

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
}
