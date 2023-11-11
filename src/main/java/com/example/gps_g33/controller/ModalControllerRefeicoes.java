package com.example.gps_g33.controller;

import com.example.gps_g33.modelos.Data;
import com.example.gps_g33.modelos.Funcionario;
import com.example.gps_g33.modelos.Refeicao;
import com.example.gps_g33.modelos.Residente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class ModalControllerRefeicoes {
    private Data data;
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



    private ModalCallback callback;
    public void setModalCallback(ModalCallback callback) {
        this.callback = callback;
    }



    @FXML
    public void initialize() {
        //inicializar a tabela de residentes
        //Lógica para atualizar a tabela de residentes
        nome_residente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nif_residente.setCellValueFactory(new PropertyValueFactory<>("nif"));

        updatetable();

        tabela_residentes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
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
        // Convertendo a lista para uma ObservableList
        ObservableList<Residente> observableList = FXCollections.observableArrayList(residentes);

        tabela_residentes.setItems(observableList);
    }

    @FXML
    public void handleCriarButton() {
        // Lógica para criar um novo funcionário com os dados inseridos
        String nome = nome_Refeicao.getText();
        String nif = nif_Refeicao.getText();
        String descricao = descricao_Refeicao.getText();
        String dataRefeicao = dataRefeicao_Refeicao.getValue().toString();
        String tipoDieta;
        if(dieta_Refeicao.isSelected()){
            tipoDieta = "Dieta";
        }else if(dieta_Personalizada.isSelected()){
            tipoDieta = "Dieta Personalizada";
        }
        else{
            tipoDieta = "Dieta Normal";
        }

        Refeicao refeicao = new Refeicao(0, nome, dataRefeicao, descricao , nif, tipoDieta);

        if (callback != null) {
            callback.onRefeicaoCriado(refeicao);
        }

        // Fechar o modal
        Stage stage = (Stage) criar_Button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleCancelarButton() {
        // Fechar o modal sem fazer nada
        Stage stage = (Stage) cancelar_Button.getScene().getWindow();
        stage.close();
    }
}
