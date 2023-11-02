package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalControllerResidente{
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public TextField nomeField;

    @FXML
    public TextField sobrenomeField;

    @FXML
    public DatePicker dataNascimentoPicker;

    @FXML
    public TextField nifField;

    @FXML
    public TextField contactoField;

    @FXML
    public TextField emailField;

    @FXML
    public Button criarButton;

    @FXML
    public Button cancelarButton;


    @FXML
    public void handleCriarButton() {
        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        String dataNascimento = dataNascimentoPicker.getValue().toString();

        Residente residente = new Residente(0, nome, sobrenome, dataNascimento, nif, contato, email);

        if (callback != null) {
            callback.onResidenteCriado(residente);
        }

        // Fechar o modal
        Stage stage = (Stage) criarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
