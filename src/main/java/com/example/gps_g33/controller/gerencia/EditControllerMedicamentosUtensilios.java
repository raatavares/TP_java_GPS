package com.example.gps_g33.controller.gerencia;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.modelos.Utensilio;
import com.example.gps_g33.util.InputValidation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditControllerMedicamentosUtensilios {
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public TextField nomeField;

    @FXML
    public CheckBox faltaField;


    @FXML
    public Button editarButton;

    @FXML
    public Button cancelarButton;

    private Utensilio utensilio;
    @FXML
    public void onEditarButton() {
        String nome = nomeField.getText();
        boolean falta = faltaField.isSelected();

        if( !nome.isEmpty() && !nome.isBlank() ){
            utensilio.setId(this.utensilio.getId());
            utensilio.setNome(nome);
            utensilio.setFalta(falta);

            if (callback != null) {
                callback.onUtensilioEditado(utensilio);
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

    public void setUtensilioParaEdicao(Utensilio utensilio) {
        this.utensilio = utensilio;

        nomeField.setText(utensilio.getNome());
        faltaField.setSelected(utensilio.getFalta());
    }
}
