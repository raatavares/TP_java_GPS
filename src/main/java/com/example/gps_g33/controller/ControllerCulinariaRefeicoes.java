package com.example.gps_g33.controller;

import com.example.gps_g33.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerCulinariaRefeicoes{
    public Button buttonToAddRefeicao;
    public Button buttonToEditRefeicao;
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

    public void handleToAddRefeicao() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_CriarRefeicao.fxml"));
            Parent popupRoot = loader.load();

            // Criar uma nova cena com a janela pop-up
            Scene popupScene = new Scene(popupRoot);

            // Criar um novo palco (Stage) para a janela pop-up
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Torna a janela pop-up modal
            popupStage.setTitle("Adicionar Refeição"); // Defina o título da janela pop-up
            popupStage.setScene(popupScene);

            // Mostrar a janela pop-up
            popupStage.showAndWait(); // Espere até que a janela pop-up seja fechada

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void handleToEditRefeicao() {
        try {
            // Carregar o FXML da janela pop-up
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/depCulinaria/Culinaria_EditRefeicao.fxml"));
            Parent popupRoot = loader.load();

            // Criar uma nova cena com a janela pop-up
            Scene popupScene = new Scene(popupRoot);

            // Criar um novo palco (Stage) para a janela pop-up
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL); // Torna a janela pop-up modal
            popupStage.setTitle("Editar Refeição"); // Defina o título da janela pop-up
            popupStage.setScene(popupScene);

            // Mostrar a janela pop-up
            popupStage.showAndWait(); // Espere até que a janela pop-up seja fechada

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
