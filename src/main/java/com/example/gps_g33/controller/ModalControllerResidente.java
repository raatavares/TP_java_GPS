package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.util.InputValidation;
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

        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        LocalDate dataNascimento = dataNascimentoPicker.getValue();

        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,3))
        && InputValidation.styleTextError(sobrenomeField, !InputValidation.isLengthValid(sobrenome,3))
        && InputValidation.styleTextError(emailField, !InputValidation.isEmail(email) && !InputValidation.isLengthValid(email,3))
        && InputValidation.styleTextError(nifField, !InputValidation.isNif(nif))
        && InputValidation.styleTextError(contactoField, !InputValidation.isTelemovel(contato))
        && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isDataValida(dataNascimento))
        && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isAdulto(dataNascimento))
        )
        {
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


    public void setCampos(TextField nomeField, TextField sobrenomeField, TextField nifField, TextField contactoField, TextField emailField, DatePicker dataNascimentoPicker) {
        this.nomeField = nomeField;
        this.sobrenomeField = sobrenomeField;
        this.nifField = nifField;
        this.contactoField = contactoField;
        this.emailField = emailField;
        this.dataNascimentoPicker = dataNascimentoPicker;
    }



}
