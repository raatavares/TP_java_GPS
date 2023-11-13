package com.example.gps_g33.controller.gerencia;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.util.InputValidation;
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


        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,3))
                && InputValidation.styleTextError(sobrenomeField, !InputValidation.isLengthValid(sobrenome,3))
                && InputValidation.styleTextError(emailField, !InputValidation.isEmail(email) || !InputValidation.isLengthValid(email,3))
                && InputValidation.styleTextError(nifField, !InputValidation.isNif(nif))
                && InputValidation.styleTextError(contactoField, !InputValidation.isTelemovel(contato))
                && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isDataValida(dataNascimento))
                && InputValidation.styleDataError(dataNascimentoPicker, !InputValidation.isAdulto(dataNascimento))
        )
        {
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
