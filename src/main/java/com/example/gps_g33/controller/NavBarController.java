package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NavBarController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public Button funcionariosButton;
    public Button buttonHomeResidentes;
    public Button buttonHomeFuncionarios;
    public Button buttonHomeAvisos;
    public StackPane contentArea;

    public void switchToHome() throws IOException {
        Parent fxml = FXMLLoader.load(HelloApplication.class.getResource("views/Gerencia_Home.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void switchToFuncionarios(ActionEvent event) throws IOException {
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
