package com.example.gps_g33.controller.depCulinaria;

import com.example.gps_g33.controller.ModalCallback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalControllerResidentes {

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }


    @FXML
    public TextField nomeField;


    @FXML
    public TextField nifField;

    @FXML
    public Button criarButton;

    @FXML
    public Button cancelarButton;



    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void handleCriarButton(ActionEvent actionEvent) {
    }
}
