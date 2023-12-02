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

public class ModalAddAtividadeController {
    private Data data;
    private boolean adicionarParaTodos = false;
    @FXML
    public TextField nome_Atividade;

    @FXML
    public TextField nif_Atividade;

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
    public Button criar_Button;

    @FXML
    public Button cancelar_Button;

    @FXML
    public TableView<Residente> tabela_residentes;

    @FXML
    public TableColumn<Residente,String> nome_residente;

    @FXML
    public TableColumn<Residente,String> nif_residente;

    @FXML
    public ToggleButton toogleButtonTodos;

    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }
    @FXML
    public void initialize() {

        // Desabilitar a edição das TextField
        nome_Atividade.setEditable(false);
        nif_Atividade.setEditable(false);

        //inicializar a tabela de residentes
        //Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updatetable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                adicionarParaTodos = false;
                // Quando um residente é selecionado, atualizar as TextField
                nome_Atividade.setText(newSelection.getNome());
                nif_Atividade.setText(newSelection.getNif());
            }
        });
    }

    public void updatetable() {
        // Lógica para atualizar a tabela de residentes

        data = Data.getInstance();
        List<Residente> residentes = data.getResidentes();
        // Converter a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentes);

        tabela_residentes.setItems(observableList);
    }

    @FXML
    public void handleCriarButton() {
        boolean camposValidos = true;
        if(adicionarParaTodos){
            List<Residente> residentes = data.getResidentes();
            for (Residente residente: residentes){
                String nome = residente.getNome();
                String nif = residente.getNif();
                String descricao = descricao_Atividade.getText();
                LocalDate dataAtividade = dataAtividade_Atividade.getValue();
                LocalDate dataAtual = LocalDate.now();
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
                        && InputValidation.styleDataError(dataAtividade_Atividade, !InputValidation.isDataValidaRefeicoes(dataAtividade))
                )
                {
                    Atividade atividade = new Atividade(0, nome, dataAtividade.toString(), tipoAtividade, descricao, nif);

                    if (callback != null) {
                        callback.onAtividadeCriada(atividade);
                    }

                    // Fechar o modal
                    Stage stage = (Stage) criar_Button.getScene().getWindow();
                    stage.close();
                }

            }
        }
        else{
            // Lógica para a atividade para um unico residente
            String nome = nome_Atividade.getText();
            String nif = nif_Atividade.getText();
            String descricao = descricao_Atividade.getText();
            LocalDate dataAtividade = dataAtividade_Atividade.getValue();
            LocalDate dataAtual = LocalDate.now();
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

            if(InputValidation.styleTextError(nome_Atividade, !InputValidation.isLengthValid(nome,3))
                    && InputValidation.styleTextError(nif_Atividade, !InputValidation.isNif(nif))
                    && InputValidation.styleTextAreaError(descricao_Atividade, !InputValidation.isDescricaoValid(descricao,3))
                    && InputValidation.styleDataError(dataAtividade_Atividade, !InputValidation.isDataValidaRefeicoes(dataAtividade))
            ){
                Atividade atividade = new Atividade(0, nome, dataAtividade.toString(), tipoAtividade, descricao, nif);

                if (callback != null) {
                    callback.onAtividadeCriada(atividade);
                }

                // Fechar o modal
                Stage stage = (Stage) criar_Button.getScene().getWindow();
                stage.close();
            }
        }

    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleToogleButtonTodos(){
        nome_Atividade.clear();
        nif_Atividade.clear();
        adicionarParaTodos = toogleButtonTodos.isSelected();
    }
}
