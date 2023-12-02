package com.example.gps_g33.controller.familiares;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.page.WeekPage;
import com.calendarfx.view.popover.PopOverContentPane;
import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.controller.depClinico.ModalControllerConsultasMedicacao;
import com.example.gps_g33.modelos.*;
import com.example.gps_g33.util.CustomPopOver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;

import static com.calendarfx.model.Calendar.Style.STYLE2;



public class marcarHorarioVisitaController {

    public Data data;
    @FXML
    public WeekPage weekPage;
    public void initialize() {
        data = Data.getInstance();

        CalendarSource myCalendarSource = new CalendarSource();

        weekPage.getCalendarSources().addAll(myCalendarSource);

        weekPage.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        weekPage.setToday(LocalDate.now());
                        weekPage.setTime(LocalTime.now());
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
        LocalDateTime currentDateAndTime = LocalDateTime.now(); // Data e hora atuais

        if(data.getCalendarData() != null){
            for (Visita p:visitasList){
                LocalDateTime startDateTime = LocalDateTime.of(LocalDate.parse(p.getStartDate()), LocalTime.parse(p.getStartTime()));
                //Mostrar apenas as visitas que não estão cheias e as que são depois da data atual (não mostrar visitas passadas)

                if(p.getFamiliares().size() < 5 && startDateTime.isAfter(currentDateAndTime) ) {
                    Entry<String> entry = new Entry<>(p.getTitle());
                    LocalDate startDate = LocalDate.parse(p.getStartDate());
                    LocalDate endDate = LocalDate.parse(p.getEndDate());

                    entry.setInterval(startDate.atTime(LocalTime.parse(p.getStartTime())),endDate.atTime(LocalTime.parse(p.getEndTime())));

                    ZoneId zoneId = ZoneId.of(p.getZoneId());
                    entry.setZoneId(zoneId);

                    entry.setFullDay(p.isFullDay());
                    entry.setRecurrenceRule(p.getRrule());
                    entry.setId(p.getId());

                    if(p.isFamiliarId(data.getIdFamiliar()))
                        entry.getStyleClass().add("customStyle");

                    weekPage.getCalendars().get(0).addEntry(entry);
                }

            }
        }


        weekPage.setEntryDetailsPopOverContentCallback(param -> {
            PopOver popOver = new PopOver();
            CustomPopOver customPopOver = new CustomPopOver(popOver, param.getEntry());
            return customPopOver;

        });
    }
}
