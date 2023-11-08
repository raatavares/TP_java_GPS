package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditControllerRefeicao {
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public TextField nomeField;

    @FXML
    public DatePicker dataRefeicaoPicker;

    @FXML
    public TextField nifField;

    @FXML
    public TextArea descricaoField;

    @FXML
    public RadioButton  dieta_Refeicao;
    @FXML
    public RadioButton  dieta_Personalizada;
    @FXML
    public RadioButton  dieta_Normal;
    @FXML
    public Button editarButton;

    @FXML
    public Button cancelarButton;

    private Refeicao refeicao;
    @FXML
    public void onEditarButton() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        String nif = nifField.getText();
        String dataNascimento = dataRefeicaoPicker.getValue().toString();
        String tipoDieta;
        if(dieta_Refeicao.isSelected()){
            tipoDieta = "Dieta";
        }else if(dieta_Personalizada.isSelected()){
            tipoDieta = "Dieta Personalizada";
        }
        else{
            tipoDieta = "Dieta Normal";
        }

        refeicao.setId(this.refeicao.getId());
        refeicao.setNome(nome);
        refeicao.setDescricao(descricao);
        refeicao.setNif(nif);
        refeicao.setTipoDieta(tipoDieta);
        refeicao.setDataRefeicao(dataNascimento);



        if (callback != null) {
            callback.onRefeicaoEditado(refeicao);
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

    public void setRefeicaoParaEdicao(Refeicao refeicao) {
        this.refeicao = refeicao;

        nomeField.setText(refeicao.getNome());
        descricaoField.setText(refeicao.getDescricao());
        nifField.setText(refeicao.getNif());
        String tipoDieta = refeicao.getTipoDieta();
        if(tipoDieta.equals("Dieta")){
            dieta_Refeicao.setSelected(true);
        }else if(tipoDieta.equals("Dieta Personalizada")){
            dieta_Personalizada.setSelected(true);
        } else{
            dieta_Normal.setSelected(true);
        }

        String dataNascimentoString = refeicao.getDataRefeicao();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString);
        dataRefeicaoPicker.setValue(dataNascimento);
    }
}
