package com.example.gps_g33.controller.gerencia;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.util.InputValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Vector;

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
    public ChoiceBox<String> departamentoField;

    @FXML
    public TextField emailField;

    @FXML
    public Button criarButton;

    @FXML
    public Button cancelarButton;

    Vector<String> departamentos;

    public void initialize() {
        System.out.println("Entrei no initialize");

        departamentos = new Vector<>();
        departamentos.add("Gerencia");
        departamentos.add("Culinaria");
        departamentos.add("Animacao");
        departamentos.add("Funcionario");
        departamentos.add("Clinico");

        ObservableList<String> observableDepartamentos = FXCollections.observableArrayList(departamentos);
        departamentoField.setItems(observableDepartamentos);
    }
    @FXML
    public void handleCriarButton() {
        boolean camposValidos = true;

        // Lógica para criar um novo funcionário com os dados inseridos
        String nome = nomeField.getText();
        String sobrenome = sobrenomeField.getText();
        String nif = nifField.getText();
        String contato = contactoField.getText();
        String email = emailField.getText();
        String departamento = departamentoField.getValue();
        LocalDate dataNascimento = dataNascimentoPicker.getValue();
        LocalDate dataAtual = LocalDate.now();
        String username = nome + sobrenome;
        String password = "123456789";

        System.out.println(departamento);
        System.out.println(departamentos.contains(departamento));
        // Se todos os campos são válidos, criar o funcionário
        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,3))
                && InputValidation.styleTextError(sobrenomeField, !InputValidation.isLengthValid(sobrenome,3))
                && InputValidation.styleTextError(emailField, !InputValidation.isEmail(email) || !InputValidation.isLengthValid(email,3))
                && InputValidation.styleTextError(nifField, !InputValidation.isNif(nif))
                && InputValidation.styleTextError(contactoField, !InputValidation.isTelemovel(contato))
                && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isDataValida(dataNascimento))
                && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isAdulto(dataNascimento))
                && departamentos.contains(departamento))

        {
          Funcionario  funcionario = new Funcionario(0, nome, sobrenome, dataNascimento.toString(), nif, contato, email, departamento, username, password);

            if (callback != null) {
                callback.onFuncionarioCriado(funcionario);
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
