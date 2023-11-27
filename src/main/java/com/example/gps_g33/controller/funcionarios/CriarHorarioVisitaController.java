package com.example.gps_g33.controller.funcionarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

import com.calendarfx.model.*;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.view.CalendarView;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Visita;
import impl.com.calendarfx.view.ZoneIdStringConverter;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;

import static com.calendarfx.model.CalendarEvent.*;

public class CriarHorarioVisitaController {

    @FXML
    public CalendarView calendarView;
    private static Data data;
    public void initialize() throws Exception {
        data = Data.getInstance();

        CalendarSource myCalendarSource = new CalendarSource();

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        calendarView.setShowAddCalendarButton(false);

        EventHandler<CalendarEvent> handler = event -> handleEvent1(event);
        calendarView.getCalendars().forEach(calendar -> calendar.addEventHandler(handler));

        //apenas uma evento por hora
        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();


        List<Visita> visitasList = data.getCalendarData();
        //Criar eventos para vagasVisitas
        if(data.getCalendarData() != null){
            for (Visita p:visitasList){
                Entry<String> entry = new Entry<>(p.getTitle());
                LocalDate startDate = LocalDate.parse(p.getStartDate());
                LocalDate endDate = LocalDate.parse(p.getEndDate());

                entry.setInterval(startDate.atTime(LocalTime.parse(p.getStartTime())),endDate.atTime(LocalTime.parse(p.getEndTime())));

                ZoneId zoneId = ZoneId.of(p.getZoneId());
                entry.setZoneId(zoneId);

                entry.setFullDay(p.isFullDay());
                entry.setRecurrenceRule(p.getRrule());
                entry.setId(p.getId());
                calendarView.getCalendars().get(0).addEntry(entry);
        }
    }


    }

    private void handleEvent1(CalendarEvent event) {
        //Se event for adicionado ao vagasVisitas então adicionar ao ficheiro json
        System.out.println("Tipo Evento: " + event.getEventType());

        EventType type = event.getEventType();


        if(event.isEntryRemoved()){
            System.out.println("Evento removido");
            data.removeCalendarEvent(event.getEntry().getId());
        }else if(event.isEntryAdded()){
            System.out.println("Evento adicionado");
            data.addCalendarEvent(event.getEntry());

        }else if(type == ENTRY_INTERVAL_CHANGED || type == ENTRY_TITLE_CHANGED || type == ENTRY_FULL_DAY_CHANGED || type == ENTRY_LOCATION_CHANGED || type == ENTRY_RECURRENCE_RULE_CHANGED  || type == ENTRY_USER_OBJECT_CHANGED){ // Evento alterado por algum motivo
            System.out.println("Evento alterado");
            data.updateCalendarEvent(event.getEntry());
            if(data.updateCalendarEvent(event.getEntry())){
                calendarView.refreshData();
            }
        }




        /*if(event.getCalendar().getName().equals("Visitas - Livres")){
            System.out.println("Calendar Name: " + event.getCalendar().getName());
            if(event.isEntryAdded()){
                System.out.println("Adicionado ao ficheiro json");
                System.out.println(event.getEntry());
                data.addCalendarEvent(event.getEntry());
            }
        }*/
    }

}