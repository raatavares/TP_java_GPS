package com.example.gps_g33.controller.depCulinaria;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CulinariaResidentesController {
    public Button buttonToRefeicoes;
    public Button buttonToResidentes;

    public TableView<Residente> tableViewResidentes;
    public TableColumn<Residente, Integer> idColumn;
    public TableColumn<Residente, String> nomeColumn;
    public TableColumn<Residente, String> dataNascimentoColumn;
    public TableColumn<Residente, String> nifColumn;
    public TableColumn<Residente, String> contatoColumn;
    public TableColumn<Residente, String> emailColumn;
    public TableColumn<Residente, String> prefAliColumn;
    public TableColumn<Residente, String> alergiasColumn;
    public TextField searchField;

    private Data data;
    public StackPane contentArea;


    @FXML
    public void initialize() {
        data = Data.getInstance();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        prefAliColumn.setCellValueFactory(new PropertyValueFactory<>("prefAli"));
        alergiasColumn.setCellValueFactory(new PropertyValueFactory<>("alergias"));


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
