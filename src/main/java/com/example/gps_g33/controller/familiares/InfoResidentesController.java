package com.example.gps_g33.controller.familiares;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Medicacao;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InfoResidentesController {
    private Data data;
    @FXML
    public TableView<Medicacao> tableViewResidentes;

    public TableColumn<Residente, Integer> idColumn;

    public TableColumn<Residente, String> nomeColumn;

    public TableColumn<Residente, String> dataConsultaColumn;

    public TableColumn<Residente, String> medicacaoColumn;

    public TextField searchField;

    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataConsultaColumn.setCellValueFactory(new PropertyValueFactory<>("dataConsultaMedicacao"));
        medicacaoColumn.setCellValueFactory(new PropertyValueFactory<>("medicacao"));
        tableViewResidentes.setPlaceholder(new Label("Deve inserir o nome do residente para pesquisar"));



    }

    public void updateTable() {
        tableViewResidentes.getItems().clear();
        tableViewResidentes.getItems().addAll(data.getMedicacoes());
    }

    public void onSearch() {

        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Medicacao> residentesFiltrados = data.getMedicacoes().stream()
                .filter(residente -> residente.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableViewResidentes.getItems().clear();

        if(residentesFiltrados.size()==0) {
            tableViewResidentes.setPlaceholder(new Label("Não foram encontrados residentes com esse nome"));
        }else{
            tableViewResidentes.setItems(FXCollections.observableArrayList(residentesFiltrados));
        }

    }
}
