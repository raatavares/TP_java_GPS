package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Funcionario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditController {
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

    private Funcionario funcionario;
    @FXML
    public void onEditarButton() {
        boolean camposValidos = true;

        // Lógica para criar um novo funcionário com os dados inseridos
        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();

        LocalDate dataNascimento = dataNascimentoPicker.getValue();
        LocalDate dataAtual = LocalDate.now();


        // Verificar comprimento dos campos
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

        if (email.length() < 3 || !email.contains("@")) {
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
        if (dataNascimento == null || dataNascimento.isAfter(dataAtual) || dataNascimento.isBefore(LocalDate.of(1900, 1, 1)) || dataNascimento.isAfter(LocalDate.of(2000, 1, 1))) {
            dataNascimentoPicker.setStyle("-fx-border-color: red");
            camposValidos = false;
        } else {
            dataNascimentoPicker.setStyle("");
        }

        if (camposValidos) {
            funcionario.setId(this.funcionario.getId());
            funcionario.setContato(contato);
            funcionario.setEmail(email);
            funcionario.setNif(nif);
            funcionario.setNome(nome);
            funcionario.setSobrenome(sobrenome);
            funcionario.setDataNascimento(dataNascimento.toString());

            if (callback != null) {
                callback.onFuncionarioEditado(funcionario);
            }

            // Fechar o modal
            Stage stage = (Stage) editarButton.getScene().getWindow();
            stage.close();
        }


    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    public void setFuncionarioParaEdicao(Funcionario funcionario) {
        this.funcionario = funcionario;

        nomeField.setText(funcionario.getNome());
        sobrenomeField.setText(funcionario.getSobrenome());
        nifField.setText(funcionario.getNif());
        contactoField.setText(funcionario.getContato());
        emailField.setText(funcionario.getEmail());

        String dataNascimentoString = funcionario.getDataNascimento();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString);
        dataNascimentoPicker.setValue(dataNascimento);
    }
}
