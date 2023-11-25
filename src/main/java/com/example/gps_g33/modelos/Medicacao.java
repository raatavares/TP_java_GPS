package com.example.gps_g33.modelos;

public class Medicacao {
    private int id;
    private String nome;
    private String dataConsultaMedicacao;
    private String medicacao;
    private String nif;

    public Medicacao(int id, String nome, String dataConsultaMedicacao, String medicacao, String nif) {
        this.id = id;
        this.nome = nome;
        this.dataConsultaMedicacao = dataConsultaMedicacao;
        this.medicacao = medicacao;
        this.nif = nif;
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
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDataConsultaMedicacao() {
        return dataConsultaMedicacao;
    }

    public void setDataConsultaMedicacao(String dataConsultaMedicacao) {
        this.dataConsultaMedicacao = dataConsultaMedicacao;
    }

    public String getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(String descricao) {
        this.medicacao = descricao;
    }
}
