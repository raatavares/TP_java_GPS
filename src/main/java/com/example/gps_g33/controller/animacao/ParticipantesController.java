package com.example.gps_g33.controller.animacao;

import com.example.gps_g33.modelos.Atividade;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Residente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParticipantesController {
    private Data data;
    @FXML
    public TableView<Residente> tableViewParticipantes;
    @FXML
    public TableColumn<Residente, Integer> idColumn;
    @FXML
    public TableColumn<Residente, String> nomeColumnParti;
    @FXML
    public TableColumn<Residente, String> nifColumnParti;
    @FXML
    public TableView<Residente> tableViewResidentes;
    @FXML
    public TableColumn<Residente, String> nomeColumnResi;
    @FXML
    public TableColumn<Residente, String> nifColumnResi;
    @FXML
    public DatePicker datePicker;

    private Atividade atividade;

    private List<Integer> residentes;
    private List<Integer> participantes;

    @FXML
    public Button saveButton;

    @FXML
    public Button cancelButton;

    public void initialize() {
        data = Data.getInstance();

        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumnParti.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nifColumnParti.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nomeColumnResi.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nifColumnResi.setCellValueFactory(new PropertyValueFactory<>("nif"));
        TableColumn<Residente, Boolean> colBtn = new TableColumn<>("Ação");
        colBtn.setMinWidth(60);
        colBtn.setCellFactory(new Callback<TableColumn<Residente, Boolean>, TableCell<Residente, Boolean>>() {
            @Override
            public TableCell<Residente, Boolean> call(TableColumn<Residente, Boolean> p) {
                return new RemoverParticipante();
            }
        });

        TableColumn<Residente, Boolean> colBtn2 = new TableColumn<>("Ação");
        colBtn2.setMinWidth(60);
        colBtn2.setCellFactory(new Callback<TableColumn<Residente, Boolean>, TableCell<Residente, Boolean>>() {
            @Override
            public TableCell<Residente, Boolean> call(TableColumn<Residente, Boolean> p) {
                return new AdicionarParticipante();
            }
        });

        tableViewParticipantes.getColumns().add(colBtn);
        tableViewResidentes.getColumns().add(colBtn2);
        residentes = new ArrayList<>();
        participantes = new ArrayList<>();

        updateTable();
    }

    public class RemoverParticipante extends TableCell<Residente, Boolean> {
        final Button colBtn = new Button("Remover");
        RemoverParticipante() {
            colBtn.setOnAction(event -> {

                    Residente residenteId = getTableView().getItems().get(getIndex());

                    try {
                        if(!participantes.contains(residenteId.getId()))
                            return;
                        int residenteIndex = participantes.indexOf(residenteId.getId());

                        // Check if the residenteId is in the participantes list
                        if (residenteIndex != -1) {
                            participantes.remove(residenteIndex);
                            updateTable();
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            });


        }
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(colBtn);
            } else {
                setGraphic(null);
            }
        }
    }

    public class AdicionarParticipante extends TableCell<Residente, Boolean> {
        final Button colBtn = new Button("Adicionar");
        AdicionarParticipante() {
            colBtn.setOnAction(event -> {

                Residente residenteId = getTableView().getItems().get(getIndex());

                try {
                    if(participantes.contains(residenteId.getId()))
                        return;

                    participantes.add(residenteId.getId());
                    updateTable();

                } catch (Exception e) {
                    e.printStackTrace();
                }


                System.out.println("Participantes Adicionado" + residenteId.getId());

            });
        }

            @Override
            protected void updateItem(Boolean t, boolean empty) {
                super.updateItem(t, empty);
                if (!empty) {
                    setGraphic(colBtn);
                } else {
                    setGraphic(null);
                }
            }
        }
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
        LocalDate localDate = LocalDate.now();
        datePicker.setValue(localDate.parse(atividade.getDataAtividade()));

        participantes.addAll(atividade.getParticipantes());
        residentes.addAll(data.getResidentes().stream().map(Residente::getId).toList());
        updateTable();

        System.out.println("Atividade: " + this.atividade);
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        System.out.println("Cancel");

        Stage stage = (Stage)  cancelButton.getScene().getWindow();
        stage.close();

    }

    public void handleGuardarButton(ActionEvent actionEvent) {
        System.out.println("Guardar");
        atividade.setParticipantes(participantes);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    public void updateTable() {
        tableViewParticipantes.getItems().clear();
        if(atividade == null)
            return;


        if(participantes != null)
            for (Integer id : participantes)
                tableViewParticipantes.getItems().add(data.getResidentePorId(id));

        tableViewResidentes.getItems().clear();


        if(residentes != null)
            for (Integer id : residentes) {
                assert participantes != null;
                if(!participantes.contains(id))
                    tableViewResidentes.getItems().add(data.getResidentePorId(id));
            }


    }
}
