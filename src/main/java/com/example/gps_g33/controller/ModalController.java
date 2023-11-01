package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModalController{
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
        // Lógica para criar um novo funcionário com os dados inseridos
        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        String dataNascimento = dataNascimentoPicker.getValue().toString();

        Funcionario funcionario = new Funcionario(0, nome, sobrenome, dataNascimento, nif, contato, email);

        if (callback != null) {
            callback.onFuncionarioCriado(funcionario);
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
