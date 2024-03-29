package com.example.gps_g33.modelos;

import com.calendarfx.model.Entry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Visita {
    private String title;
    private String id;
    private boolean fullDay;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String zoneId;
    private boolean recurring;
    private String rrule;
    private boolean recurrence;

    private List<Integer> idFamiliares;

    public Visita(String title, String id, boolean fullDay, String startDate, String endDate, String startTime, String endTime, String zoneId, boolean recurring, String rrule, boolean recurrence, int idFamiliar) {
        this.title = title;
        this.id = id;
        this.fullDay = fullDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.zoneId = zoneId;
        this.recurring = recurring;
        this.rrule = rrule;
        this.recurrence = recurrence;
        this.idFamiliares.add(idFamiliar);
    }

    public Visita(String title, String startTime, String endTime, String startDate, String endDate){
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.idFamiliares = new ArrayList<>();
    }

    public Visita(Entry c){
        this.title = c.getTitle();
        this.id = c.getId();
        this.fullDay = c.isFullDay();
        this.startDate = c.getStartDate().toString();
        this.endDate = c.getEndDate().toString();
        this.startTime = c.getStartTime().toString();
        this.endTime = c.getEndTime().toString();
        this.zoneId = c.getZoneId().toString();
        this.recurring = c.isRecurring();
        this.rrule = c.getRecurrenceRule();
        this.recurrence = c.isRecurrence();
        this.idFamiliares = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public boolean isFullDay() {
        return fullDay;
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

    public String getZoneId() {
        return zoneId;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public String getRrule() {
        return rrule;
    }

    public boolean isRecurrence() {
        return recurrence;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getFamiliares() {
        return idFamiliares;
    }

    public void addFamiliarId(int id) {
        this.idFamiliares.add(id);
    }

    public boolean isFamiliarId(int id) {
        return this.idFamiliares.contains(id);
    }

    public void removeFamiliarId(int id) {
        this.idFamiliares.remove(Integer.valueOf(id));
    }
}
