package com.example.gps_g33.controller.animacao;

import com.example.gps_g33.controller.ModalCallback;
import com.example.gps_g33.modelos.Atividade;
import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import com.example.gps_g33.util.InputValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class ModalEditAtividadeController {
    private Data data;
    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }

    @FXML
    public DatePicker dataAtividade_Atividade;

    @FXML
    public TextArea descricao_Atividade;

    @FXML
    public RadioButton atividade_ExercicioLeve;

    @FXML
    public RadioButton atividade_Piscina;

    @FXML
    public RadioButton atividade_Leitura;

    @FXML
    public RadioButton atividade_Jogos;

    @FXML
    public RadioButton atividade_Excursao;

    @FXML
    public RadioButton atividade_Outra;

    @FXML
    public Button editar_Button;

    @FXML
    public Button cancelar_Button;

    private Atividade atividade;


    @FXML
    public void initialize() {
        addRadioButtonListener(atividade_ExercicioLeve);
        addRadioButtonListener(atividade_Piscina);
        addRadioButtonListener(atividade_Leitura);
        addRadioButtonListener(atividade_Jogos);
        addRadioButtonListener(atividade_Excursao);
        addRadioButtonListener(atividade_Outra);

    }

    private void addRadioButtonListener(RadioButton radioButton) {
        radioButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                // Desmarca os outros RadioButtons
                if (radioButton != atividade_ExercicioLeve) atividade_ExercicioLeve.setSelected(false);
                if (radioButton != atividade_Piscina) atividade_Piscina.setSelected(false);
                if (radioButton != atividade_Leitura) atividade_Leitura.setSelected(false);
                if (radioButton != atividade_Jogos) atividade_Jogos.setSelected(false);
                if (radioButton != atividade_Excursao) atividade_Excursao.setSelected(false);
                if (radioButton != atividade_Outra) atividade_Outra.setSelected(false);
            }
        });
    }
    @FXML
    public void onEditarButton() {

        String descricao = descricao_Atividade.getText();
        String dataNascimento = dataAtividade_Atividade.getValue().toString();
        String tipoAtividade;
        if(atividade_ExercicioLeve.isSelected()){
            tipoAtividade = "Exercício Leve";
        }else if(atividade_Piscina.isSelected()){
            tipoAtividade = "Piscina";
        }
        else if(atividade_Leitura.isSelected()){
            tipoAtividade = "Leitura";
        }
        else if(atividade_Jogos.isSelected()){
            tipoAtividade = "Jogos";
        }
        else if(atividade_Excursao.isSelected()){
            tipoAtividade = "Excursão";
        }
        else{
            tipoAtividade = "Outra";
        }

        if(InputValidation.styleTextAreaError(descricao_Atividade, !InputValidation.isDescricaoValid(descricao,3))
                && InputValidation.styleDataError(dataAtividade_Atividade, !InputValidation.isDataValidaRefeicoes(LocalDate.parse(dataNascimento)))
        )
        {
            atividade.setId(this.atividade.getId());
            atividade.setDescricao(descricao);
            atividade.setTipoAtividade(tipoAtividade);
            atividade.setDataAtividade(dataNascimento);

            if (callback != null) {
                callback.onAtividadeEditada(atividade);
            }

            // Fechar o modal
            Stage stage = (Stage) editar_Button.getScene().getWindow();
            stage.close();
        }

    }
    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

    public void setAtividadeParaEdicao(Atividade atividade) {
        this.atividade = atividade;

        descricao_Atividade.setText(atividade.getDescricao());
        String tipoAtividade = atividade.getTipoAtividade();
        if(tipoAtividade.equals("Exercício Leve")){
            atividade_ExercicioLeve.setSelected(true);
        }else if(tipoAtividade.equals("Piscina")){
            atividade_Piscina.setSelected(true);
        }else if(tipoAtividade.equals("Leitura")){
            atividade_Leitura.setSelected(true);
        }
        else if(tipoAtividade.equals("Jogos")){
            atividade_Jogos.setSelected(true);
        }
        else if(tipoAtividade.equals("Excursão")){
            atividade_Excursao.setSelected(true);
        }
        else{
            atividade_Outra.setSelected(true);
        }

        String dataNascimentoString = atividade.getDataAtividade();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString);
        dataAtividade_Atividade.setValue(dataNascimento);
    }
}
