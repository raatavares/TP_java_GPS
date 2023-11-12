package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

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
        boolean camposValidos = true;

        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        LocalDate dataNascimento = dataNascimentoPicker.getValue();
        LocalDate dataAtual = LocalDate.now();

        if (nome.length() < 3) {
            nomeField.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            nomeField.setStyle(""); // Remover borda vermelha se estiver presente
        }

        if (sobrenome.length() < 3) {
            sobrenomeField.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            sobrenomeField.setStyle("");
        }

        if (email.length() < 3) {
            emailField.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            emailField.setStyle("");
        }

        // Verificar tamanho exato dos campos
        if (nif.length() != 9) {
            nifField.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            nifField.setStyle("");
        }

        if (contato.length() != 9) {
            contactoField.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            contactoField.setStyle("");
        }

        // Verificar se a data de nascimento é válida
        if (dataNascimento == null || dataNascimento.isAfter(dataAtual)) {
            dataNascimentoPicker.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            dataNascimentoPicker.setStyle("");
        }
        if (camposValidos) {
            Residente residente = new Residente(0, nome, sobrenome, dataNascimento.toString(), nif, contato, email, "Nenhuma", "Nenhuma");

            if (callback != null) {
                callback.onResidenteCriado(residente);
            }

            // Fechar o modal
            Stage stage = (Stage) criarButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
