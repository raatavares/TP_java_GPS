package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import com.example.gps_g33.ModelManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NavBarController {
    ModelManager model;

    @FXML
    public Button funcionariosButton;
    public Button buttonHomeResidentes;
    public Button buttonHomeFuncionarios;
    public Button buttonHomeAvisos;
    public StackPane contentArea;

    public Text lblDate;


    @FXML
    public void initialize() {
        Date dataAtual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm | dd-MM");
        String data = formato.format(dataAtual);
        lblDate.setText(data);
        this.model = new ModelManager();
    }

    public void switchToHome() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/Gerencia_Home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void switchToFuncionarios() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/Gerencia_Funcionarios.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void switchToResidentes() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/Gerencia_Residentes.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}
