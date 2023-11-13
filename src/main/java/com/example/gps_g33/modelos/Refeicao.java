package com.example.gps_g33.modelos;

public class Refeicao {
    private int id;
    private String nome;
    private String dataRefeicao;
    private String descricao;
    private String nif;
    private String tipoDieta;
    public Refeicao(int id, String nome, String dataRefeicao, String descricao, String nif,String tipoDieta) {
        this.id = id;
        this.nome = nome;
        this.dataRefeicao = dataRefeicao;
        this.descricao = descricao;
        this.nif = nif;
        this.tipoDieta = tipoDieta;
    }

    public String getTipoDieta() {
        return tipoDieta;
    }

    public void setTipoDieta(String tipoDieta) {
        this.tipoDieta = tipoDieta;
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

    public String getDataRefeicao() {
        return dataRefeicao;
    }

    public void setDataRefeicao(String dataRefeicao) {
        this.dataRefeicao = dataRefeicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public String toString() {
        return "Refeicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataRefeicao='" + dataRefeicao + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nif='" + nif + '\'' +
                ", tipoDieta='" + tipoDieta + '\'' +
                '}';
    }
}
