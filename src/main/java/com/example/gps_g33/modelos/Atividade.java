package com.example.gps_g33.modelos;

public class Atividade {
    private int id;
    private String nome;
    private String dataAtividade;
    private String tipoAtividade; // Exemplo: piscina, teatro, etc.
    private String responsavel;
    private String descricao; // Descrição opcional da atividade
    private String nif;

    public Atividade(int id, String nome, String dataAtividade, String tipoAtividade, String descricao, String nif) {
        this.id = id;
        this.nome = nome;
        this.dataAtividade = dataAtividade;
        this.tipoAtividade = tipoAtividade;
        this.descricao = descricao;
        this.nif = nif;
    }

    // Getters e setters para cada propriedade
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

    public String getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(String dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public String getTipoAtividade() {
        return tipoAtividade;
    }

    public void setTipoAtividade(String tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
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
}
