package com.example.gps_g33.controller.depClinico;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Medicacao;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.modelos.Utensilio;
import com.example.gps_g33.util.InputValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ModalCriarMedicamentosUtensilios {
    private Data data;

    @FXML
    public Button criar_Button;

    @FXML
    public Button cancelar_Button;


    @FXML
    public TextField nomeField;
    public CheckBox emFaltaCheckBox;

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleCriarButton() {

        // Lógica para adicionar medicacao para um residente
        String nome = nomeField.getText();
        boolean falta = emFaltaCheckBox.isSelected();

        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,5))){
            Utensilio utensilio = new Utensilio(0, nome, falta);

            if (callback != null) {
                callback.onUtensilioCriado(utensilio);
            }

            // Fechar o modal
            Stage stage = (Stage) criar_Button.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

}
