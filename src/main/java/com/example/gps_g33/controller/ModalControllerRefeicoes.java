package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
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

public class ModalControllerRefeicoes {
    private Data data;
    private boolean adicionarParaTodos = false;
    @FXML
    public TextField nome_Refeicao;

    @FXML
    public TextField nif_Refeicao;

    @FXML
    public DatePicker dataRefeicao_Refeicao;

    @FXML
    public TextArea descricao_Refeicao;

    @FXML
    public RadioButton dieta_Refeicao;

    @FXML
    public RadioButton dieta_Personalizada;

    @FXML
    public RadioButton dieta_Normal;

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
        nome_Refeicao.setEditable(false);
        nif_Refeicao.setEditable(false);

        //inicializar a tabela de residentes
        //Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updatetable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                adicionarParaTodos = false;
                // Quando um residente é selecionado, atualizar as TextField
                nome_Refeicao.setText(newSelection.getNome());
                nif_Refeicao.setText(newSelection.getNif());
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
                String descricao = descricao_Refeicao.getText();
                LocalDate dataRefeicao = dataRefeicao_Refeicao.getValue();
                LocalDate dataAtual = LocalDate.now();
                String tipoDieta;
                if(dieta_Refeicao.isSelected()){
                    tipoDieta = "Dieta";
                }else if(dieta_Personalizada.isSelected()){
                    tipoDieta = "Dieta Personalizada";
                }
                else{
                    tipoDieta = "Dieta Normal";
                }


                if(InputValidation.styleTextAreaError(descricao_Refeicao, !InputValidation.isDescricaoValid(descricao,3))
                        && InputValidation.styleDataError(dataRefeicao_Refeicao, !InputValidation.isDataValidaRefeicoes(dataRefeicao))
                )
                {
                    Refeicao refeicao = new Refeicao(0, nome, dataRefeicao.toString(), descricao, nif, tipoDieta);

                    if (callback != null) {
                        callback.onRefeicaoCriado(refeicao);
                    }

                    // Fechar o modal
                    Stage stage = (Stage) criar_Button.getScene().getWindow();
                    stage.close();
                }

            }
        }
        else{
            // Lógica para a refeição para um unico residente
            String nome = nome_Refeicao.getText();
            String nif = nif_Refeicao.getText();
            String descricao = descricao_Refeicao.getText();
            LocalDate dataRefeicao = dataRefeicao_Refeicao.getValue();
            LocalDate dataAtual = LocalDate.now();
            String tipoDieta;
            if(dieta_Refeicao.isSelected()){
                tipoDieta = "Dieta";
            }else if(dieta_Personalizada.isSelected()){
                tipoDieta = "Dieta Personalizada";
            }
            else{
                tipoDieta = "Dieta Normal";
            }

            if(InputValidation.styleTextError(nome_Refeicao, !InputValidation.isLengthValid(nome,3))
                    && InputValidation.styleTextError(nif_Refeicao, !InputValidation.isNif(nif))
                    && InputValidation.styleTextAreaError(descricao_Refeicao, !InputValidation.isDescricaoValid(descricao,3))
                    && InputValidation.styleDataError(dataRefeicao_Refeicao, !InputValidation.isDataValidaRefeicoes(dataRefeicao))
            ){
                Refeicao refeicao = new Refeicao(0, nome, dataRefeicao.toString(), descricao, nif, tipoDieta);

                if (callback != null) {
                    callback.onRefeicaoCriado(refeicao);
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
        nome_Refeicao.clear();
        nif_Refeicao.clear();
        adicionarParaTodos = toogleButtonTodos.isSelected();
    }
}
