package com.example.gps_g33.controller.funcionarios;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Medicacao;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InfoResidentesController {
    private Data data;
    @FXML
    public TableView<Residente> tableViewResidentes;

    public TableColumn<Residente, Integer> idColumn;

    public TableColumn<Residente, String> nomeColumn;

    public TableColumn<Residente, String> dataNascimentoColumn;

    public TableColumn<Residente, String> contatoColumn;

    public TableColumn<Residente, String> problemasSaudeColumn;

    public TextField searchField;

    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        dataNascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        contatoColumn.setCellValueFactory(new PropertyValueFactory<>("contato"));
        problemasSaudeColumn.setCellValueFactory(new PropertyValueFactory<>("problemasSaude"));

        updateTable();
    }

    public void updateTable() {
        tableViewResidentes.getItems().clear();
        tableViewResidentes.getItems().addAll(data.getResidentes());
    }

    public void onSearch() {
        String nome = searchField.getText().toLowerCase(); // Converta para minúsculas para tornar a pesquisa não sensível a maiúsculas e minúsculas
        List<Residente> residentesFiltrados = data.getResidentes().stream()
                .filter(residente -> residente.getNome().toLowerCase().contains(nome))
                .collect(Collectors.toList());

        tableViewResidentes.setItems(FXCollections.observableArrayList(residentesFiltrados));
    }
}
