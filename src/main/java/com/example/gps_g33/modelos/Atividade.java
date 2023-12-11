package com.example.gps_g33.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Atividade {
    private int id;
    private String dataAtividade;
    private String tipoAtividade; // Exemplo: piscina, teatro, etc.
    private String responsavel;
    private String descricao; // Descrição opcional da atividade
    private List<Integer> Participantes = new ArrayList<>(); // Lista de participantes na atividade

    public Atividade(int id, String dataAtividade, String tipoAtividade, String descricao, List<Integer> participantes) {
        this.id = id;
        this.dataAtividade = dataAtividade;
        this.tipoAtividade = tipoAtividade;
        this.descricao = descricao;
        if(participantes != null)
            this.Participantes = participantes;
        else
            this.Participantes = new ArrayList<>();
    }



    // Getters e setters para cada propriedade
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getParticipantes() {
        return Participantes;
    }

    public void setParticipantes(List<Integer> participantes) {
        this.Participantes = participantes;
    }

    public void addParticipante(int id) {
        this.Participantes.add(id);
    }

    public void removeParticipante(int id) {
        if (Participantes.contains(id)) {
            Participantes.remove(Integer.valueOf(id)); // Remove the first occurrence of the specified id
        } else {
            System.out.println("Não existe o id " + id + " na lista de participantes");
        }
    }

}
