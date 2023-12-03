package com.example.gps_g33.modelos;

import java.util.ArrayList;
import java.util.List;

public class Atividade {
    private int id;
    private List<String> nomes;
    private String dataAtividade;
    private String tipoAtividade; // Exemplo: piscina, teatro, etc.
    private String responsavel;
    private String descricao; // Descrição opcional da atividade
    private List<String> nifs;

    public Atividade(int id, List<String> nome, String dataAtividade, String tipoAtividade, String descricao, List<String> nif) {
        this.id = id;
        this.nomes = nome;
        this.dataAtividade = dataAtividade;
        this.tipoAtividade = tipoAtividade;
        this.descricao = descricao;
        this.nifs = nif;
    }
    public void adicionarNomeResidente(Residente residente) {
        if(this.nomes == null)
            this.nomes = new ArrayList<>();
        this.nomes.add(residente.getNome());
    }
    public void adicionarNifResidente(Residente residente) {
        if(this.nifs == null)
            this.nifs = new ArrayList<>();
        this.nifs.add(residente.getNif());
    }

    // Getters e setters para cada propriedade
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getNomes() {
        return nomes;
    }

    public void setNome(List<String> nome) {
        this.nomes = nome;
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

    public List<String> getNifs() {
        return nifs;
    }

    public void setNif(List<String> nif) {
        this.nifs = nif;
    }
}
