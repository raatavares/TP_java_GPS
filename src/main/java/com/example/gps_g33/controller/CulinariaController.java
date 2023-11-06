package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CulinariaController {

    public Button buttonToRefeicoes;
    public Button buttonToResidentes;
    public StackPane contentArea;
    public Text lblDate;

    @FXML
    public void initialize() {
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String data = formato.format(dataAtual);
        lblDate.setText(data);
    }
    public void switchToRefeicoes() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Refeicoes.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    public void switchToResidentes() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/depCulinaria/Culinaria_Residentes.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
}