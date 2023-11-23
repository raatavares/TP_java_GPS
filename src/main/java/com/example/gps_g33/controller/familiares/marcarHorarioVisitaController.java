package com.example.gps_g33.controller.familiares;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.List;
import com.example.gps_g33.modelos.Visita;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class marcarHorarioVisitaController {
    @FXML
    public TableView<Visita> tableView;

    @FXML
    public TableColumn<Visita, String> titleColumn;
    @FXML
    public TableColumn<Visita, String> startDateColumn;
    @FXML
    public TableColumn<Visita, String> startTimeColumn;
    @FXML
    public TableColumn<Visita, String> endDateColumn;
    @FXML
    public TableColumn<Visita, String> endTimeColumn;

    @FXML
    public ComboBox<LocalTime> timeSelector;

    public LocalTime minTime = LocalTime.MAX;
    public LocalTime maxTime = LocalTime.MIN;

    public void initialize() {
        configureTableColumns();
        populateTimeSelector();
        loadVisitSchedules();
    }

    public void configureTableColumns() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    public void populateTimeSelector() {
        timeSelector.getItems().clear(); // Limpa itens anteriores

        LocalTime time = minTime;
        while (time.isBefore(maxTime)) {
            timeSelector.getItems().add(time);
            time = time.plusHours(1);
        }
    }

    public void loadVisitSchedules() {
        try {
            Gson gson = new Gson();
            Type horarioVisitaListType = new TypeToken<List<Visita>>(){}.getType();
            List<Visita> horariosOriginais = gson.fromJson(new FileReader("Dados/visitas.json"), horarioVisitaListType);
            List<Visita> horariosProcessados = new ArrayList<>();

            for (Visita horario : horariosOriginais) {
                LocalTime startTime = LocalTime.parse(horario.getStartTime());
                LocalTime endTime = LocalTime.parse(horario.getEndTime());
            // Verifica se a visita precisa ser dividida
            if (startTime.plusHours(1).isBefore(endTime)) {
                // Divide a visita em intervalos de 1 hora
                while (startTime.plusHours(1).isBefore(endTime)) {
                    Visita novaVisita = new Visita(horario.getTitle(), startTime.toString(), startTime.plusHours(1).toString());
                    horariosProcessados.add(novaVisita);
                    startTime = startTime.plusHours(1);
                }
                // Adiciona o último intervalo
                horariosProcessados.add(new Visita(horario.getTitle(), startTime.toString(), endTime.toString()));
            } else {
                // Adiciona a visita original
                horariosProcessados.add(horario);
            }

            // Atualiza os horários mínimos e máximos
            if (startTime.isBefore(minTime)) {
                minTime = startTime;
            }
            if (endTime.isAfter(maxTime)) {
                maxTime = endTime;
            }
        }

        tableView.getItems().setAll(horariosProcessados);
        populateTimeSelector();
    } catch (IOException e) {
        e.printStackTrace();
        // Implementar tratamento de erro
    }
    }

    @FXML
    public void handleBook() {
        Visita horarioSelecionado = tableView.getSelectionModel().getSelectedItem();
        LocalTime tempoSelecionado = timeSelector.getSelectionModel().getSelectedItem();

        if (horarioSelecionado != null && tempoSelecionado != null) {
            // Implementar a lógica da reserva aqui
            System.out.println("Reserva feita para: " + horarioSelecionado.getTitle() + " às " + tempoSelecionado);
        }
    }

    @FXML
    public void handleCancel() {
        // Implementar a lógica de cancelamento aqui
        System.out.println("Reserva cancelada.");
    }
}
