package com.example.gps_g33.controller.depCulinaria;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.util.InputValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditControllerResidenteCulinaria {

    private ModalCallback callback;

    private Residente residente;

    @FXML
    public TextField nomeField;


    @FXML
    public TextField nifField;


    @FXML
    public TextArea descricaoField;

    @FXML
    public RadioButton preferenciaAlimentar;

    @FXML
    public RadioButton restricaoAlimentar;

    @FXML
    public Button editarButton;

    @FXML
    public Button cancelarButton;

    private int idSelected;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    public void setResidenteParaEdicao(Residente residente) {
        this.residente = residente;

        nomeField.setText(residente.getNome());
        nifField.setText(residente.getNif());
        if(residente.getPrefAli()!=""){
            preferenciaAlimentar.setSelected(true);
            descricaoField.setText(residente.getPrefAli());

        }else{
            restricaoAlimentar.setSelected(true);
            descricaoField.setText(residente.getAlergias());
        }

        idSelected = residente.getId();
    }

    public void onEditarButton(ActionEvent actionEvent) {

        String nome = nomeField.getText();
        String nif = nifField.getText();
        String descricao = descricaoField.getText();
        String tipoDieta = null;
        if(restricaoAlimentar.isSelected()){
            tipoDieta = "Restrição Alimentar";
        }else if(preferenciaAlimentar.isSelected()){
            tipoDieta = "Preferência Alimentar";
        }

        if(InputValidation.styleTextError(nomeField, !InputValidation.isLengthValid(nome,3))
                && InputValidation.styleTextError(nifField, !InputValidation.isNif(nif))
                && InputValidation.styleTextAreaError(descricaoField, !InputValidation.isDescricaoValid(descricao,3))
                && tipoDieta != null
        ){
            if(tipoDieta.equals("Preferência Alimentar"))
                residente.setPrefAli(descricao);
            else {
                residente.setAlergias(descricao);
            }
            if (callback != null) {
                callback.onRestrictionEditada(residente);
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
}
