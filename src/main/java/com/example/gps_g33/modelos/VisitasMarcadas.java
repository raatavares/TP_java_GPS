package com.example.gps_g33.modelos;

import java.util.ArrayList;
import java.util.List;

public class VisitasMarcadas {
    private List<FamiliarParaMarcacao> familiares;
    private int id;
    private String titulo;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    public Data data;

    public VisitasMarcadas(int id, FamiliarParaMarcacao familiar, String titulo, String startDate, String endDate, String startTime, String endTime) {
        this.id = id;
        this.familiares = new ArrayList<>();
        this.familiares.add(familiar);
        this.titulo = titulo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void adicionarNomeVisitante(FamiliarParaMarcacao familiar) {
        this.familiares.add(familiar);
    }


    public List<FamiliarParaMarcacao> getFamiliares() {
        return familiares;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setId(int idVisitante) {
        this.id = idVisitante;
    }
}
