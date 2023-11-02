package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Residente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditControllerResidente {
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
    public Button editarButton;

    @FXML
    public Button cancelarButton;

    private Residente residente;
    @FXML
    public void onEditarButton() {
        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        String dataNascimento = dataNascimentoPicker.getValue().toString();

        residente.setId(this.residente.getId());
        residente.setContato(contato);
        residente.setEmail(email);
        residente.setNif(nif);
        residente.setNome(nome);
        residente.setSobrenome(sobrenome);
        residente.setDataNascimento(dataNascimento);



        if (callback != null) {
            callback.onResidenteEditado(residente);
        }

        // Fechar o modal
        Stage stage = (Stage) editarButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void setResidenteParaEdicao(Residente residente) {
        this.residente = residente;

        nomeField.setText(residente.getNome());
        sobrenomeField.setText(residente.getSobrenome());
        nifField.setText(residente.getNif());
        contactoField.setText(residente.getContato());
        emailField.setText(residente.getEmail());

        String dataNascimentoString = residente.getDataNascimento();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString);
        dataNascimentoPicker.setValue(dataNascimento);
    }
}
