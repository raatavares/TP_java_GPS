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
import javafx.fxml.FXML;

import static com.calendarfx.model.CalendarEvent.*;

public class CriarHorarioVisitaController {

    @FXML
    public CalendarView calendarView;
    private static Data data;
    public void initialize() throws Exception {
        data = Data.getInstance();


        Calendar vagasVisitas = new Calendar("Visitas - Livres");
        Calendar visitas = new Calendar("Visitas - Ocupadas");
        visitas.setStyle(Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("Visitas - Lar de Idosos");
        myCalendarSource.getCalendars().addAll(vagasVisitas,visitas);

        calendarView.getCalendarSources().addAll(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        calendarView.setShowAddCalendarButton(false);

        EventHandler<CalendarEvent> handler = event -> handleEvent1(event);
        vagasVisitas.addEventHandler(handler);

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
                vagasVisitas.addEntries(entry);
        }
    }



/*        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendar.setToday(LocalDate.now());
                        calendar.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
        };*/


    }

    //Criar handle para calendarview[0} e adicionar evento

    private static void handleEvent1(CalendarEvent event) {
        //Se event for adicionado ao vagasVisitas então adicionar ao ficheiro json
        System.out.println("Calendar Event Type: " + event.getEventType());

        if(event.isEntryRemoved()){
            System.out.println("Removido do ficheiro json");
            data.removeCalendarEvent(event.getEntry().getId());
            return;
        }

        if(event.getCalendar().getName().equals("Visitas - Livres") && !event.getEntry().getTitle().equals("New Entry")){
            System.out.println("Calendar Name: " + event.getCalendar().getName());
            /*if(event.isEntryAdded()){*/
                System.out.println("Adicionado ao ficheiro json");
                System.out.println(event.getEntry());
                data.addCalendarEvent(event.getEntry());
          //  }
        }
    }

}